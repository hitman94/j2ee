package images;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import user.User;

@Stateless
@Named
public class ImageController {
	@PersistenceContext(unitName = "freesql")
	private EntityManager em;
	
	
	public Image findImg(Long id) {
		return em.find(Image.class, id);
	}
	
	public Image update(Image i) {
		return em.merge(i);
	}
	
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
	
	
	public List<Image> retrieveLastPublicImageByUsername(String username, int index, int number) {
		TypedQuery<Image> q = em.createQuery("SELECT i FROM Image i WHERE i.owner.username=:username AND i.isPublic=true", Image.class);
		q.setParameter("username", username);
		q.setFirstResult(index);
		q.setMaxResults(number);
		return q.getResultList();
		
	}
	public  List<Image> getPublicImageFrom(int index,int number,String size, String name) {
		
		TypedQuery<Image> q;
		if(name.isEmpty())
			q= em.createQuery("SELECT i FROM Image i WHERE i.isPublic=true AND i.width BETWEEN :minWidth AND :maxWidth ORDER BY i.uploadedTime DESC", Image.class);
		else {
			q= em.createQuery("SELECT i FROM Image i WHERE i.isPublic=true AND i.width BETWEEN :minWidth AND :maxWidth AND i.name LIKE :name ORDER BY i.uploadedTime DESC", Image.class);
			q.setParameter("name", "%"+name+"%");
		}
		switch(size) {
		case "all":
			q.setParameter("minWidth",0);
			q.setParameter("maxWidth",20000);

			break;
			
		case "small":
			q.setParameter("minWidth",0);
			q.setParameter("maxWidth",512);
			break;
			
		case "normal":
			q.setParameter("minWidth",513);
			q.setParameter("maxWidth",1024);
			break;
			
		case "big":
			q.setParameter("minWidth",1025);
			q.setParameter("maxWidth",20000);
			break;
		}
		q.setFirstResult(index);
		q.setMaxResults(number);
		return q.getResultList();		
		
	}
	
	public void remove(Image i) {
		em.remove(em.merge(i));
	}
	
}
