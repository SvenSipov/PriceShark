package at.sipovsven.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import at.sipovsven.model.Category;
import at.sipovsven.model.LabelModel;
import at.sipovsven.model.Product;

public class LabelRepositoryJPA {

	
	private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("Preiskalkulator");

	@Override
	public String toString() {
	
		return super.toString();
	}

	public void addLabel(LabelModel label) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;

		try {
			et = em.getTransaction();
			et.begin();

			em.persist(label);
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
	
	public List<LabelModel> getAllLabels() {

		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();

		@SuppressWarnings("unchecked")

		// CASE SENSITIVE HQL QUERY!!!!
		List<LabelModel> labels = (List<LabelModel>) em.createQuery("select l from LabelModel l").getResultList();
		et.commit();
		em.close();
		return labels;
	}
	public LabelModel getLabelByID(int id) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();
		@SuppressWarnings("unchecked")
		List<LabelModel> labels = (List<LabelModel>) em.createQuery("select l from LabelModel l where l.id in :id")
				.setParameter("id", id).getResultList();

		LabelModel label = labels.get(0);

		return label;
	}
	public LabelModel updateLabel(LabelModel label) {

		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();

		label = em.merge(label);

		et.commit();

		em.close();
		return label;

	}
	
	public void removeLabel(int id) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;

		try {
			et = em.getTransaction();
			et.begin();
			LabelModel label = new LabelModel();
			label = em.find(LabelModel.class, id);

			em.remove(label);
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
