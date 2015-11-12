/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mgl.fundamental.controller;

import com.mgl.fundamental.controller.exceptions.NonexistentEntityException;
import com.mgl.fundamental.controller.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.mgl.fundamental.entities.Exchange;
import com.mgl.fundamental.entities.FinancialStatement;
import com.mgl.fundamental.entities.FiscalPeriodType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author mlanger
 */
public class FiscalPeriodTypeJpaController implements Serializable {

    public FiscalPeriodTypeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(FiscalPeriodType fiscalPeriodType) throws PreexistingEntityException, Exception {
        if (fiscalPeriodType.getFinancialStatementCollection() == null) {
            fiscalPeriodType.setFinancialStatementCollection(new ArrayList<FinancialStatement>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<FinancialStatement> attachedFinancialStatementCollection = new ArrayList<FinancialStatement>();
            for (FinancialStatement financialStatementCollectionFinancialStatementToAttach : fiscalPeriodType.getFinancialStatementCollection()) {
                financialStatementCollectionFinancialStatementToAttach = em.getReference(financialStatementCollectionFinancialStatementToAttach.getClass(), financialStatementCollectionFinancialStatementToAttach.getId());
                attachedFinancialStatementCollection.add(financialStatementCollectionFinancialStatementToAttach);
            }
            fiscalPeriodType.setFinancialStatementCollection(attachedFinancialStatementCollection);
            em.persist(fiscalPeriodType);
            for (FinancialStatement financialStatementCollectionFinancialStatement : fiscalPeriodType.getFinancialStatementCollection()) {
                FiscalPeriodType oldFiscalPeriodOfFinancialStatementCollectionFinancialStatement = financialStatementCollectionFinancialStatement.getFiscalPeriod();
                financialStatementCollectionFinancialStatement.setFiscalPeriod(fiscalPeriodType);
                financialStatementCollectionFinancialStatement = em.merge(financialStatementCollectionFinancialStatement);
                if (oldFiscalPeriodOfFinancialStatementCollectionFinancialStatement != null) {
                    oldFiscalPeriodOfFinancialStatementCollectionFinancialStatement.getFinancialStatementCollection().remove(financialStatementCollectionFinancialStatement);
                    oldFiscalPeriodOfFinancialStatementCollectionFinancialStatement = em.merge(oldFiscalPeriodOfFinancialStatementCollectionFinancialStatement);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findFiscalPeriodType(fiscalPeriodType.getId()) != null) {
                throw new PreexistingEntityException("FiscalPeriodType " + fiscalPeriodType + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(FiscalPeriodType fiscalPeriodType) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FiscalPeriodType persistentFiscalPeriodType = em.find(FiscalPeriodType.class, fiscalPeriodType.getId());
            Collection<FinancialStatement> financialStatementCollectionOld = persistentFiscalPeriodType.getFinancialStatementCollection();
            Collection<FinancialStatement> financialStatementCollectionNew = fiscalPeriodType.getFinancialStatementCollection();
            Collection<FinancialStatement> attachedFinancialStatementCollectionNew = new ArrayList<FinancialStatement>();
            for (FinancialStatement financialStatementCollectionNewFinancialStatementToAttach : financialStatementCollectionNew) {
                financialStatementCollectionNewFinancialStatementToAttach = em.getReference(financialStatementCollectionNewFinancialStatementToAttach.getClass(), financialStatementCollectionNewFinancialStatementToAttach.getId());
                attachedFinancialStatementCollectionNew.add(financialStatementCollectionNewFinancialStatementToAttach);
            }
            financialStatementCollectionNew = attachedFinancialStatementCollectionNew;
            fiscalPeriodType.setFinancialStatementCollection(financialStatementCollectionNew);
            fiscalPeriodType = em.merge(fiscalPeriodType);
            for (FinancialStatement financialStatementCollectionOldFinancialStatement : financialStatementCollectionOld) {
                if (!financialStatementCollectionNew.contains(financialStatementCollectionOldFinancialStatement)) {
                    financialStatementCollectionOldFinancialStatement.setFiscalPeriod(null);
                    financialStatementCollectionOldFinancialStatement = em.merge(financialStatementCollectionOldFinancialStatement);
                }
            }
            for (FinancialStatement financialStatementCollectionNewFinancialStatement : financialStatementCollectionNew) {
                if (!financialStatementCollectionOld.contains(financialStatementCollectionNewFinancialStatement)) {
                    FiscalPeriodType oldFiscalPeriodOfFinancialStatementCollectionNewFinancialStatement = financialStatementCollectionNewFinancialStatement.getFiscalPeriod();
                    financialStatementCollectionNewFinancialStatement.setFiscalPeriod(fiscalPeriodType);
                    financialStatementCollectionNewFinancialStatement = em.merge(financialStatementCollectionNewFinancialStatement);
                    if (oldFiscalPeriodOfFinancialStatementCollectionNewFinancialStatement != null && !oldFiscalPeriodOfFinancialStatementCollectionNewFinancialStatement.equals(fiscalPeriodType)) {
                        oldFiscalPeriodOfFinancialStatementCollectionNewFinancialStatement.getFinancialStatementCollection().remove(financialStatementCollectionNewFinancialStatement);
                        oldFiscalPeriodOfFinancialStatementCollectionNewFinancialStatement = em.merge(oldFiscalPeriodOfFinancialStatementCollectionNewFinancialStatement);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = fiscalPeriodType.getId();
                if (findFiscalPeriodType(id) == null) {
                    throw new NonexistentEntityException("The fiscalPeriodType with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FiscalPeriodType fiscalPeriodType;
            try {
                fiscalPeriodType = em.getReference(FiscalPeriodType.class, id);
                fiscalPeriodType.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The fiscalPeriodType with id " + id + " no longer exists.", enfe);
            }
            Collection<FinancialStatement> financialStatementCollection = fiscalPeriodType.getFinancialStatementCollection();
            for (FinancialStatement financialStatementCollectionFinancialStatement : financialStatementCollection) {
                financialStatementCollectionFinancialStatement.setFiscalPeriod(null);
                financialStatementCollectionFinancialStatement = em.merge(financialStatementCollectionFinancialStatement);
            }
            em.remove(fiscalPeriodType);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<FiscalPeriodType> findFiscalPeriodTypeEntities() {
        return findFiscalPeriodTypeEntities(true, -1, -1);
    }

    public List<FiscalPeriodType> findFiscalPeriodTypeEntities(int maxResults, int firstResult) {
        return findFiscalPeriodTypeEntities(false, maxResults, firstResult);
    }

    private List<FiscalPeriodType> findFiscalPeriodTypeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(FiscalPeriodType.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public FiscalPeriodType findFiscalPeriodType(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(FiscalPeriodType.class, id);
        } finally {
            em.close();
        }
    }

    public FiscalPeriodType findFiscalPeriodType(String str) {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("SELECT e FROM FiscalPeriodType e WHERE e.periodType = :periodType", FiscalPeriodType.class);
            query.setParameter("periodType", str);
            return (FiscalPeriodType) query.getSingleResult();
        } catch (NoResultException noResults) {
            return null;
        } finally {
            em.close();
        }
    }

    public int getFiscalPeriodTypeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<FiscalPeriodType> rt = cq.from(FiscalPeriodType.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
