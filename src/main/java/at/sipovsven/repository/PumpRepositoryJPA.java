package at.sipovsven.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import at.sipovsven.model.Category;
import at.sipovsven.model.Pump;
import at.sipovsven.model.Wrapping;

public class PumpRepositoryJPA {
	private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("Preiskalkulator");

	@Override
	public String toString() {
	
		return super.toString();
	}

	public void addPump(Pump pump) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;

		try {
			et = em.getTransaction();
			et.begin();

			em.persist(pump);
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
	
	public List<Pump> getAllPumps() {

		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();

		@SuppressWarnings("unchecked")

		// CASE SENSITIVE HQL QUERY!!!!
		List<Pump> pumps = (List<Pump>) em.createQuery("select p from Pump p").getResultList();
		et.commit();
		em.close();
		return pumps;
	}
	public Pump getPumpByID(int id) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();
		@SuppressWarnings("unchecked")
		List<Pump> pumps = (List<Pump>) em.createQuery("select p from Pump p where p.id in :id")
				.setParameter("id", id).getResultList();

		Pump pump = pumps.get(0);

		return pump;
	}
	public Pump updatePump(Pump pump) {

		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();

		pump = em.merge(pump);

		et.commit();

		em.close();
		return pump;

	}
	public void removePump(int id) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;

		try {
			et = em.getTransaction();
			et.begin();
			Pump pump = new Pump();
			pump = em.find(Pump.class, id);

			em.remove(pump);
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

