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
import com.mgl.fundamental.entities.Company;
import com.mgl.fundamental.entities.CompanyFinancialStatement;
import com.mgl.fundamental.entities.FinancialStatement;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author mlanger
 */
public class CompanyFinancialStatementJpaController implements Serializable {

    public CompanyFinancialStatementJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CompanyFinancialStatement companyFinancialStatement) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Company company = companyFinancialStatement.getCompany();
            if (company != null) {
                company = em.getReference(company.getClass(), company.getId());
                companyFinancialStatement.setCompany(company);
            }
            FinancialStatement financialStatement = companyFinancialStatement.getFinancialStatement();
            if (financialStatement != null) {
                financialStatement = em.getReference(financialStatement.getClass(), financialStatement.getId());
                companyFinancialStatement.setFinancialStatement(financialStatement);
            }
            em.persist(companyFinancialStatement);
            if (company != null) {
                company.getCompanyFinancialStatementCollection().add(companyFinancialStatement);
                company = em.merge(company);
            }
            if (financialStatement != null) {
                financialStatement.getCompanyFinancialStatementCollection().add(companyFinancialStatement);
                financialStatement = em.merge(financialStatement);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCompanyFinancialStatement(companyFinancialStatement.getId()) != null) {
                throw new PreexistingEntityException("CompanyFinancialStatement " + companyFinancialStatement + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CompanyFinancialStatement companyFinancialStatement) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CompanyFinancialStatement persistentCompanyFinancialStatement = em.find(CompanyFinancialStatement.class, companyFinancialStatement.getId());
            Company companyOld = persistentCompanyFinancialStatement.getCompany();
            Company companyNew = companyFinancialStatement.getCompany();
            FinancialStatement financialStatementOld = persistentCompanyFinancialStatement.getFinancialStatement();
            FinancialStatement financialStatementNew = companyFinancialStatement.getFinancialStatement();
            if (companyNew != null) {
                companyNew = em.getReference(companyNew.getClass(), companyNew.getId());
                companyFinancialStatement.setCompany(companyNew);
            }
            if (financialStatementNew != null) {
                financialStatementNew = em.getReference(financialStatementNew.getClass(), financialStatementNew.getId());
                companyFinancialStatement.setFinancialStatement(financialStatementNew);
            }
            companyFinancialStatement = em.merge(companyFinancialStatement);
            if (companyOld != null && !companyOld.equals(companyNew)) {
                companyOld.getCompanyFinancialStatementCollection().remove(companyFinancialStatement);
                companyOld = em.merge(companyOld);
            }
            if (companyNew != null && !companyNew.equals(companyOld)) {
                companyNew.getCompanyFinancialStatementCollection().add(companyFinancialStatement);
                companyNew = em.merge(companyNew);
            }
            if (financialStatementOld != null && !financialStatementOld.equals(financialStatementNew)) {
                financialStatementOld.getCompanyFinancialStatementCollection().remove(companyFinancialStatement);
                financialStatementOld = em.merge(financialStatementOld);
            }
            if (financialStatementNew != null && !financialStatementNew.equals(financialStatementOld)) {
                financialStatementNew.getCompanyFinancialStatementCollection().add(companyFinancialStatement);
                financialStatementNew = em.merge(financialStatementNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = companyFinancialStatement.getId();
                if (findCompanyFinancialStatement(id) == null) {
                    throw new NonexistentEntityException("The companyFinancialStatement with id " + id + " no longer exists.");
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
            CompanyFinancialStatement companyFinancialStatement;
            try {
                companyFinancialStatement = em.getReference(CompanyFinancialStatement.class, id);
                companyFinancialStatement.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The companyFinancialStatement with id " + id + " no longer exists.", enfe);
            }
            Company company = companyFinancialStatement.getCompany();
            if (company != null) {
                company.getCompanyFinancialStatementCollection().remove(companyFinancialStatement);
                company = em.merge(company);
            }
            FinancialStatement financialStatement = companyFinancialStatement.getFinancialStatement();
            if (financialStatement != null) {
                financialStatement.getCompanyFinancialStatementCollection().remove(companyFinancialStatement);
                financialStatement = em.merge(financialStatement);
            }
            em.remove(companyFinancialStatement);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CompanyFinancialStatement> findCompanyFinancialStatementEntities() {
        return findCompanyFinancialStatementEntities(true, -1, -1);
    }

    public List<CompanyFinancialStatement> findCompanyFinancialStatementEntities(int maxResults, int firstResult) {
        return findCompanyFinancialStatementEntities(false, maxResults, firstResult);
    }

    private List<CompanyFinancialStatement> findCompanyFinancialStatementEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CompanyFinancialStatement.class));
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

    public CompanyFinancialStatement findCompanyFinancialStatement(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CompanyFinancialStatement.class, id);
        } finally {
            em.close();
        }
    }

    public int getCompanyFinancialStatementCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CompanyFinancialStatement> rt = cq.from(CompanyFinancialStatement.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
