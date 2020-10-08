package is.hi.hbv501g.kosmosinn.Kosmosinn.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String name;
	private String password;

	// Viljum ad thetta se sql date format
	// * private Date signupdate;
	// * private Date lastonline;

	// Viljum bua til eh eins og:
	// * private Messages messages
	// * private Friends friends
	// * private Comments comments
	// * private Threads threads

	public User(String name, String password) {
		this.name = name;
		this.password = password;
	}
	
}
