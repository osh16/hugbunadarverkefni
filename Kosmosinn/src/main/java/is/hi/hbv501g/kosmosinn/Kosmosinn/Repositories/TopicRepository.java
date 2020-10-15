package is.hi.hbv501g.kosmosinn.Kosmosinn.Repositories;
import is.hi.hbv501g.kosmosinn.Kosmosinn.Entities.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    Topic save(Topic topic);
    void delete(Topic topic);
    List<Topic> findAll();
}