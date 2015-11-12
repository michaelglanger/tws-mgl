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
import com.mgl.fundamental.entities.Exchange;
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
public class ExchangeJpaController implements Serializable {

    public ExchangeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Exchange exchange) throws PreexistingEntityException, Exception {
        if (exchange.getCompanyCollection() == null) {
            exchange.setCompanyCollection(new ArrayList<Company>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Company> attachedCompanyCollection = new ArrayList<Company>();
            for (Company companyCollectionCompanyToAttach : exchange.getCompanyCollection()) {
                companyCollectionCompanyToAttach = em.getReference(companyCollectionCompanyToAttach.getClass(), companyCollectionCompanyToAttach.getId());
                attachedCompanyCollection.add(companyCollectionCompanyToAttach);
            }
            exchange.setCompanyCollection(attachedCompanyCollection);
            em.persist(exchange);
            for (Company companyCollectionCompany : exchange.getCompanyCollection()) {
                Exchange oldExchangeOfCompanyCollectionCompany = companyCollectionCompany.getExchange();
                companyCollectionCompany.setExchange(exchange);
                companyCollectionCompany = em.merge(companyCollectionCompany);
                if (oldExchangeOfCompanyCollectionCompany != null) {
                    oldExchangeOfCompanyCollectionCompany.getCompanyCollection().remove(companyCollectionCompany);
                    oldExchangeOfCompanyCollectionCompany = em.merge(oldExchangeOfCompanyCollectionCompany);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findExchange(exchange.getId()) != null) {
                throw new PreexistingEntityException("Exchange " + exchange + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Exchange exchange) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Exchange persistentExchange = em.find(Exchange.class, exchange.getId());
            Collection<Company> companyCollectionOld = persistentExchange.getCompanyCollection();
            Collection<Company> companyCollectionNew = exchange.getCompanyCollection();
            Collection<Company> attachedCompanyCollectionNew = new ArrayList<Company>();
            for (Company companyCollectionNewCompanyToAttach : companyCollectionNew) {
                companyCollectionNewCompanyToAttach = em.getReference(companyCollectionNewCompanyToAttach.getClass(), companyCollectionNewCompanyToAttach.getId());
                attachedCompanyCollectionNew.add(companyCollectionNewCompanyToAttach);
            }
            companyCollectionNew = attachedCompanyCollectionNew;
            exchange.setCompanyCollection(companyCollectionNew);
            exchange = em.merge(exchange);
            for (Company companyCollectionOldCompany : companyCollectionOld) {
                if (!companyCollectionNew.contains(companyCollectionOldCompany)) {
                    companyCollectionOldCompany.setExchange(null);
                    companyCollectionOldCompany = em.merge(companyCollectionOldCompany);
                }
            }
            for (Company companyCollectionNewCompany : companyCollectionNew) {
                if (!companyCollectionOld.contains(companyCollectionNewCompany)) {
                    Exchange oldExchangeOfCompanyCollectionNewCompany = companyCollectionNewCompany.getExchange();
                    companyCollectionNewCompany.setExchange(exchange);
                    companyCollectionNewCompany = em.merge(companyCollectionNewCompany);
                    if (oldExchangeOfCompanyCollectionNewCompany != null && !oldExchangeOfCompanyCollectionNewCompany.equals(exchange)) {
                        oldExchangeOfCompanyCollectionNewCompany.getCompanyCollection().remove(companyCollectionNewCompany);
                        oldExchangeOfCompanyCollectionNewCompany = em.merge(oldExchangeOfCompanyCollectionNewCompany);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = exchange.getId();
                if (findExchange(id) == null) {
                    throw new NonexistentEntityException("The exchange with id " + id + " no longer exists.");
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
            Exchange exchange;
            try {
                exchange = em.getReference(Exchange.class, id);
                exchange.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The exchange with id " + id + " no longer exists.", enfe);
            }
            Collection<Company> companyCollection = exchange.getCompanyCollection();
            for (Company companyCollectionCompany : companyCollection) {
                companyCollectionCompany.setExchange(null);
                companyCollectionCompany = em.merge(companyCollectionCompany);
            }
            em.remove(exchange);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Exchange> findExchangeEntities() {
        return findExchangeEntities(true, -1, -1);
    }

    public List<Exchange> findExchangeEntities(int maxResults, int firstResult) {
        return findExchangeEntities(false, maxResults, firstResult);
    }

    private List<Exchange> findExchangeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Exchange.class));
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

    public Exchange findExchange(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Exchange.class, id);
        } finally {
            em.close();
        }
    }
    
    public Exchange findExchange(String code) {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("SELECT e FROM Exchange e WHERE e.code = :code", Exchange.class);
            query.setParameter("code", code);
            return (Exchange) query.getSingleResult();
        } catch (NoResultException noResults) {
            return null;
        } finally {
            em.close();
        }
    }

    public int getExchangeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Exchange> rt = cq.from(Exchange.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
