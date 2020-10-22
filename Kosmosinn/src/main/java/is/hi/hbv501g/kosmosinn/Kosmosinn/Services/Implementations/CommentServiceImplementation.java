package is.hi.hbv501g.kosmosinn.Kosmosinn.Services.Implementations;
import is.hi.hbv501g.kosmosinn.Kosmosinn.Entities.Comment;
import is.hi.hbv501g.kosmosinn.Kosmosinn.Repositories.CommentRepository;
import is.hi.hbv501g.kosmosinn.Kosmosinn.Services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImplementation implements CommentService {
    CommentRepository repository;

    @Autowired
    public CommentServiceImplementation(CommentRepository commentRepository) {
        this.repository = commentRepository;
    }

    @Override
    public Comment save(Comment comment) {
        return repository.save(comment);
    }

    @Override
    public void delete(Comment comment) {
        repository.delete(comment);
    }

    @Override
    public List<Comment> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Comment> findById(long id) {
        return repository.findById(id);
    }
}