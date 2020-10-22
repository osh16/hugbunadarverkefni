package is.hi.hbv501g.kosmosinn.Kosmosinn.Services;
import is.hi.hbv501g.kosmosinn.Kosmosinn.Entities.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    Comment save(Comment comment);
    void delete(Comment comment);
    List<Comment> findAll();
    Optional<Comment> findById(long id);
}