package is.hi.hbv501g.kosmosinn.Kosmosinn.Services.Implementations;
import is.hi.hbv501g.kosmosinn.Kosmosinn.Entities.Topic;
import is.hi.hbv501g.kosmosinn.Kosmosinn.Repositories.TopicRepository;
import is.hi.hbv501g.kosmosinn.Kosmosinn.Services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}