package is.hi.hbv501g.kosmosinn.Kosmosinn.Entities;

import javax.persistence.*;

@Entity
public class Comment{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String commentText;

    public Comment() {

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

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }
}