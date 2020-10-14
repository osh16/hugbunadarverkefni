package is.hi.hbv501g.kosmosinn.Kosmosinn.Services.Implementations;
import is.hi.hbv501g.kosmosinn.Kosmosinn.Entities.User;
import is.hi.hbv501g.kosmosinn.Kosmosinn.Repositories.UserRepository;
import is.hi.hbv501g.kosmosinn.Kosmosinn.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
		repository.delete(user);
	}

	@Override
	public List<User> findAll() {
		return repository.findAll();
	}

	@Override
	public List<User> findByUserame(String username) {
		return repository.findByUsername(username);
	}

	@Override
	public Optional<User> findById(long id) {
		return repository.findById(id);
	}
}
