package user;

import images.Image;

import java.util.Set;
import java.util.SortedSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.eclipse.persistence.jpa.config.Cascade;

@Entity
@NamedQueries({
		@NamedQuery(name = "checkUsername", query = "SELECT u FROM User u WHERE u.username=:username"),
		@NamedQuery(name = "findUser", query = "SELECT u FROM User u WHERE u.username=:username AND u.password=:password") })
public class User {

	public static final int minUsernameLength = 4;
	public static final int maxUsernameLength = 15;
	public static final String regexpmail = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)+$";

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	@Size(min = minUsernameLength, max = maxUsernameLength)
	private String username;

	@NotNull
	private String password;

	@NotNull
	@Pattern(regexp = regexpmail)
	private String email;

	@OneToMany(orphanRemoval = true, fetch=FetchType.EAGER,mappedBy = "owner")
	private Set<Image> imageList;

	public User() {
	}

	public User(String username, String password, String email) {
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	public void addImage(Image img) {
		imageList.add(img);
	}

	public Set<Image> getImageList() {
		return imageList;
	}

	public void setImageList(Set<Image> imageList) {
		this.imageList = imageList;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public Long getId() {
		return id;
	}

}
