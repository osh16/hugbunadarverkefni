package is.hi.hbv501g.kosmosinn.Kosmosinn.Entities;

import javax.persistence.*;

@Entity
public class Topic{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String topicName;
    private int topicPoints;
    private String topicContent;

    public Topic() {
    }

    public Topic(String topicName, int topicPoints, String topicContent) {
        this.topicName = topicName;
        this.topicPoints = topicPoints;
        this.topicContent = topicContent;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public int getTopicPoints() {
        return topicPoints;
    }

    public void setTopicPoints(int topicPoints) {
        this.topicPoints = topicPoints;
    }

    public String getTopicContent() {
        return topicContent;
    }

    public void setTopicContent(String topicContent) {
        this.topicContent = topicContent;
    }
}