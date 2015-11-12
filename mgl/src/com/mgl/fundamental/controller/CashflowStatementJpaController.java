/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mgl.fundamental.controller;

import com.mgl.fundamental.controller.exceptions.NonexistentEntityException;
import com.mgl.fundamental.controller.exceptions.PreexistingEntityException;
import com.mgl.fundamental.entities.CashflowStatement;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mgl.fundamental.entities.PeriodType;
import com.mgl.fundamental.entities.FinancialStatement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author mlanger
 */
public class CashflowStatementJpaController implements Serializable {

    public CashflowStatementJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CashflowStatement cashflowStatement) throws PreexistingEntityException, Exception {
        if (cashflowStatement.getFinancialStatementCollection() == null) {
            cashflowStatement.setFinancialStatementCollection(new ArrayList<FinancialStatement>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PeriodType periodType = cashflowStatement.getPeriodType();
            if (periodType != null) {
                periodType = em.getReference(periodType.getClass(), periodType.getId());
                cashflowStatement.setPeriodType(periodType);
            }
            Collection<FinancialStatement> attachedFinancialStatementCollection = new ArrayList<FinancialStatement>();
            for (FinancialStatement financialStatementCollectionFinancialStatementToAttach : cashflowStatement.getFinancialStatementCollection()) {
                financialStatementCollectionFinancialStatementToAttach = em.getReference(financialStatementCollectionFinancialStatementToAttach.getClass(), financialStatementCollectionFinancialStatementToAttach.getId());
                attachedFinancialStatementCollection.add(financialStatementCollectionFinancialStatementToAttach);
            }
            cashflowStatement.setFinancialStatementCollection(attachedFinancialStatementCollection);
            em.persist(cashflowStatement);
            if (periodType != null) {
                periodType.getCashflowStatementCollection().add(cashflowStatement);
                periodType = em.merge(periodType);
            }
            for (FinancialStatement financialStatementCollectionFinancialStatement : cashflowStatement.getFinancialStatementCollection()) {
                CashflowStatement oldCashflowStatementOfFinancialStatementCollectionFinancialStatement = financialStatementCollectionFinancialStatement.getCashflowStatement();
                financialStatementCollectionFinancialStatement.setCashflowStatement(cashflowStatement);
                financialStatementCollectionFinancialStatement = em.merge(financialStatementCollectionFinancialStatement);
                if (oldCashflowStatementOfFinancialStatementCollectionFinancialStatement != null) {
                    oldCashflowStatementOfFinancialStatementCollectionFinancialStatement.getFinancialStatementCollection().remove(financialStatementCollectionFinancialStatement);
                    oldCashflowStatementOfFinancialStatementCollectionFinancialStatement = em.merge(oldCashflowStatementOfFinancialStatementCollectionFinancialStatement);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCashflowStatement(cashflowStatement.getId()) != null) {
                throw new PreexistingEntityException("CashflowStatement " + cashflowStatement + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CashflowStatement cashflowStatement) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CashflowStatement persistentCashflowStatement = em.find(CashflowStatement.class, cashflowStatement.getId());
            PeriodType periodTypeOld = persistentCashflowStatement.getPeriodType();
            PeriodType periodTypeNew = cashflowStatement.getPeriodType();
            Collection<FinancialStatement> financialStatementCollectionOld = persistentCashflowStatement.getFinancialStatementCollection();
            Collection<FinancialStatement> financialStatementCollectionNew = cashflowStatement.getFinancialStatementCollection();
            if (periodTypeNew != null) {
                periodTypeNew = em.getReference(periodTypeNew.getClass(), periodTypeNew.getId());
                cashflowStatement.setPeriodType(periodTypeNew);
            }
            Collection<FinancialStatement> attachedFinancialStatementCollectionNew = new ArrayList<FinancialStatement>();
            for (FinancialStatement financialStatementCollectionNewFinancialStatementToAttach : financialStatementCollectionNew) {
                financialStatementCollectionNewFinancialStatementToAttach = em.getReference(financialStatementCollectionNewFinancialStatementToAttach.getClass(), financialStatementCollectionNewFinancialStatementToAttach.getId());
                attachedFinancialStatementCollectionNew.add(financialStatementCollectionNewFinancialStatementToAttach);
            }
            financialStatementCollectionNew = attachedFinancialStatementCollectionNew;
            cashflowStatement.setFinancialStatementCollection(financialStatementCollectionNew);
            cashflowStatement = em.merge(cashflowStatement);
            if (periodTypeOld != null && !periodTypeOld.equals(periodTypeNew)) {
                periodTypeOld.getCashflowStatementCollection().remove(cashflowStatement);
                periodTypeOld = em.merge(periodTypeOld);
            }
            if (periodTypeNew != null && !periodTypeNew.equals(periodTypeOld)) {
                periodTypeNew.getCashflowStatementCollection().add(cashflowStatement);
                periodTypeNew = em.merge(periodTypeNew);
            }
            for (FinancialStatement financialStatementCollectionOldFinancialStatement : financialStatementCollectionOld) {
                if (!financialStatementCollectionNew.contains(financialStatementCollectionOldFinancialStatement)) {
                    financialStatementCollectionOldFinancialStatement.setCashflowStatement(null);
                    financialStatementCollectionOldFinancialStatement = em.merge(financialStatementCollectionOldFinancialStatement);
                }
            }
            for (FinancialStatement financialStatementCollectionNewFinancialStatement : financialStatementCollectionNew) {
                if (!financialStatementCollectionOld.contains(financialStatementCollectionNewFinancialStatement)) {
                    CashflowStatement oldCashflowStatementOfFinancialStatementCollectionNewFinancialStatement = financialStatementCollectionNewFinancialStatement.getCashflowStatement();
                    financialStatementCollectionNewFinancialStatement.setCashflowStatement(cashflowStatement);
                    financialStatementCollectionNewFinancialStatement = em.merge(financialStatementCollectionNewFinancialStatement);
                    if (oldCashflowStatementOfFinancialStatementCollectionNewFinancialStatement != null && !oldCashflowStatementOfFinancialStatementCollectionNewFinancialStatement.equals(cashflowStatement)) {
                        oldCashflowStatementOfFinancialStatementCollectionNewFinancialStatement.getFinancialStatementCollection().remove(financialStatementCollectionNewFinancialStatement);
                        oldCashflowStatementOfFinancialStatementCollectionNewFinancialStatement = em.merge(oldCashflowStatementOfFinancialStatementCollectionNewFinancialStatement);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cashflowStatement.getId();
                if (findCashflowStatement(id) == null) {
                    throw new NonexistentEntityException("The cashflowStatement with id " + id + " no longer exists.");
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
            CashflowStatement cashflowStatement;
            try {
                cashflowStatement = em.getReference(CashflowStatement.class, id);
                cashflowStatement.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cashflowStatement with id " + id + " no longer exists.", enfe);
            }
            PeriodType periodType = cashflowStatement.getPeriodType();
            if (periodType != null) {
                periodType.getCashflowStatementCollection().remove(cashflowStatement);
                periodType = em.merge(periodType);
            }
            Collection<FinancialStatement> financialStatementCollection = cashflowStatement.getFinancialStatementCollection();
            for (FinancialStatement financialStatementCollectionFinancialStatement : financialStatementCollection) {
                financialStatementCollectionFinancialStatement.setCashflowStatement(null);
                financialStatementCollectionFinancialStatement = em.merge(financialStatementCollectionFinancialStatement);
            }
            em.remove(cashflowStatement);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CashflowStatement> findCashflowStatementEntities() {
        return findCashflowStatementEntities(true, -1, -1);
    }

    public List<CashflowStatement> findCashflowStatementEntities(int maxResults, int firstResult) {
        return findCashflowStatementEntities(false, maxResults, firstResult);
    }

    private List<CashflowStatement> findCashflowStatementEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CashflowStatement.class));
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

    public CashflowStatement findCashflowStatement(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CashflowStatement.class, id);
        } finally {
            em.close();
        }
    }

    public int getCashflowStatementCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CashflowStatement> rt = cq.from(CashflowStatement.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
