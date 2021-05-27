package com.order.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.order.exceptions.WebExceptions;
import com.order.entity.Entity;

public class OrderManagementDAOImpl implements OrderMangementDAO {

	private final EntityManagerFactory emf;

	public OrderManagementDAOImpl() {
		emf = Persistence.createEntityManagerFactory("ordermanagement");
	}

	@SuppressWarnings("unchecked")
	public List<? extends Entity> getAll(String tableName) {
		List<? extends Entity> result = new ArrayList<>();
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			result = em.createQuery("from " + tableName).getResultList();
			tx.commit();
		} catch (Exception e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			throw new WebExceptions(e.getMessage()).InternalException();
		} finally {
			em.close();
		}
		return result;
	}

	public <T> Entity getById(Long id, Class<T> t) {
		Entity result = null;
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			result = (Entity) em.find(t, id);
			tx.commit();
		} catch (Exception e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			throw new WebExceptions(e.getMessage()).InternalException();
		} finally {
			em.close();
		}
		return result;
	}

	public Long save(Entity data) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			em.persist(data);
			em.flush();
			tx.commit();
		} catch (Exception e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			throw new WebExceptions(e.getMessage()).InternalException();
		} finally {
			em.close();
		}
		return data.getId();
	}

	public Boolean merge(Entity data) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			em.merge(data);
			tx.commit();
		} catch (Exception e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			throw new WebExceptions(e.getMessage()).InternalException();
		} finally {
			em.close();
		}
		return true;
	}

	public Boolean delete(Entity data) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			em.remove(em.contains(data) ? data : em.merge(data));
			tx.commit();
		} catch (Exception e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			throw new WebExceptions(e.getMessage()).InternalException();
		} finally {
			em.close();
		}
		return true;
	}

}
