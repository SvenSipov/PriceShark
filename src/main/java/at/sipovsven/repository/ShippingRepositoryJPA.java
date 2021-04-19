package at.sipovsven.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import at.sipovsven.model.Category;
import at.sipovsven.model.Pump;
import at.sipovsven.model.Shipping;

public class ShippingRepositoryJPA {
	private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("Preiskalkulator");

	@Override
	public String toString() {
	
		return super.toString();
	}

	public void addShipping(Shipping shipping) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;

		try {
			et = em.getTransaction();
			et.begin();

			em.persist(shipping);
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
	
	public List<Shipping> getAllShippings() {

		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();

		@SuppressWarnings("unchecked")

		// CASE SENSITIVE HQL QUERY!!!!
		List<Shipping> shippings = (List<Shipping>) em.createQuery("select s from Shipping s").getResultList();
		et.commit();
		em.close();
		return shippings;
	}
	public Shipping getShippingByID(int id) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();
		@SuppressWarnings("unchecked")
		List<Shipping> shippings = (List<Shipping>) em.createQuery("select s from Shipping s where p.id in :id")
				.setParameter("id", id).getResultList();

		Shipping shipping = shippings.get(0);

		return shipping;
	}
	public Shipping updateShipping(Shipping shipping) {

		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();

		shipping = em.merge(shipping);

		et.commit();

		em.close();
		return shipping;

	}	public void removeShipping(int id) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;

		try {
			et = em.getTransaction();
			et.begin();
			Shipping shipping = new Shipping();
			shipping = em.find(Shipping.class, id);

			em.remove(shipping);
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

