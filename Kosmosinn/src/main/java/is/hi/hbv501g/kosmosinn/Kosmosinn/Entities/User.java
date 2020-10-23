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

	@Column(nullable = false, unique = true)
	public String password;

	@OneToMany(mappedBy = "user")
	private List<Topic> topics = new ArrayList<>();

	@OneToMany(mappedBy = "user")
	private List<Comment> comments = new ArrayList<>();

	public User(String username, String password)  {
		this.username = username;
		this.password = password;
	}

	public User() {}

	public long getId() {
		return id;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() { return password; }
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
	public void setTopics(List<Topic> topics) {
		this.topics = topics;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
}
