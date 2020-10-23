package is.hi.hbv501g.kosmosinn.Kosmosinn.Entities;

import javax.persistence.*;

@Entity
public class Board{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String boardName;
    private int boardFollowers;
    private String boardInformation;
    private List<Topic> boardTopics;

    public Board() {
    }

    public Board(String boardName, int boardFollowers, String boardInformation, List<Topic> boardTopics) {
        this.boardName = boardName;
        this.boardFollowers = boardFollowers;
        this.boardInformation = boardInformation;
        this.boardTopics = boardTopics;
    }

    public long getId() {
        return id;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public int getBoardFollowers() {
        return boardFollowers;
    }

    public void setBoardFollowers(int boardFollowers) {
        this.boardFollowers = boardFollowers;
    }

    public String getBoardInformation() {
        return boardInformation;
    }

    public void setBoardInformation(String boardInformation) {
        this.boardInformation = boardInformation;
    }

    public List<Topic> getBoardTopics() {
        return boardTopics;
    }
    
    public void setBoardTopics(List<Topic> boardTopics) {
        this.boardTopics = boardTopics;
    }
}