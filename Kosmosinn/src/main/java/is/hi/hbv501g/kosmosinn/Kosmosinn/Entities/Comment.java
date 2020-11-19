package is.hi.hbv501g.kosmosinn.Kosmosinn.Entities;

import javax.persistence.*;

/**
 * Comment Entity, an entity for the many comments of Kosmosinn.
 * A Comment has an id (long), a designated user (User Entity), a designated Topic (Topic Entity)
 * and comment text (String).
 */
@Entity
public class Comment{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Topic topic;

    private String commentText;
    private Date commentDate;

    public Comment() {
    }

    public Comment(User user) {
        this.user = user;
    }
    public Comment(String commentText) {
        this.commentText = commentText;
    }

    public long getId() {
        return id;
    }

    public String getCommentText() {
        return commentText;
    }

    public User getUser() {
        return user;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }
}