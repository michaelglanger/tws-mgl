/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mgl.fundamental.controller;

import com.mgl.entities.BarEntity;
import com.mgl.fundamental.controller.exceptions.IllegalOrphanException;
import com.mgl.fundamental.controller.exceptions.NonexistentEntityException;
import com.mgl.fundamental.controller.exceptions.PreexistingEntityException;
import com.mgl.fundamental.entities.Company;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mgl.fundamental.entities.Exchange;
import com.mgl.fundamental.entities.CompanyFinancialStatement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;

/**
 *
 * @author mlanger
 */
public class CompanyJpaController implements Serializable {

    public CompanyJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Company company) throws PreexistingEntityException, Exception {
        if (company.getCompanyFinancialStatementCollection() == null) {
            company.setCompanyFinancialStatementCollection(new ArrayList<CompanyFinancialStatement>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Exchange exchange = company.getExchange();
            if (exchange != null) {
                exchange = em.getReference(exchange.getClass(), exchange.getId());
                company.setExchange(exchange);
            }
            Collection<CompanyFinancialStatement> attachedCompanyFinancialStatementCollection = new ArrayList<CompanyFinancialStatement>();
            for (CompanyFinancialStatement companyFinancialStatementCollectionCompanyFinancialStatementToAttach : company.getCompanyFinancialStatementCollection()) {
                companyFinancialStatementCollectionCompanyFinancialStatementToAttach = em.getReference(companyFinancialStatementCollectionCompanyFinancialStatementToAttach.getClass(), companyFinancialStatementCollectionCompanyFinancialStatementToAttach.getId());
                attachedCompanyFinancialStatementCollection.add(companyFinancialStatementCollectionCompanyFinancialStatementToAttach);
            }
            company.setCompanyFinancialStatementCollection(attachedCompanyFinancialStatementCollection);
            em.persist(company);
            if (exchange != null) {
                exchange.getCompanyCollection().add(company);
                exchange = em.merge(exchange);
            }
            for (CompanyFinancialStatement companyFinancialStatementCollectionCompanyFinancialStatement : company.getCompanyFinancialStatementCollection()) {
                Company oldCompanyOfCompanyFinancialStatementCollectionCompanyFinancialStatement = companyFinancialStatementCollectionCompanyFinancialStatement.getCompany();
                companyFinancialStatementCollectionCompanyFinancialStatement.setCompany(company);
                companyFinancialStatementCollectionCompanyFinancialStatement = em.merge(companyFinancialStatementCollectionCompanyFinancialStatement);
                if (oldCompanyOfCompanyFinancialStatementCollectionCompanyFinancialStatement != null) {
                    oldCompanyOfCompanyFinancialStatementCollectionCompanyFinancialStatement.getCompanyFinancialStatementCollection().remove(companyFinancialStatementCollectionCompanyFinancialStatement);
                    oldCompanyOfCompanyFinancialStatementCollectionCompanyFinancialStatement = em.merge(oldCompanyOfCompanyFinancialStatementCollectionCompanyFinancialStatement);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCompany(company.getId()) != null) {
                throw new PreexistingEntityException("Company " + company + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Company company) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Company persistentCompany = em.find(Company.class, company.getId());
            Exchange exchangeOld = persistentCompany.getExchange();
            Exchange exchangeNew = company.getExchange();
            Collection<CompanyFinancialStatement> companyFinancialStatementCollectionOld = persistentCompany.getCompanyFinancialStatementCollection();
            Collection<CompanyFinancialStatement> companyFinancialStatementCollectionNew = company.getCompanyFinancialStatementCollection();
            List<String> illegalOrphanMessages = null;
            for (CompanyFinancialStatement companyFinancialStatementCollectionOldCompanyFinancialStatement : companyFinancialStatementCollectionOld) {
                if (!companyFinancialStatementCollectionNew.contains(companyFinancialStatementCollectionOldCompanyFinancialStatement)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CompanyFinancialStatement " + companyFinancialStatementCollectionOldCompanyFinancialStatement + " since its company field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (exchangeNew != null) {
                exchangeNew = em.getReference(exchangeNew.getClass(), exchangeNew.getId());
                company.setExchange(exchangeNew);
            }
            Collection<CompanyFinancialStatement> attachedCompanyFinancialStatementCollectionNew = new ArrayList<CompanyFinancialStatement>();
            for (CompanyFinancialStatement companyFinancialStatementCollectionNewCompanyFinancialStatementToAttach : companyFinancialStatementCollectionNew) {
                companyFinancialStatementCollectionNewCompanyFinancialStatementToAttach = em.getReference(companyFinancialStatementCollectionNewCompanyFinancialStatementToAttach.getClass(), companyFinancialStatementCollectionNewCompanyFinancialStatementToAttach.getId());
                attachedCompanyFinancialStatementCollectionNew.add(companyFinancialStatementCollectionNewCompanyFinancialStatementToAttach);
            }
            companyFinancialStatementCollectionNew = attachedCompanyFinancialStatementCollectionNew;
            company.setCompanyFinancialStatementCollection(companyFinancialStatementCollectionNew);
            company = em.merge(company);
            if (exchangeOld != null && !exchangeOld.equals(exchangeNew)) {
                exchangeOld.getCompanyCollection().remove(company);
                exchangeOld = em.merge(exchangeOld);
            }
            if (exchangeNew != null && !exchangeNew.equals(exchangeOld)) {
                exchangeNew.getCompanyCollection().add(company);
                exchangeNew = em.merge(exchangeNew);
            }
            for (CompanyFinancialStatement companyFinancialStatementCollectionNewCompanyFinancialStatement : companyFinancialStatementCollectionNew) {
                if (!companyFinancialStatementCollectionOld.contains(companyFinancialStatementCollectionNewCompanyFinancialStatement)) {
                    Company oldCompanyOfCompanyFinancialStatementCollectionNewCompanyFinancialStatement = companyFinancialStatementCollectionNewCompanyFinancialStatement.getCompany();
                    companyFinancialStatementCollectionNewCompanyFinancialStatement.setCompany(company);
                    companyFinancialStatementCollectionNewCompanyFinancialStatement = em.merge(companyFinancialStatementCollectionNewCompanyFinancialStatement);
                    if (oldCompanyOfCompanyFinancialStatementCollectionNewCompanyFinancialStatement != null && !oldCompanyOfCompanyFinancialStatementCollectionNewCompanyFinancialStatement.equals(company)) {
                        oldCompanyOfCompanyFinancialStatementCollectionNewCompanyFinancialStatement.getCompanyFinancialStatementCollection().remove(companyFinancialStatementCollectionNewCompanyFinancialStatement);
                        oldCompanyOfCompanyFinancialStatementCollectionNewCompanyFinancialStatement = em.merge(oldCompanyOfCompanyFinancialStatementCollectionNewCompanyFinancialStatement);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = company.getId();
                if (findCompany(id) == null) {
                    throw new NonexistentEntityException("The company with id " + id + " no longer exists.");
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
            Company company;
            try {
                company = em.getReference(Company.class, id);
                company.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The company with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<CompanyFinancialStatement> companyFinancialStatementCollectionOrphanCheck = company.getCompanyFinancialStatementCollection();
            for (CompanyFinancialStatement companyFinancialStatementCollectionOrphanCheckCompanyFinancialStatement : companyFinancialStatementCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Company (" + company + ") cannot be destroyed since the CompanyFinancialStatement " + companyFinancialStatementCollectionOrphanCheckCompanyFinancialStatement + " in its companyFinancialStatementCollection field has a non-nullable company field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Exchange exchange = company.getExchange();
            if (exchange != null) {
                exchange.getCompanyCollection().remove(company);
                exchange = em.merge(exchange);
            }
            em.remove(company);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Company> findCompanyEntities() {
        return findCompanyEntities(true, -1, -1);
    }

    public List<Company> findCompanyEntities(int maxResults, int firstResult) {
        return findCompanyEntities(false, maxResults, firstResult);
    }

    private List<Company> findCompanyEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Company.class));
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

    public Company findCompany(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Company.class, id);
        } finally {
            em.close();
        }
    }
    
    public Company findCompany(String symbol) {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("SELECT c FROM Company c WHERE c.ticker = :ticker", Company.class);
            query.setParameter("ticker", symbol);
            return (Company) query.getSingleResult();
        } catch (NoResultException noResults) {
            return null;
        } finally {
            em.close();
        }
    }

    public int getCompanyCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Company> rt = cq.from(Company.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
