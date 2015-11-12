/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mgl.fundamental.controller;

import com.mgl.fundamental.controller.exceptions.NonexistentEntityException;
import com.mgl.fundamental.controller.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mgl.fundamental.entities.PeriodType;
import com.mgl.fundamental.entities.FinancialStatement;
import com.mgl.fundamental.entities.IncomeStatement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author mlanger
 */
public class IncomeStatementJpaController implements Serializable {

    public IncomeStatementJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(IncomeStatement incomeStatement) throws PreexistingEntityException, Exception {
        if (incomeStatement.getFinancialStatementCollection() == null) {
            incomeStatement.setFinancialStatementCollection(new ArrayList<FinancialStatement>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PeriodType periodType = incomeStatement.getPeriodType();
            if (periodType != null) {
                periodType = em.getReference(periodType.getClass(), periodType.getId());
                incomeStatement.setPeriodType(periodType);
            }
            Collection<FinancialStatement> attachedFinancialStatementCollection = new ArrayList<FinancialStatement>();
            for (FinancialStatement financialStatementCollectionFinancialStatementToAttach : incomeStatement.getFinancialStatementCollection()) {
                financialStatementCollectionFinancialStatementToAttach = em.getReference(financialStatementCollectionFinancialStatementToAttach.getClass(), financialStatementCollectionFinancialStatementToAttach.getId());
                attachedFinancialStatementCollection.add(financialStatementCollectionFinancialStatementToAttach);
            }
            incomeStatement.setFinancialStatementCollection(attachedFinancialStatementCollection);
            em.persist(incomeStatement);
            if (periodType != null) {
                periodType.getIncomeStatementCollection().add(incomeStatement);
                periodType = em.merge(periodType);
            }
            for (FinancialStatement financialStatementCollectionFinancialStatement : incomeStatement.getFinancialStatementCollection()) {
                IncomeStatement oldIncomeStatementOfFinancialStatementCollectionFinancialStatement = financialStatementCollectionFinancialStatement.getIncomeStatement();
                financialStatementCollectionFinancialStatement.setIncomeStatement(incomeStatement);
                financialStatementCollectionFinancialStatement = em.merge(financialStatementCollectionFinancialStatement);
                if (oldIncomeStatementOfFinancialStatementCollectionFinancialStatement != null) {
                    oldIncomeStatementOfFinancialStatementCollectionFinancialStatement.getFinancialStatementCollection().remove(financialStatementCollectionFinancialStatement);
                    oldIncomeStatementOfFinancialStatementCollectionFinancialStatement = em.merge(oldIncomeStatementOfFinancialStatementCollectionFinancialStatement);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findIncomeStatement(incomeStatement.getId()) != null) {
                throw new PreexistingEntityException("IncomeStatement " + incomeStatement + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(IncomeStatement incomeStatement) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            IncomeStatement persistentIncomeStatement = em.find(IncomeStatement.class, incomeStatement.getId());
            PeriodType periodTypeOld = persistentIncomeStatement.getPeriodType();
            PeriodType periodTypeNew = incomeStatement.getPeriodType();
            Collection<FinancialStatement> financialStatementCollectionOld = persistentIncomeStatement.getFinancialStatementCollection();
            Collection<FinancialStatement> financialStatementCollectionNew = incomeStatement.getFinancialStatementCollection();
            if (periodTypeNew != null) {
                periodTypeNew = em.getReference(periodTypeNew.getClass(), periodTypeNew.getId());
                incomeStatement.setPeriodType(periodTypeNew);
            }
            Collection<FinancialStatement> attachedFinancialStatementCollectionNew = new ArrayList<FinancialStatement>();
            for (FinancialStatement financialStatementCollectionNewFinancialStatementToAttach : financialStatementCollectionNew) {
                financialStatementCollectionNewFinancialStatementToAttach = em.getReference(financialStatementCollectionNewFinancialStatementToAttach.getClass(), financialStatementCollectionNewFinancialStatementToAttach.getId());
                attachedFinancialStatementCollectionNew.add(financialStatementCollectionNewFinancialStatementToAttach);
            }
            financialStatementCollectionNew = attachedFinancialStatementCollectionNew;
            incomeStatement.setFinancialStatementCollection(financialStatementCollectionNew);
            incomeStatement = em.merge(incomeStatement);
            if (periodTypeOld != null && !periodTypeOld.equals(periodTypeNew)) {
                periodTypeOld.getIncomeStatementCollection().remove(incomeStatement);
                periodTypeOld = em.merge(periodTypeOld);
            }
            if (periodTypeNew != null && !periodTypeNew.equals(periodTypeOld)) {
                periodTypeNew.getIncomeStatementCollection().add(incomeStatement);
                periodTypeNew = em.merge(periodTypeNew);
            }
            for (FinancialStatement financialStatementCollectionOldFinancialStatement : financialStatementCollectionOld) {
                if (!financialStatementCollectionNew.contains(financialStatementCollectionOldFinancialStatement)) {
                    financialStatementCollectionOldFinancialStatement.setIncomeStatement(null);
                    financialStatementCollectionOldFinancialStatement = em.merge(financialStatementCollectionOldFinancialStatement);
                }
            }
            for (FinancialStatement financialStatementCollectionNewFinancialStatement : financialStatementCollectionNew) {
                if (!financialStatementCollectionOld.contains(financialStatementCollectionNewFinancialStatement)) {
                    IncomeStatement oldIncomeStatementOfFinancialStatementCollectionNewFinancialStatement = financialStatementCollectionNewFinancialStatement.getIncomeStatement();
                    financialStatementCollectionNewFinancialStatement.setIncomeStatement(incomeStatement);
                    financialStatementCollectionNewFinancialStatement = em.merge(financialStatementCollectionNewFinancialStatement);
                    if (oldIncomeStatementOfFinancialStatementCollectionNewFinancialStatement != null && !oldIncomeStatementOfFinancialStatementCollectionNewFinancialStatement.equals(incomeStatement)) {
                        oldIncomeStatementOfFinancialStatementCollectionNewFinancialStatement.getFinancialStatementCollection().remove(financialStatementCollectionNewFinancialStatement);
                        oldIncomeStatementOfFinancialStatementCollectionNewFinancialStatement = em.merge(oldIncomeStatementOfFinancialStatementCollectionNewFinancialStatement);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = incomeStatement.getId();
                if (findIncomeStatement(id) == null) {
                    throw new NonexistentEntityException("The incomeStatement with id " + id + " no longer exists.");
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
            IncomeStatement incomeStatement;
            try {
                incomeStatement = em.getReference(IncomeStatement.class, id);
                incomeStatement.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The incomeStatement with id " + id + " no longer exists.", enfe);
            }
            PeriodType periodType = incomeStatement.getPeriodType();
            if (periodType != null) {
                periodType.getIncomeStatementCollection().remove(incomeStatement);
                periodType = em.merge(periodType);
            }
            Collection<FinancialStatement> financialStatementCollection = incomeStatement.getFinancialStatementCollection();
            for (FinancialStatement financialStatementCollectionFinancialStatement : financialStatementCollection) {
                financialStatementCollectionFinancialStatement.setIncomeStatement(null);
                financialStatementCollectionFinancialStatement = em.merge(financialStatementCollectionFinancialStatement);
            }
            em.remove(incomeStatement);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<IncomeStatement> findIncomeStatementEntities() {
        return findIncomeStatementEntities(true, -1, -1);
    }

    public List<IncomeStatement> findIncomeStatementEntities(int maxResults, int firstResult) {
        return findIncomeStatementEntities(false, maxResults, firstResult);
    }

    private List<IncomeStatement> findIncomeStatementEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(IncomeStatement.class));
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

    public IncomeStatement findIncomeStatement(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(IncomeStatement.class, id);
        } finally {
            em.close();
        }
    }

    public int getIncomeStatementCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<IncomeStatement> rt = cq.from(IncomeStatement.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
