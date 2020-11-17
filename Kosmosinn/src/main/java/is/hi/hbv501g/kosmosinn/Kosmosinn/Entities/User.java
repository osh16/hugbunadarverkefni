package is.hi.hbv501g.kosmosinn.Kosmosinn.Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false, unique = true)
	public String username;

	@Column(nullable = false)
	public String password;

	@OneToMany(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<Topic> topics = new ArrayList<>();

	@OneToMany(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<Comment> comments = new ArrayList<>();

	private String role;

	// default
	public User(String username, String password)  {
		this.username = username;
		this.password = password;
		this.role = "user";
	}

	// stofna med serstoku hlutverki
	public User(String username, String password, String role)  {
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public User() {}

	public long getId() {
		return id;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() { return password; }
	public String getRole() { return role; }

	public List<Topic> getTopics() {
		return topics;
	}
	public List<Comment> getComments() {
		return comments;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setRole(String role) { this.role = role; }
	public void setTopics(List<Topic> topics) {
		this.topics = topics;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
}
