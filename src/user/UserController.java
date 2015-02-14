package user;

import java.util.Objects;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.commons.codec.digest.DigestUtils;

@Stateless
@Named
public class UserController {

	@PersistenceContext(unitName = "freesql")
	private EntityManager em;
	

	public UserController() {
	}
	

	public boolean register(String username, String password, String email) {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);
		Objects.requireNonNull(email);
		try {

			User u = new User(username, DigestUtils.sha1Hex(password), email);
			em.persist(u);
		} catch (EntityExistsException e) {
			return false;
		}

		return true;
	}

	public Long connexion(String username, String password) {
		TypedQuery<User> q = em.createNamedQuery("findUser", User.class);
		q.setParameter("username", username);
		q.setParameter("password", DigestUtils.sha1Hex(password));
		try {
			User u = q.getSingleResult();
			return u.getId();
		} catch (NoResultException e) {
			return -1L;
		}
	}

	public boolean checkUsername(String username) {
		Query q = em.createNamedQuery("checkUsername");
		q.setParameter("username", username);
		try {
			q.getSingleResult();
			return false;
		} catch (NoResultException e) {
			return true;
		} catch (NonUniqueResultException e) {
			return false;
		}
	}
	

}
