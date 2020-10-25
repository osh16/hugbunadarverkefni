package is.hi.hbv501g.kosmosinn.Kosmosinn.Services.Implementations;
import is.hi.hbv501g.kosmosinn.Kosmosinn.Entities.Board;
import is.hi.hbv501g.kosmosinn.Kosmosinn.Entities.Topic;
import is.hi.hbv501g.kosmosinn.Kosmosinn.Entities.User;
import is.hi.hbv501g.kosmosinn.Kosmosinn.Repositories.TopicRepository;
import is.hi.hbv501g.kosmosinn.Kosmosinn.Services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class TopicServiceImplementation implements TopicService {
    TopicRepository repository;

    @Autowired
    public TopicServiceImplementation(TopicRepository topicRepository) {
        this.repository = topicRepository;
    }

    @Override
    public Topic save(Topic topic) {
        return repository.save(topic);
    }

    @Override
    public void delete(Topic topic) {
        repository.delete(topic);
    }

    @Override
    public List<Topic> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Topic> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public Board findByBoard(long id) {
        return repository.findByBoard(id);
    }

    @Override
    public User findByUser(long id) {
        return repository.findByUser(id);
    }

    @Override
    public List<Topic> findAllByBoardId(long id) {
        Board board = repository.findByBoard(id);
        List<Topic> topics = repository.findAll();
        List<Topic> topicsByBoardId = new ArrayList<>();

        for (int i = 0; i < topics.size(); i++) {
            if (topics.get(i).getBoard() == board) {
                topicsByBoardId.add(topics.get(i));
            }
        }
        return topicsByBoardId;
    }
}