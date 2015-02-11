package test;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Named
@Stateless
public class User {
	
	@PersistenceContext(unitName = "freesql")
	private EntityManager em;
	
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public String sayHello(String user) {
//		em=Persistence.createEntityManagerFactory("freesql").createEntityManager();
//		EntityTransaction et=em.getTransaction();
//		et.begin();
		em.persist(new Test());	
		//et.commit();
		return "hello "+user;
	}

}
