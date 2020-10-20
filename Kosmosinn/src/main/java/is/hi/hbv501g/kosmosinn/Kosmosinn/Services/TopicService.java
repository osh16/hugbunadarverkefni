package is.hi.hbv501g.kosmosinn.Kosmosinn.Services;
import is.hi.hbv501g.kosmosinn.Kosmosinn.Entities.Topic;

import java.util.List;
import java.util.Optional;

public interface TopicService {
    Topic save(Topic topic);
    void delete(Topic topic);
    List<Topic> findAll();
    Optional<Topic> findById(long id);
}