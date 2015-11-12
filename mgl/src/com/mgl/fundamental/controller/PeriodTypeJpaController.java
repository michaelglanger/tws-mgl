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
import com.mgl.fundamental.entities.CashflowStatement;
import java.util.ArrayList;
import java.util.Collection;
import com.mgl.fundamental.entities.IncomeStatement;
import com.mgl.fundamental.entities.BalanceSheet;
import com.mgl.fundamental.entities.PeriodType;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author mlanger
 */
public class PeriodTypeJpaController implements Serializable {

    public PeriodTypeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PeriodType periodType) throws PreexistingEntityException, Exception {
        if (periodType.getCashflowStatementCollection() == null) {
            periodType.setCashflowStatementCollection(new ArrayList<CashflowStatement>());
        }
        if (periodType.getIncomeStatementCollection() == null) {
            periodType.setIncomeStatementCollection(new ArrayList<IncomeStatement>());
        }
        if (periodType.getBalanceSheetCollection() == null) {
            periodType.setBalanceSheetCollection(new ArrayList<BalanceSheet>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<CashflowStatement> attachedCashflowStatementCollection = new ArrayList<CashflowStatement>();
            for (CashflowStatement cashflowStatementCollectionCashflowStatementToAttach : periodType.getCashflowStatementCollection()) {
                cashflowStatementCollectionCashflowStatementToAttach = em.getReference(cashflowStatementCollectionCashflowStatementToAttach.getClass(), cashflowStatementCollectionCashflowStatementToAttach.getId());
                attachedCashflowStatementCollection.add(cashflowStatementCollectionCashflowStatementToAttach);
            }
            periodType.setCashflowStatementCollection(attachedCashflowStatementCollection);
            Collection<IncomeStatement> attachedIncomeStatementCollection = new ArrayList<IncomeStatement>();
            for (IncomeStatement incomeStatementCollectionIncomeStatementToAttach : periodType.getIncomeStatementCollection()) {
                incomeStatementCollectionIncomeStatementToAttach = em.getReference(incomeStatementCollectionIncomeStatementToAttach.getClass(), incomeStatementCollectionIncomeStatementToAttach.getId());
                attachedIncomeStatementCollection.add(incomeStatementCollectionIncomeStatementToAttach);
            }
            periodType.setIncomeStatementCollection(attachedIncomeStatementCollection);
            Collection<BalanceSheet> attachedBalanceSheetCollection = new ArrayList<BalanceSheet>();
            for (BalanceSheet balanceSheetCollectionBalanceSheetToAttach : periodType.getBalanceSheetCollection()) {
                balanceSheetCollectionBalanceSheetToAttach = em.getReference(balanceSheetCollectionBalanceSheetToAttach.getClass(), balanceSheetCollectionBalanceSheetToAttach.getId());
                attachedBalanceSheetCollection.add(balanceSheetCollectionBalanceSheetToAttach);
            }
            periodType.setBalanceSheetCollection(attachedBalanceSheetCollection);
            em.persist(periodType);
            for (CashflowStatement cashflowStatementCollectionCashflowStatement : periodType.getCashflowStatementCollection()) {
                PeriodType oldPeriodTypeOfCashflowStatementCollectionCashflowStatement = cashflowStatementCollectionCashflowStatement.getPeriodType();
                cashflowStatementCollectionCashflowStatement.setPeriodType(periodType);
                cashflowStatementCollectionCashflowStatement = em.merge(cashflowStatementCollectionCashflowStatement);
                if (oldPeriodTypeOfCashflowStatementCollectionCashflowStatement != null) {
                    oldPeriodTypeOfCashflowStatementCollectionCashflowStatement.getCashflowStatementCollection().remove(cashflowStatementCollectionCashflowStatement);
                    oldPeriodTypeOfCashflowStatementCollectionCashflowStatement = em.merge(oldPeriodTypeOfCashflowStatementCollectionCashflowStatement);
                }
            }
            for (IncomeStatement incomeStatementCollectionIncomeStatement : periodType.getIncomeStatementCollection()) {
                PeriodType oldPeriodTypeOfIncomeStatementCollectionIncomeStatement = incomeStatementCollectionIncomeStatement.getPeriodType();
                incomeStatementCollectionIncomeStatement.setPeriodType(periodType);
                incomeStatementCollectionIncomeStatement = em.merge(incomeStatementCollectionIncomeStatement);
                if (oldPeriodTypeOfIncomeStatementCollectionIncomeStatement != null) {
                    oldPeriodTypeOfIncomeStatementCollectionIncomeStatement.getIncomeStatementCollection().remove(incomeStatementCollectionIncomeStatement);
                    oldPeriodTypeOfIncomeStatementCollectionIncomeStatement = em.merge(oldPeriodTypeOfIncomeStatementCollectionIncomeStatement);
                }
            }
            for (BalanceSheet balanceSheetCollectionBalanceSheet : periodType.getBalanceSheetCollection()) {
                PeriodType oldPeriodTypeOfBalanceSheetCollectionBalanceSheet = balanceSheetCollectionBalanceSheet.getPeriodType();
                balanceSheetCollectionBalanceSheet.setPeriodType(periodType);
                balanceSheetCollectionBalanceSheet = em.merge(balanceSheetCollectionBalanceSheet);
                if (oldPeriodTypeOfBalanceSheetCollectionBalanceSheet != null) {
                    oldPeriodTypeOfBalanceSheetCollectionBalanceSheet.getBalanceSheetCollection().remove(balanceSheetCollectionBalanceSheet);
                    oldPeriodTypeOfBalanceSheetCollectionBalanceSheet = em.merge(oldPeriodTypeOfBalanceSheetCollectionBalanceSheet);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPeriodType(periodType.getId()) != null) {
                throw new PreexistingEntityException("PeriodType " + periodType + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PeriodType periodType) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PeriodType persistentPeriodType = em.find(PeriodType.class, periodType.getId());
            Collection<CashflowStatement> cashflowStatementCollectionOld = persistentPeriodType.getCashflowStatementCollection();
            Collection<CashflowStatement> cashflowStatementCollectionNew = periodType.getCashflowStatementCollection();
            Collection<IncomeStatement> incomeStatementCollectionOld = persistentPeriodType.getIncomeStatementCollection();
            Collection<IncomeStatement> incomeStatementCollectionNew = periodType.getIncomeStatementCollection();
            Collection<BalanceSheet> balanceSheetCollectionOld = persistentPeriodType.getBalanceSheetCollection();
            Collection<BalanceSheet> balanceSheetCollectionNew = periodType.getBalanceSheetCollection();
            Collection<CashflowStatement> attachedCashflowStatementCollectionNew = new ArrayList<CashflowStatement>();
            for (CashflowStatement cashflowStatementCollectionNewCashflowStatementToAttach : cashflowStatementCollectionNew) {
                cashflowStatementCollectionNewCashflowStatementToAttach = em.getReference(cashflowStatementCollectionNewCashflowStatementToAttach.getClass(), cashflowStatementCollectionNewCashflowStatementToAttach.getId());
                attachedCashflowStatementCollectionNew.add(cashflowStatementCollectionNewCashflowStatementToAttach);
            }
            cashflowStatementCollectionNew = attachedCashflowStatementCollectionNew;
            periodType.setCashflowStatementCollection(cashflowStatementCollectionNew);
            Collection<IncomeStatement> attachedIncomeStatementCollectionNew = new ArrayList<IncomeStatement>();
            for (IncomeStatement incomeStatementCollectionNewIncomeStatementToAttach : incomeStatementCollectionNew) {
                incomeStatementCollectionNewIncomeStatementToAttach = em.getReference(incomeStatementCollectionNewIncomeStatementToAttach.getClass(), incomeStatementCollectionNewIncomeStatementToAttach.getId());
                attachedIncomeStatementCollectionNew.add(incomeStatementCollectionNewIncomeStatementToAttach);
            }
            incomeStatementCollectionNew = attachedIncomeStatementCollectionNew;
            periodType.setIncomeStatementCollection(incomeStatementCollectionNew);
            Collection<BalanceSheet> attachedBalanceSheetCollectionNew = new ArrayList<BalanceSheet>();
            for (BalanceSheet balanceSheetCollectionNewBalanceSheetToAttach : balanceSheetCollectionNew) {
                balanceSheetCollectionNewBalanceSheetToAttach = em.getReference(balanceSheetCollectionNewBalanceSheetToAttach.getClass(), balanceSheetCollectionNewBalanceSheetToAttach.getId());
                attachedBalanceSheetCollectionNew.add(balanceSheetCollectionNewBalanceSheetToAttach);
            }
            balanceSheetCollectionNew = attachedBalanceSheetCollectionNew;
            periodType.setBalanceSheetCollection(balanceSheetCollectionNew);
            periodType = em.merge(periodType);
            for (CashflowStatement cashflowStatementCollectionOldCashflowStatement : cashflowStatementCollectionOld) {
                if (!cashflowStatementCollectionNew.contains(cashflowStatementCollectionOldCashflowStatement)) {
                    cashflowStatementCollectionOldCashflowStatement.setPeriodType(null);
                    cashflowStatementCollectionOldCashflowStatement = em.merge(cashflowStatementCollectionOldCashflowStatement);
                }
            }
            for (CashflowStatement cashflowStatementCollectionNewCashflowStatement : cashflowStatementCollectionNew) {
                if (!cashflowStatementCollectionOld.contains(cashflowStatementCollectionNewCashflowStatement)) {
                    PeriodType oldPeriodTypeOfCashflowStatementCollectionNewCashflowStatement = cashflowStatementCollectionNewCashflowStatement.getPeriodType();
                    cashflowStatementCollectionNewCashflowStatement.setPeriodType(periodType);
                    cashflowStatementCollectionNewCashflowStatement = em.merge(cashflowStatementCollectionNewCashflowStatement);
                    if (oldPeriodTypeOfCashflowStatementCollectionNewCashflowStatement != null && !oldPeriodTypeOfCashflowStatementCollectionNewCashflowStatement.equals(periodType)) {
                        oldPeriodTypeOfCashflowStatementCollectionNewCashflowStatement.getCashflowStatementCollection().remove(cashflowStatementCollectionNewCashflowStatement);
                        oldPeriodTypeOfCashflowStatementCollectionNewCashflowStatement = em.merge(oldPeriodTypeOfCashflowStatementCollectionNewCashflowStatement);
                    }
                }
            }
            for (IncomeStatement incomeStatementCollectionOldIncomeStatement : incomeStatementCollectionOld) {
                if (!incomeStatementCollectionNew.contains(incomeStatementCollectionOldIncomeStatement)) {
                    incomeStatementCollectionOldIncomeStatement.setPeriodType(null);
                    incomeStatementCollectionOldIncomeStatement = em.merge(incomeStatementCollectionOldIncomeStatement);
                }
            }
            for (IncomeStatement incomeStatementCollectionNewIncomeStatement : incomeStatementCollectionNew) {
                if (!incomeStatementCollectionOld.contains(incomeStatementCollectionNewIncomeStatement)) {
                    PeriodType oldPeriodTypeOfIncomeStatementCollectionNewIncomeStatement = incomeStatementCollectionNewIncomeStatement.getPeriodType();
                    incomeStatementCollectionNewIncomeStatement.setPeriodType(periodType);
                    incomeStatementCollectionNewIncomeStatement = em.merge(incomeStatementCollectionNewIncomeStatement);
                    if (oldPeriodTypeOfIncomeStatementCollectionNewIncomeStatement != null && !oldPeriodTypeOfIncomeStatementCollectionNewIncomeStatement.equals(periodType)) {
                        oldPeriodTypeOfIncomeStatementCollectionNewIncomeStatement.getIncomeStatementCollection().remove(incomeStatementCollectionNewIncomeStatement);
                        oldPeriodTypeOfIncomeStatementCollectionNewIncomeStatement = em.merge(oldPeriodTypeOfIncomeStatementCollectionNewIncomeStatement);
                    }
                }
            }
            for (BalanceSheet balanceSheetCollectionOldBalanceSheet : balanceSheetCollectionOld) {
                if (!balanceSheetCollectionNew.contains(balanceSheetCollectionOldBalanceSheet)) {
                    balanceSheetCollectionOldBalanceSheet.setPeriodType(null);
                    balanceSheetCollectionOldBalanceSheet = em.merge(balanceSheetCollectionOldBalanceSheet);
                }
            }
            for (BalanceSheet balanceSheetCollectionNewBalanceSheet : balanceSheetCollectionNew) {
                if (!balanceSheetCollectionOld.contains(balanceSheetCollectionNewBalanceSheet)) {
                    PeriodType oldPeriodTypeOfBalanceSheetCollectionNewBalanceSheet = balanceSheetCollectionNewBalanceSheet.getPeriodType();
                    balanceSheetCollectionNewBalanceSheet.setPeriodType(periodType);
                    balanceSheetCollectionNewBalanceSheet = em.merge(balanceSheetCollectionNewBalanceSheet);
                    if (oldPeriodTypeOfBalanceSheetCollectionNewBalanceSheet != null && !oldPeriodTypeOfBalanceSheetCollectionNewBalanceSheet.equals(periodType)) {
                        oldPeriodTypeOfBalanceSheetCollectionNewBalanceSheet.getBalanceSheetCollection().remove(balanceSheetCollectionNewBalanceSheet);
                        oldPeriodTypeOfBalanceSheetCollectionNewBalanceSheet = em.merge(oldPeriodTypeOfBalanceSheetCollectionNewBalanceSheet);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = periodType.getId();
                if (findPeriodType(id) == null) {
                    throw new NonexistentEntityException("The periodType with id " + id + " no longer exists.");
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
            PeriodType periodType;
            try {
                periodType = em.getReference(PeriodType.class, id);
                periodType.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The periodType with id " + id + " no longer exists.", enfe);
            }
            Collection<CashflowStatement> cashflowStatementCollection = periodType.getCashflowStatementCollection();
            for (CashflowStatement cashflowStatementCollectionCashflowStatement : cashflowStatementCollection) {
                cashflowStatementCollectionCashflowStatement.setPeriodType(null);
                cashflowStatementCollectionCashflowStatement = em.merge(cashflowStatementCollectionCashflowStatement);
            }
            Collection<IncomeStatement> incomeStatementCollection = periodType.getIncomeStatementCollection();
            for (IncomeStatement incomeStatementCollectionIncomeStatement : incomeStatementCollection) {
                incomeStatementCollectionIncomeStatement.setPeriodType(null);
                incomeStatementCollectionIncomeStatement = em.merge(incomeStatementCollectionIncomeStatement);
            }
            Collection<BalanceSheet> balanceSheetCollection = periodType.getBalanceSheetCollection();
            for (BalanceSheet balanceSheetCollectionBalanceSheet : balanceSheetCollection) {
                balanceSheetCollectionBalanceSheet.setPeriodType(null);
                balanceSheetCollectionBalanceSheet = em.merge(balanceSheetCollectionBalanceSheet);
            }
            em.remove(periodType);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PeriodType> findPeriodTypeEntities() {
        return findPeriodTypeEntities(true, -1, -1);
    }

    public List<PeriodType> findPeriodTypeEntities(int maxResults, int firstResult) {
        return findPeriodTypeEntities(false, maxResults, firstResult);
    }

    private List<PeriodType> findPeriodTypeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PeriodType.class));
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

    public PeriodType findPeriodType(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PeriodType.class, id);
        } finally {
            em.close();
        }
    }

    public int getPeriodTypeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PeriodType> rt = cq.from(PeriodType.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
