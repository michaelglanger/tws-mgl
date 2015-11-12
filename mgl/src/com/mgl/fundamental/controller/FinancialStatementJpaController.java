/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mgl.fundamental.controller;

import com.mgl.fundamental.controller.exceptions.IllegalOrphanException;
import com.mgl.fundamental.controller.exceptions.NonexistentEntityException;
import com.mgl.fundamental.controller.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mgl.fundamental.entities.BalanceSheet;
import com.mgl.fundamental.entities.CashflowStatement;
import com.mgl.fundamental.entities.FiscalPeriodType;
import com.mgl.fundamental.entities.IncomeStatement;
import com.mgl.fundamental.entities.CompanyFinancialStatement;
import com.mgl.fundamental.entities.FinancialStatement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Michael G. Langer
 */
public class FinancialStatementJpaController implements Serializable {

    public FinancialStatementJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(FinancialStatement financialStatement) throws PreexistingEntityException, Exception {
        if (financialStatement.getCompanyFinancialStatementCollection() == null) {
            financialStatement.setCompanyFinancialStatementCollection(new ArrayList<CompanyFinancialStatement>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            BalanceSheet balanceSheet = financialStatement.getBalanceSheet();
            if (balanceSheet != null) {
                balanceSheet = em.getReference(balanceSheet.getClass(), balanceSheet.getId());
                financialStatement.setBalanceSheet(balanceSheet);
            }
            CashflowStatement cashflowStatement = financialStatement.getCashflowStatement();
            if (cashflowStatement != null) {
                cashflowStatement = em.getReference(cashflowStatement.getClass(), cashflowStatement.getId());
                financialStatement.setCashflowStatement(cashflowStatement);
            }
            FiscalPeriodType fiscalPeriod = financialStatement.getFiscalPeriod();
            if (fiscalPeriod != null) {
                fiscalPeriod = em.getReference(fiscalPeriod.getClass(), fiscalPeriod.getId());
                financialStatement.setFiscalPeriod(fiscalPeriod);
            }
            IncomeStatement incomeStatement = financialStatement.getIncomeStatement();
            if (incomeStatement != null) {
                incomeStatement = em.getReference(incomeStatement.getClass(), incomeStatement.getId());
                financialStatement.setIncomeStatement(incomeStatement);
            }
            Collection<CompanyFinancialStatement> attachedCompanyFinancialStatementCollection = new ArrayList<CompanyFinancialStatement>();
            for (CompanyFinancialStatement companyFinancialStatementCollectionCompanyFinancialStatementToAttach : financialStatement.getCompanyFinancialStatementCollection()) {
                companyFinancialStatementCollectionCompanyFinancialStatementToAttach = em.getReference(companyFinancialStatementCollectionCompanyFinancialStatementToAttach.getClass(), companyFinancialStatementCollectionCompanyFinancialStatementToAttach.getId());
                attachedCompanyFinancialStatementCollection.add(companyFinancialStatementCollectionCompanyFinancialStatementToAttach);
            }
            financialStatement.setCompanyFinancialStatementCollection(attachedCompanyFinancialStatementCollection);
            em.persist(financialStatement);
            if (balanceSheet != null) {
                balanceSheet.getFinancialStatementCollection().add(financialStatement);
                balanceSheet = em.merge(balanceSheet);
            }
            if (cashflowStatement != null) {
                cashflowStatement.getFinancialStatementCollection().add(financialStatement);
                cashflowStatement = em.merge(cashflowStatement);
            }
            if (fiscalPeriod != null) {
                fiscalPeriod.getFinancialStatementCollection().add(financialStatement);
                fiscalPeriod = em.merge(fiscalPeriod);
            }
            if (incomeStatement != null) {
                incomeStatement.getFinancialStatementCollection().add(financialStatement);
                incomeStatement = em.merge(incomeStatement);
            }
            for (CompanyFinancialStatement companyFinancialStatementCollectionCompanyFinancialStatement : financialStatement.getCompanyFinancialStatementCollection()) {
                FinancialStatement oldFinancialStatementOfCompanyFinancialStatementCollectionCompanyFinancialStatement = companyFinancialStatementCollectionCompanyFinancialStatement.getFinancialStatement();
                companyFinancialStatementCollectionCompanyFinancialStatement.setFinancialStatement(financialStatement);
                companyFinancialStatementCollectionCompanyFinancialStatement = em.merge(companyFinancialStatementCollectionCompanyFinancialStatement);
                if (oldFinancialStatementOfCompanyFinancialStatementCollectionCompanyFinancialStatement != null) {
                    oldFinancialStatementOfCompanyFinancialStatementCollectionCompanyFinancialStatement.getCompanyFinancialStatementCollection().remove(companyFinancialStatementCollectionCompanyFinancialStatement);
                    oldFinancialStatementOfCompanyFinancialStatementCollectionCompanyFinancialStatement = em.merge(oldFinancialStatementOfCompanyFinancialStatementCollectionCompanyFinancialStatement);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findFinancialStatement(financialStatement.getId()) != null) {
                throw new PreexistingEntityException("FinancialStatement " + financialStatement + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(FinancialStatement financialStatement) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FinancialStatement persistentFinancialStatement = em.find(FinancialStatement.class, financialStatement.getId());
            BalanceSheet balanceSheetOld = persistentFinancialStatement.getBalanceSheet();
            BalanceSheet balanceSheetNew = financialStatement.getBalanceSheet();
            CashflowStatement cashflowStatementOld = persistentFinancialStatement.getCashflowStatement();
            CashflowStatement cashflowStatementNew = financialStatement.getCashflowStatement();
            FiscalPeriodType fiscalPeriodOld = persistentFinancialStatement.getFiscalPeriod();
            FiscalPeriodType fiscalPeriodNew = financialStatement.getFiscalPeriod();
            IncomeStatement incomeStatementOld = persistentFinancialStatement.getIncomeStatement();
            IncomeStatement incomeStatementNew = financialStatement.getIncomeStatement();
            Collection<CompanyFinancialStatement> companyFinancialStatementCollectionOld = persistentFinancialStatement.getCompanyFinancialStatementCollection();
            Collection<CompanyFinancialStatement> companyFinancialStatementCollectionNew = financialStatement.getCompanyFinancialStatementCollection();
            List<String> illegalOrphanMessages = null;
            for (CompanyFinancialStatement companyFinancialStatementCollectionOldCompanyFinancialStatement : companyFinancialStatementCollectionOld) {
                if (!companyFinancialStatementCollectionNew.contains(companyFinancialStatementCollectionOldCompanyFinancialStatement)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CompanyFinancialStatement " + companyFinancialStatementCollectionOldCompanyFinancialStatement + " since its financialStatement field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (balanceSheetNew != null) {
                balanceSheetNew = em.getReference(balanceSheetNew.getClass(), balanceSheetNew.getId());
                financialStatement.setBalanceSheet(balanceSheetNew);
            }
            if (cashflowStatementNew != null) {
                cashflowStatementNew = em.getReference(cashflowStatementNew.getClass(), cashflowStatementNew.getId());
                financialStatement.setCashflowStatement(cashflowStatementNew);
            }
            if (fiscalPeriodNew != null) {
                fiscalPeriodNew = em.getReference(fiscalPeriodNew.getClass(), fiscalPeriodNew.getId());
                financialStatement.setFiscalPeriod(fiscalPeriodNew);
            }
            if (incomeStatementNew != null) {
                incomeStatementNew = em.getReference(incomeStatementNew.getClass(), incomeStatementNew.getId());
                financialStatement.setIncomeStatement(incomeStatementNew);
            }
            Collection<CompanyFinancialStatement> attachedCompanyFinancialStatementCollectionNew = new ArrayList<CompanyFinancialStatement>();
            for (CompanyFinancialStatement companyFinancialStatementCollectionNewCompanyFinancialStatementToAttach : companyFinancialStatementCollectionNew) {
                companyFinancialStatementCollectionNewCompanyFinancialStatementToAttach = em.getReference(companyFinancialStatementCollectionNewCompanyFinancialStatementToAttach.getClass(), companyFinancialStatementCollectionNewCompanyFinancialStatementToAttach.getId());
                attachedCompanyFinancialStatementCollectionNew.add(companyFinancialStatementCollectionNewCompanyFinancialStatementToAttach);
            }
            companyFinancialStatementCollectionNew = attachedCompanyFinancialStatementCollectionNew;
            financialStatement.setCompanyFinancialStatementCollection(companyFinancialStatementCollectionNew);
            financialStatement = em.merge(financialStatement);
            if (balanceSheetOld != null && !balanceSheetOld.equals(balanceSheetNew)) {
                balanceSheetOld.getFinancialStatementCollection().remove(financialStatement);
                balanceSheetOld = em.merge(balanceSheetOld);
            }
            if (balanceSheetNew != null && !balanceSheetNew.equals(balanceSheetOld)) {
                balanceSheetNew.getFinancialStatementCollection().add(financialStatement);
                balanceSheetNew = em.merge(balanceSheetNew);
            }
            if (cashflowStatementOld != null && !cashflowStatementOld.equals(cashflowStatementNew)) {
                cashflowStatementOld.getFinancialStatementCollection().remove(financialStatement);
                cashflowStatementOld = em.merge(cashflowStatementOld);
            }
            if (cashflowStatementNew != null && !cashflowStatementNew.equals(cashflowStatementOld)) {
                cashflowStatementNew.getFinancialStatementCollection().add(financialStatement);
                cashflowStatementNew = em.merge(cashflowStatementNew);
            }
            if (fiscalPeriodOld != null && !fiscalPeriodOld.equals(fiscalPeriodNew)) {
                fiscalPeriodOld.getFinancialStatementCollection().remove(financialStatement);
                fiscalPeriodOld = em.merge(fiscalPeriodOld);
            }
            if (fiscalPeriodNew != null && !fiscalPeriodNew.equals(fiscalPeriodOld)) {
                fiscalPeriodNew.getFinancialStatementCollection().add(financialStatement);
                fiscalPeriodNew = em.merge(fiscalPeriodNew);
            }
            if (incomeStatementOld != null && !incomeStatementOld.equals(incomeStatementNew)) {
                incomeStatementOld.getFinancialStatementCollection().remove(financialStatement);
                incomeStatementOld = em.merge(incomeStatementOld);
            }
            if (incomeStatementNew != null && !incomeStatementNew.equals(incomeStatementOld)) {
                incomeStatementNew.getFinancialStatementCollection().add(financialStatement);
                incomeStatementNew = em.merge(incomeStatementNew);
            }
            for (CompanyFinancialStatement companyFinancialStatementCollectionNewCompanyFinancialStatement : companyFinancialStatementCollectionNew) {
                if (!companyFinancialStatementCollectionOld.contains(companyFinancialStatementCollectionNewCompanyFinancialStatement)) {
                    FinancialStatement oldFinancialStatementOfCompanyFinancialStatementCollectionNewCompanyFinancialStatement = companyFinancialStatementCollectionNewCompanyFinancialStatement.getFinancialStatement();
                    companyFinancialStatementCollectionNewCompanyFinancialStatement.setFinancialStatement(financialStatement);
                    companyFinancialStatementCollectionNewCompanyFinancialStatement = em.merge(companyFinancialStatementCollectionNewCompanyFinancialStatement);
                    if (oldFinancialStatementOfCompanyFinancialStatementCollectionNewCompanyFinancialStatement != null && !oldFinancialStatementOfCompanyFinancialStatementCollectionNewCompanyFinancialStatement.equals(financialStatement)) {
                        oldFinancialStatementOfCompanyFinancialStatementCollectionNewCompanyFinancialStatement.getCompanyFinancialStatementCollection().remove(companyFinancialStatementCollectionNewCompanyFinancialStatement);
                        oldFinancialStatementOfCompanyFinancialStatementCollectionNewCompanyFinancialStatement = em.merge(oldFinancialStatementOfCompanyFinancialStatementCollectionNewCompanyFinancialStatement);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = financialStatement.getId();
                if (findFinancialStatement(id) == null) {
                    throw new NonexistentEntityException("The financialStatement with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FinancialStatement financialStatement;
            try {
                financialStatement = em.getReference(FinancialStatement.class, id);
                financialStatement.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The financialStatement with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<CompanyFinancialStatement> companyFinancialStatementCollectionOrphanCheck = financialStatement.getCompanyFinancialStatementCollection();
            for (CompanyFinancialStatement companyFinancialStatementCollectionOrphanCheckCompanyFinancialStatement : companyFinancialStatementCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This FinancialStatement (" + financialStatement + ") cannot be destroyed since the CompanyFinancialStatement " + companyFinancialStatementCollectionOrphanCheckCompanyFinancialStatement + " in its companyFinancialStatementCollection field has a non-nullable financialStatement field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            BalanceSheet balanceSheet = financialStatement.getBalanceSheet();
            if (balanceSheet != null) {
                balanceSheet.getFinancialStatementCollection().remove(financialStatement);
                balanceSheet = em.merge(balanceSheet);
            }
            CashflowStatement cashflowStatement = financialStatement.getCashflowStatement();
            if (cashflowStatement != null) {
                cashflowStatement.getFinancialStatementCollection().remove(financialStatement);
                cashflowStatement = em.merge(cashflowStatement);
            }
            FiscalPeriodType fiscalPeriod = financialStatement.getFiscalPeriod();
            if (fiscalPeriod != null) {
                fiscalPeriod.getFinancialStatementCollection().remove(financialStatement);
                fiscalPeriod = em.merge(fiscalPeriod);
            }
            IncomeStatement incomeStatement = financialStatement.getIncomeStatement();
            if (incomeStatement != null) {
                incomeStatement.getFinancialStatementCollection().remove(financialStatement);
                incomeStatement = em.merge(incomeStatement);
            }
            em.remove(financialStatement);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<FinancialStatement> findFinancialStatementEntities() {
        return findFinancialStatementEntities(true, -1, -1);
    }

    public List<FinancialStatement> findFinancialStatementEntities(int maxResults, int firstResult) {
        return findFinancialStatementEntities(false, maxResults, firstResult);
    }

    private List<FinancialStatement> findFinancialStatementEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(FinancialStatement.class));
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

    public FinancialStatement findFinancialStatement(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(FinancialStatement.class, id);
        } finally {
            em.close();
        }
    }

    public int getFinancialStatementCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<FinancialStatement> rt = cq.from(FinancialStatement.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
