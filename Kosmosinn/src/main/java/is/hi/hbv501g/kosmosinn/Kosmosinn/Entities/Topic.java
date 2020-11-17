package is.hi.hbv501g.kosmosinn.Kosmosinn.Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Topic Entity, an entity for the many topics of Kosmosinn.
 * A Topic has an id (long), an ArrayList of Comments (Comment Entities),
 * a designated Board (Board Entitiy), a designated User (User Entity), 
 * a topicName (String), a topicContent (String) and topicPoints (int) currently not implemented fully.
 */
@Entity
public class Topic{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "topic", orphanRemoval = true, cascade = CascadeType.ALL)
    //private List<Comment> comments = new ArrayList<>();
    private List<Long> commentIDs = new ArrayList<>();

    @ManyToOne
    //private Board board;
    private long boardID;

    @ManyToOne()
    //private User user;
    private long userID;

    @Column(nullable = false)
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

    public long getId() {
        return id;
    }
    public String getTopicName() {
        return topicName;
    }
    public int getTopicPoints() {
        return topicPoints;
    }
    public String getTopicContent() {
        return topicContent;
    }

    //public List<Comment> getComments() { return comments; }
    public List<Long> getCommentIDs() { return commentIDs; }
    //public Board getBoard() { return board; }
    public long getBoardID() { return boardID; }
    //public User getUser() { return user; }
    public long getUserID() { return userID; }
    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }
    public void setTopicPoints(int topicPoints) {
        this.topicPoints = topicPoints;
    }
    public void setTopicContent(String topicContent) {
        this.topicContent = topicContent;
    }
    //public void setComments(List<Comment> comments) { this.comments = comments; }
    //public void setBoard(Board board) { this.board = board; }
    //public void setUser(User user) { this.user = user; }

    public void setCommentIDs(List<Long> commentIDs) {
        this.commentIDs = commentIDs;
    }

    public void setBoardID(long boardID) {
        this.boardID = boardID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }
}