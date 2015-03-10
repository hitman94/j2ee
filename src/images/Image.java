package images;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;

import user.User;


@Entity
@NamedQueries({
		@NamedQuery(name="findLastImgByUser", query = "SELECT i FROM Image i WHERE i.owner=:user ORDER BY i.uploadedTime DESC "),
		@NamedQuery(name="getPublicImageByDate", query = "SELECT i FROM Image i WHERE i.isPublic=true ORDER BY i.uploadedTime DESC")
})
public class Image {

	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull
	private String name;
	
	@NotNull
	private boolean isPublic;
	
	private String description;
	
	@NotNull
	private Double width;
	
	@NotNull
	private Double length;
	
	@NotNull
	private Long uploadedTime;
		
	@ManyToOne
	@JoinColumn(name = "OWNER_ID")
	private User owner;
	
	public Image() {
		// TODO Auto-generated constructor stub
	}
	
	public Image(String name, String desc, boolean isPublic,Double width, Double length, Long uploadedTime,User owner) {
		this.name=name;
		this.description=desc;
		this.isPublic=isPublic;
		this.width=width;
		this.length=length;
		this.uploadedTime=uploadedTime;
		this.owner=owner;
	}
	
	

	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Double getLength() {
		return length;
	}
	
	public void setLength(Double length) {
		this.length = length;
	}
	
	public Long getUploadedTime() {
		return uploadedTime;
	}
	public void setUploadedTime(Long uploadedTime) {
		this.uploadedTime = uploadedTime;
	}
	
	public Double getWidth() {
		return width;
	}
	
	public void setWidth(Double width) {
		this.width = width;
	}
	
	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}
	
	public boolean isPublic() {
		return isPublic;
	}
	
	public User getOwner() {
		return owner;
	}
	
	public void setOwner(User owner) {
		this.owner = owner;
	}
	
}
