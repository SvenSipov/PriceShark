package at.sipovsven.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import at.sipovsven.model.Category;
import at.sipovsven.model.Product;

public class CategoryRepositoryJPA {
	private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
			.createEntityManagerFactory("Preiskalkulator");

	@Override
	public String toString() {

		return super.toString();
	}

	public void addCategory(Category category) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;

		try {
			et = em.getTransaction();
			et.begin();

			em.persist(category);
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

	public List<Category> getAllCategories() {

		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();

		@SuppressWarnings("unchecked")

		// CASE SENSITIVE HQL QUERY!!!!
		List<Category> categories = (List<Category>) em.createQuery("select c from Category c").getResultList();
		et.commit();
		em.close();
		return categories;
	}

	public Category getCategoryByID(int id) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();
		@SuppressWarnings("unchecked")
		List<Category> categories = (List<Category>) em.createQuery("select c from Category c where c.id in :id")
				.setParameter("id", id).getResultList();

		Category category = categories.get(0);

		return category;
	}

	public Category updateCategory(Category category) {

		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();

		category = em.merge(category);

		et.commit();

		em.close();
		return category;

	}
	

	
	public void removeCategory(int id) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;

		try {
			et = em.getTransaction();
			et.begin();
			Category category = new Category();
			category = em.find(Category.class, id);

			em.remove(category);
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
