/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mgl.entities;

import com.mgl.entities.exceptions.IllegalOrphanException;
import com.mgl.entities.exceptions.NonexistentEntityException;
import com.mgl.entities.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;

/**
 *
 * @author Michael G. Langer
 */
public class ContractJpaController implements Serializable {

    public ContractJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ContractEntity contract) throws PreexistingEntityException, Exception {
        if (contract.getBarCollection() == null) {
            contract.setBarCollection(new ArrayList<BarEntity>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<BarEntity> attachedBarCollection = new ArrayList<BarEntity>();
            for (BarEntity barCollectionBarToAttach : contract.getBarCollection()) {
                barCollectionBarToAttach = em.getReference(barCollectionBarToAttach.getClass(), barCollectionBarToAttach.getId());
                attachedBarCollection.add(barCollectionBarToAttach);
            }
            contract.setBarCollection(attachedBarCollection);
            em.persist(contract);
            for (BarEntity barCollectionBar : contract.getBarCollection()) {
                ContractEntity oldContractOfBarCollectionBar = barCollectionBar.getContract();
                barCollectionBar.setContract(contract);
                barCollectionBar = em.merge(barCollectionBar);
                if (oldContractOfBarCollectionBar != null) {
                    oldContractOfBarCollectionBar.getBarCollection().remove(barCollectionBar);
                    oldContractOfBarCollectionBar = em.merge(oldContractOfBarCollectionBar);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findContract(contract.getId()) != null) {
                throw new PreexistingEntityException("Contract " + contract + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ContractEntity contract) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ContractEntity persistentContract = em.find(ContractEntity.class, contract.getId());
            Collection<BarEntity> barCollectionOld = persistentContract.getBarCollection();
            Collection<BarEntity> barCollectionNew = contract.getBarCollection();
            List<String> illegalOrphanMessages = null;
            for (BarEntity barCollectionOldBar : barCollectionOld) {
                if (!barCollectionNew.contains(barCollectionOldBar)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Bar " + barCollectionOldBar + " since its contract field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<BarEntity> attachedBarCollectionNew = new ArrayList<BarEntity>();
            for (BarEntity barCollectionNewBarToAttach : barCollectionNew) {
                barCollectionNewBarToAttach = em.getReference(barCollectionNewBarToAttach.getClass(), barCollectionNewBarToAttach.getId());
                attachedBarCollectionNew.add(barCollectionNewBarToAttach);
            }
            barCollectionNew = attachedBarCollectionNew;
            contract.setBarCollection(barCollectionNew);
            contract = em.merge(contract);
            for (BarEntity barCollectionNewBar : barCollectionNew) {
                if (!barCollectionOld.contains(barCollectionNewBar)) {
                    ContractEntity oldContractOfBarCollectionNewBar = barCollectionNewBar.getContract();
                    barCollectionNewBar.setContract(contract);
                    barCollectionNewBar = em.merge(barCollectionNewBar);
                    if (oldContractOfBarCollectionNewBar != null && !oldContractOfBarCollectionNewBar.equals(contract)) {
                        oldContractOfBarCollectionNewBar.getBarCollection().remove(barCollectionNewBar);
                        oldContractOfBarCollectionNewBar = em.merge(oldContractOfBarCollectionNewBar);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = contract.getId();
                if (findContract(id) == null) {
                    throw new NonexistentEntityException("The contract with id " + id + " no longer exists.");
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
            ContractEntity contract;
            try {
                contract = em.getReference(ContractEntity.class, id);
                contract.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The contract with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<BarEntity> barCollectionOrphanCheck = contract.getBarCollection();
            for (BarEntity barCollectionOrphanCheckBar : barCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Contract (" + contract + ") cannot be destroyed since the Bar " + barCollectionOrphanCheckBar + " in its barCollection field has a non-nullable contract field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(contract);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ContractEntity> findContractEntities() {
        return findContractEntities(true, -1, -1);
    }

    public List<ContractEntity> findContractEntities(int maxResults, int firstResult) {
        return findContractEntities(false, maxResults, firstResult);
    }

    private List<ContractEntity> findContractEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ContractEntity.class));
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

    public ContractEntity findContract(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ContractEntity.class, id);
        } finally {
            em.close();
        }
    }
    
    public ContractEntity findContract(String symbol) {
         EntityManager em = getEntityManager();
        try {
            Query query = em.createNativeQuery("select * from CONTRACT c where c.symbol = ?", ContractEntity.class);
            query.setParameter(1, symbol);
            return (ContractEntity) query.getSingleResult();
        } catch (NoResultException noResults) {
            return null;
        } finally {
            em.close();
        }
    }

    public int getContractCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ContractEntity> rt = cq.from(ContractEntity.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
