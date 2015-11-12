/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mgl.entities;

import com.mgl.entities.exceptions.NonexistentEntityException;
import com.mgl.entities.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author axjyb
 */
public class BarJpaController implements Serializable {

    public BarJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(BarEntity bar) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ContractEntity contract = bar.getContract();
            if (contract != null) {
                contract = em.getReference(contract.getClass(), contract.getId());
                bar.setContract(contract);
            }
            em.persist(bar);
            if (contract != null) {
                contract.getBarCollection().add(bar);
                contract = em.merge(contract);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findBar(bar.getId()) != null) {
                throw new PreexistingEntityException("Bar " + bar + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null /*&& em.isOpen()*/) {
                em.close();
            }
        }
    }

    public void edit(BarEntity bar) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            BarEntity persistentBar = em.find(BarEntity.class, bar.getId());
            ContractEntity contractOld = persistentBar.getContract();
            ContractEntity contractNew = bar.getContract();
            if (contractNew != null) {
                contractNew = em.getReference(contractNew.getClass(), contractNew.getId());
                bar.setContract(contractNew);
            }
            bar = em.merge(bar);
            if (contractOld != null && !contractOld.equals(contractNew)) {
                contractOld.getBarCollection().remove(bar);
                contractOld = em.merge(contractOld);
            }
            if (contractNew != null && !contractNew.equals(contractOld)) {
                contractNew.getBarCollection().add(bar);
                contractNew = em.merge(contractNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = bar.getId();
                if (findBar(id) == null) {
                    throw new NonexistentEntityException("The bar with id " + id + " no longer exists.");
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
            BarEntity bar;
            try {
                bar = em.getReference(BarEntity.class, id);
                bar.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The bar with id " + id + " no longer exists.", enfe);
            }
            ContractEntity contract = bar.getContract();
            if (contract != null) {
                contract.getBarCollection().remove(bar);
                contract = em.merge(contract);
            }
            em.remove(bar);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<BarEntity> findBarEntities() {
        return findBarEntities(true, -1, -1);
    }

    public List<BarEntity> findBarEntities(int maxResults, int firstResult) {
        return findBarEntities(false, maxResults, firstResult);
    }

    private List<BarEntity> findBarEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(BarEntity.class));
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

    public BarEntity findBar(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(BarEntity.class, id);
        } finally {
            em.close();
        }
    }
    
    
     public List<BarEntity> findBarEntities(ContractEntity contract, Date date) {
         EntityManager em = getEntityManager();
        try {
            
            Query query = em.createQuery("SELECT b FROM BarEntity b WHERE b.date <= :date and b.contract = :contract", BarEntity.class);
            query.setParameter("date", date);
            query.setParameter("contract", contract);
            return (List<BarEntity>) query.getResultList();
 
        } catch (NoResultException noResults) {
            return null;
        } finally {
            em.close();
        }
     }
    
    public BarEntity findBar(ContractEntity contract, Date date) {
        EntityManager em = getEntityManager();
        try {
            
            Query query = em.createQuery("SELECT b FROM BarEntity b WHERE b.date = :date and b.contract = :contract", BarEntity.class);
            query.setParameter("date", date);
            query.setParameter("contract", contract);
            return (BarEntity) query.getSingleResult();
 
        } catch (NoResultException noResults) {
            return null;
        } finally {
            em.close();
        }
    }
    
    public BarEntity findLastBar(ContractEntity contract) {
        EntityManager em = getEntityManager();
        try {
            
            Query query = em.createQuery("SELECT b FROM BarEntity b WHERE b.contract = :contract and b.date = (select max(be.date) from BarEntity be where be.contract = :contract2)", BarEntity.class);
            query.setParameter("contract", contract);
            query.setParameter("contract2", contract);
            return (BarEntity) query.getSingleResult();
 
        } catch (NoResultException noResults) {
            return null;
        } finally {
            em.close();
        }
    }
    
    
    public int getBarCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<BarEntity> rt = cq.from(BarEntity.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
