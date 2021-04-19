package at.sipovsven.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import at.sipovsven.model.Content;
import at.sipovsven.model.Product;

public class ContentRepositoryJPA {

	private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("Preiskalkulator");

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	public void addContent(Content content) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;

		try {
			et = em.getTransaction();
			et.begin();

			em.persist(content);
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

	public void removeContent(int id) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;

		try {
			et = em.getTransaction();
			et.begin();
			Content content = new Content();
			content = em.find(Content.class, id);

			em.remove(content);
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

	public Content updateContent(Content content) {

		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();

		content = em.merge(content);

		et.commit();

		em.close();
		return content;

	}

	public List<Content> getAllContentNames() {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();

		@SuppressWarnings("unchecked")
		List<Content> contentNames = (List<Content>) em.createQuery("select name from Content").getResultList();

		et.commit();
		em.close();
		return contentNames;
	}

	public List<Content> getAllContent() {

		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();

		@SuppressWarnings("unchecked")

		// CASE SENSITIVE HQL QUERY!!!!
		List<Content> content = (List<Content>) em.createQuery("select c from Content c").getResultList();
		et.commit();
		em.close();
		return content;
	}


	public Content getContentByName(String name) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();
		@SuppressWarnings("unchecked")
		List<Content> contents = (List<Content>) em.createQuery("select c from Content c where c.name in :name")
				.setParameter("name", name).getResultList();

		Content content = contents.get(0);

		return content;
	}

	public Content getContentByID(int id) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();
		@SuppressWarnings("unchecked")
		List<Content> contents = (List<Content>) em.createQuery("select c from Content c where c.id in :id")
				.setParameter("id", id).getResultList();

		Content content = contents.get(0);

		return content;
	}

	
	@SuppressWarnings("unused")
	private List<Content> getContent() {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();

		@SuppressWarnings("unchecked")
		List<Content> items = (List<Content>) em.createQuery("select c from Content c").getResultList();
		et.commit();
		em.close();
		return items;

	}

}
