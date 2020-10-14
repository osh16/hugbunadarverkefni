package is.hi.hbv501g.kosmosinn.Kosmosinn.Services.Implementations;

import is.hi.hbv501g.kosmosinn.Kosmosinn.Entities.User;
import is.hi.hbv501g.kosmosinn.Kosmosinn.Repositories.UserRepository;
import is.hi.hbv501g.kosmosinn.Kosmosinn.Services.UserService;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class UserServiceImplementation implements UserService {
	UserRepository repository;

	@Autowired
	public UserServiceImplementation(UserRepository userRepository) {
		this.repository = userRepository;
	}
	@Override
	public User save(User user) {
		return repository.save(user);
	}

	@Override
	public void delete(User user) {
		return repository.delete(user);
	}

	@Override
	public List<User> findAll() {
		return repository.findAll();
	} 

}
