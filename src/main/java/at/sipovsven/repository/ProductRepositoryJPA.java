package at.sipovsven.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import at.sipovsven.model.Content;
import at.sipovsven.model.LabelModel;
import at.sipovsven.model.Product;
import at.sipovsven.model.Pump;
import at.sipovsven.model.Shipping;
import at.sipovsven.model.Wrapping;

public class ProductRepositoryJPA {

	private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("Preiskalkulator");


    
	@Override
	public String toString() {
	
		return super.toString();
	}

	public void addProduct(Product product) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;

		try {
			et = em.getTransaction();
			et.begin();

			em.persist(product);
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


	
	public void removeProduct(int id) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;

		try {
			et = em.getTransaction();
			et.begin();
			Product product = new Product();
			product = em.find(Product.class, id);

			em.remove(product);
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
	
	public Product updatePurchasePrices(Product product) {

		ContentRepositoryJPA contentRepo = new ContentRepositoryJPA();
		
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();

		Content content =  contentRepo.getContentByID(1);
		LabelModel label2 = new LabelModel();
		
		product.setPurchasePrice(content.getPrice() + label2.getPrice());
		product = em.merge(product);

		et.commit();

		em.close();
		return product;

	}

	public Product updateProduct(Product product) {

		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();

		product = em.merge(product);

		et.commit();

		em.close();
		return product;

	}

	public List<Product> getAllProductNames() {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();

		@SuppressWarnings("unchecked")
		List<Product> productNames = (List<Product>) em.createQuery("select name from Product").getResultList();

		et.commit();
		em.close();
		return productNames;
	}

	public List<Product> getProductsByLabel(LabelModel label){
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		et.begin();
		@SuppressWarnings("unchecked")
		List<Product> products = (List<Product>) em.createQuery("select p from Product p where p.label in :label")
		.setParameter("label", label).getResultList();
		et.commit();
		em.close();
		return products;
	}
	
	public List<Product> getAllProducts() {

		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();

		@SuppressWarnings("unchecked")

		// CASE SENSITIVE HQL QUERY!!!!
		List<Product> products = (List<Product>) em.createQuery("select p from Product p").getResultList();
		et.commit();
		em.close();
		return products;
	}


	public Product getProductByName(String name) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();
		@SuppressWarnings("unchecked")
		List<Product> products = (List<Product>) em.createQuery("select p from Product p where p.name in :name")
				.setParameter("name", name).getResultList();

		Product product = products.get(0);

		return product;
	}

	public Product getProductByID(int id) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();
		@SuppressWarnings("unchecked")
		List<Product> products = (List<Product>) em.createQuery("select p from Product p where p.id in :id")
				.setParameter("id", id).getResultList();

		Product product = products.get(0);

		return product;
	}

	public List<Product> getProductsByPump(Pump pump) {
		
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		et.begin();
		@SuppressWarnings("unchecked")
		List<Product> products = (List<Product>) em.createQuery("select p from Product p where p.pump in :pump")
		.setParameter("pump", pump).getResultList();
		et.commit();
		em.close();
		return products;
	}

	public List<Product> getProductsByContent(Content content) {
		
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		et.begin();
		@SuppressWarnings("unchecked")
		List<Product> products = (List<Product>) em.createQuery("select p from Product p where p.content in :content")
		.setParameter("content", content).getResultList();
		et.commit();
		em.close();
		return products;
	}

	public List<Product> getProductsByShipping(Shipping ship) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		et.begin();
		@SuppressWarnings("unchecked")
		List<Product> products = (List<Product>) em.createQuery("select p from Product p where p.shipping in :shipping")
		.setParameter("shipping", ship).getResultList();
		et.commit();
		em.close();
		return products;
	}

	public List<Product> getProductsByWrapping(Wrapping wrap) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		et.begin();
		@SuppressWarnings("unchecked")
		List<Product> products = (List<Product>) em.createQuery("select p from Product p where p.wrapping in :wrapping")
		.setParameter("wrapping", wrap).getResultList();
		et.commit();
		em.close();
		return products;
	}

	
	


}
