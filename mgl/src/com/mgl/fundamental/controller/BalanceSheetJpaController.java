/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mgl.fundamental.controller;

import com.mgl.fundamental.controller.exceptions.NonexistentEntityException;
import com.mgl.fundamental.controller.exceptions.PreexistingEntityException;
import com.mgl.fundamental.entities.BalanceSheet;
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
public class BalanceSheetJpaController implements Serializable {

    public BalanceSheetJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(BalanceSheet balanceSheet) throws PreexistingEntityException, Exception {
        if (balanceSheet.getFinancialStatementCollection() == null) {
            balanceSheet.setFinancialStatementCollection(new ArrayList<FinancialStatement>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PeriodType periodType = balanceSheet.getPeriodType();
            if (periodType != null) {
                periodType = em.getReference(periodType.getClass(), periodType.getId());
                balanceSheet.setPeriodType(periodType);
            }
            Collection<FinancialStatement> attachedFinancialStatementCollection = new ArrayList<FinancialStatement>();
            for (FinancialStatement financialStatementCollectionFinancialStatementToAttach : balanceSheet.getFinancialStatementCollection()) {
                financialStatementCollectionFinancialStatementToAttach = em.getReference(financialStatementCollectionFinancialStatementToAttach.getClass(), financialStatementCollectionFinancialStatementToAttach.getId());
                attachedFinancialStatementCollection.add(financialStatementCollectionFinancialStatementToAttach);
            }
            balanceSheet.setFinancialStatementCollection(attachedFinancialStatementCollection);
            em.persist(balanceSheet);
            if (periodType != null) {
                periodType.getBalanceSheetCollection().add(balanceSheet);
                periodType = em.merge(periodType);
            }
            for (FinancialStatement financialStatementCollectionFinancialStatement : balanceSheet.getFinancialStatementCollection()) {
                BalanceSheet oldBalanceSheetOfFinancialStatementCollectionFinancialStatement = financialStatementCollectionFinancialStatement.getBalanceSheet();
                financialStatementCollectionFinancialStatement.setBalanceSheet(balanceSheet);
                financialStatementCollectionFinancialStatement = em.merge(financialStatementCollectionFinancialStatement);
                if (oldBalanceSheetOfFinancialStatementCollectionFinancialStatement != null) {
                    oldBalanceSheetOfFinancialStatementCollectionFinancialStatement.getFinancialStatementCollection().remove(financialStatementCollectionFinancialStatement);
                    oldBalanceSheetOfFinancialStatementCollectionFinancialStatement = em.merge(oldBalanceSheetOfFinancialStatementCollectionFinancialStatement);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findBalanceSheet(balanceSheet.getId()) != null) {
                throw new PreexistingEntityException("BalanceSheet " + balanceSheet + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(BalanceSheet balanceSheet) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            BalanceSheet persistentBalanceSheet = em.find(BalanceSheet.class, balanceSheet.getId());
            PeriodType periodTypeOld = persistentBalanceSheet.getPeriodType();
            PeriodType periodTypeNew = balanceSheet.getPeriodType();
            Collection<FinancialStatement> financialStatementCollectionOld = persistentBalanceSheet.getFinancialStatementCollection();
            Collection<FinancialStatement> financialStatementCollectionNew = balanceSheet.getFinancialStatementCollection();
            if (periodTypeNew != null) {
                periodTypeNew = em.getReference(periodTypeNew.getClass(), periodTypeNew.getId());
                balanceSheet.setPeriodType(periodTypeNew);
            }
            Collection<FinancialStatement> attachedFinancialStatementCollectionNew = new ArrayList<FinancialStatement>();
            for (FinancialStatement financialStatementCollectionNewFinancialStatementToAttach : financialStatementCollectionNew) {
                financialStatementCollectionNewFinancialStatementToAttach = em.getReference(financialStatementCollectionNewFinancialStatementToAttach.getClass(), financialStatementCollectionNewFinancialStatementToAttach.getId());
                attachedFinancialStatementCollectionNew.add(financialStatementCollectionNewFinancialStatementToAttach);
            }
            financialStatementCollectionNew = attachedFinancialStatementCollectionNew;
            balanceSheet.setFinancialStatementCollection(financialStatementCollectionNew);
            balanceSheet = em.merge(balanceSheet);
            if (periodTypeOld != null && !periodTypeOld.equals(periodTypeNew)) {
                periodTypeOld.getBalanceSheetCollection().remove(balanceSheet);
                periodTypeOld = em.merge(periodTypeOld);
            }
            if (periodTypeNew != null && !periodTypeNew.equals(periodTypeOld)) {
                periodTypeNew.getBalanceSheetCollection().add(balanceSheet);
                periodTypeNew = em.merge(periodTypeNew);
            }
            for (FinancialStatement financialStatementCollectionOldFinancialStatement : financialStatementCollectionOld) {
                if (!financialStatementCollectionNew.contains(financialStatementCollectionOldFinancialStatement)) {
                    financialStatementCollectionOldFinancialStatement.setBalanceSheet(null);
                    financialStatementCollectionOldFinancialStatement = em.merge(financialStatementCollectionOldFinancialStatement);
                }
            }
            for (FinancialStatement financialStatementCollectionNewFinancialStatement : financialStatementCollectionNew) {
                if (!financialStatementCollectionOld.contains(financialStatementCollectionNewFinancialStatement)) {
                    BalanceSheet oldBalanceSheetOfFinancialStatementCollectionNewFinancialStatement = financialStatementCollectionNewFinancialStatement.getBalanceSheet();
                    financialStatementCollectionNewFinancialStatement.setBalanceSheet(balanceSheet);
                    financialStatementCollectionNewFinancialStatement = em.merge(financialStatementCollectionNewFinancialStatement);
                    if (oldBalanceSheetOfFinancialStatementCollectionNewFinancialStatement != null && !oldBalanceSheetOfFinancialStatementCollectionNewFinancialStatement.equals(balanceSheet)) {
                        oldBalanceSheetOfFinancialStatementCollectionNewFinancialStatement.getFinancialStatementCollection().remove(financialStatementCollectionNewFinancialStatement);
                        oldBalanceSheetOfFinancialStatementCollectionNewFinancialStatement = em.merge(oldBalanceSheetOfFinancialStatementCollectionNewFinancialStatement);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = balanceSheet.getId();
                if (findBalanceSheet(id) == null) {
                    throw new NonexistentEntityException("The balanceSheet with id " + id + " no longer exists.");
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
            BalanceSheet balanceSheet;
            try {
                balanceSheet = em.getReference(BalanceSheet.class, id);
                balanceSheet.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The balanceSheet with id " + id + " no longer exists.", enfe);
            }
            PeriodType periodType = balanceSheet.getPeriodType();
            if (periodType != null) {
                periodType.getBalanceSheetCollection().remove(balanceSheet);
                periodType = em.merge(periodType);
            }
            Collection<FinancialStatement> financialStatementCollection = balanceSheet.getFinancialStatementCollection();
            for (FinancialStatement financialStatementCollectionFinancialStatement : financialStatementCollection) {
                financialStatementCollectionFinancialStatement.setBalanceSheet(null);
                financialStatementCollectionFinancialStatement = em.merge(financialStatementCollectionFinancialStatement);
            }
            em.remove(balanceSheet);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<BalanceSheet> findBalanceSheetEntities() {
        return findBalanceSheetEntities(true, -1, -1);
    }

    public List<BalanceSheet> findBalanceSheetEntities(int maxResults, int firstResult) {
        return findBalanceSheetEntities(false, maxResults, firstResult);
    }

    private List<BalanceSheet> findBalanceSheetEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(BalanceSheet.class));
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

    public BalanceSheet findBalanceSheet(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(BalanceSheet.class, id);
        } finally {
            em.close();
        }
    }

    public int getBalanceSheetCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<BalanceSheet> rt = cq.from(BalanceSheet.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
