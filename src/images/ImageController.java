package images;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import user.User;

@Stateless
@Named
public class ImageController {
	@PersistenceContext(unitName = "freesql")
	private EntityManager em;
	
	
	public Long createImage(String name, String description, Double width, Double length,boolean isPublic, Long uploadedTime,User u) {
		Image i = new Image(name,description,isPublic,width,length,uploadedTime,u);
		em.persist(i);
		u.addImage(i);
		em.merge(u);
		
		return i.getId();
	}
	
	public  List<Image> retriveImage(User u) {
		TypedQuery<Image> q = em.createNamedQuery("findLastImgByUser",Image.class);
		q.setParameter("user", u);
		return q.getResultList();		
		
	}
	
}
