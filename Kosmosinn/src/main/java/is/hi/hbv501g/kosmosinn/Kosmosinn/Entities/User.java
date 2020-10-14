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

	public User(long id, String name)  {
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
}
