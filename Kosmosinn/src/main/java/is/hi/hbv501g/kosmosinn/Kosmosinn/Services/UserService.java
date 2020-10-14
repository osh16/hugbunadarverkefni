package is.hi.hbv501g.kosmosinn.Kosmosinn.Services;
import is.hi.hbv501g.kosmosinn.Kosmosinn.Entities.User;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserService {
	User save(User user);
	void delete(User user);
	List<User> listAll();
}
