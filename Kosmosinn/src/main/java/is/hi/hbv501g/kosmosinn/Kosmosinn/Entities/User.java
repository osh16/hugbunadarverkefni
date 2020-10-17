package is.hi.hbv501g.kosmosinn.Kosmosinn.Entities;

import javax.persistence.*;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String username;
	private String password;

	// Viljum ad thetta se hashad einhvernveginn
	// * private String password;

	// Viljum ad thetta se sql date format
	// * private Date signupdate;
	// * private Date lastonline;

	// Viljum bua til eh eins og:
	// * private Messages messages
	// * private Friends friends
	// * private Comments comments
	// * private Threads threads

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

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
