package user;

import java.util.Objects;

import javax.ejb.Stateful;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.commons.codec.digest.DigestUtils;

@Stateful
public class UserController {

	@PersistenceContext(unitName = "freesql")
	private EntityManager em;

	private User user = null;

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

	public boolean connexion(String username, String password) {
		TypedQuery<User> q = em.createNamedQuery("findUser", User.class);
		q.setParameter("username", username);
		q.setParameter("password", password);
		try {
			user = q.getSingleResult();
			return true;
		} catch (NoResultException e) {
			return false;
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

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}
}
