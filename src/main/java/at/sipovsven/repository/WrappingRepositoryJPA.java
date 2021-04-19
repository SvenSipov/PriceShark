package at.sipovsven.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import at.sipovsven.model.Category;
import at.sipovsven.model.LabelModel;
import at.sipovsven.model.Wrapping;

public class WrappingRepositoryJPA {
	private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("Preiskalkulator");

	@Override
	public String toString() {
	
		return super.toString();
	}

	public void addWrap(Wrapping wrap) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;

		try {
			et = em.getTransaction();
			et.begin();

			em.persist(wrap);
			et.commit();

		} catch (Exception e) {

			if (et != null) {
				et.rollback();
			}
			e.printStackTrace();

		} finally {
			em.close();
		}
	}
	
	public List<Wrapping> getAllWraps() {

		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();

		@SuppressWarnings("unchecked")

		// CASE SENSITIVE HQL QUERY!!!!
		List<Wrapping> wraps = (List<Wrapping>) em.createQuery("select w from Wrapping w").getResultList();
		et.commit();
		em.close();
		return wraps;
	}
	public Wrapping getWrapByID(int id) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();
		@SuppressWarnings("unchecked")
		List<Wrapping> wraps = (List<Wrapping>) em.createQuery("select w from Wrapping w where w.id in :id")
				.setParameter("id", id).getResultList();

		Wrapping wrap = wraps.get(0);

		return wrap;
	}
	public Wrapping updateWrap(Wrapping wrap) {

		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();

		wrap = em.merge(wrap);

		et.commit();

		em.close();
		return wrap;

	}
	public void removeWrapping(int id) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;

		try {
			et = em.getTransaction();
			et.begin();
			Wrapping wrap = new Wrapping();
			wrap = em.find(Wrapping.class, id);

			em.remove(wrap);
			et.commit();

		} catch (Exception e) {

			if (et != null) {
				et.rollback();
			}
			e.printStackTrace();

		} finally {
			em.close();
		}

	}
}

