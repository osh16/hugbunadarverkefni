package is.hi.hbv501g.kosmosinn.Kosmosinn.Entities;

import java.util.List;
import javax.persistence.*;

@Entity
public class Board{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String boardname;
    private int boardFollowers;
    private String boardInformation;
    private List<Topic> boardTopics;

    public Board() {
    }

    public Board(String boardname, int boardFollowers, String boardInformation, List<Topic> boardTopics) {
        this.boardname = boardname;
        this.boardFollowers = boardFollowers;
        this.boardInformation = boardInformation;
        this.boardTopics = boardTopics;
    }

    public long getId() {
        return id;
    }

    public String getBoardName() {
        return boardname;
    }

    public void setBoardName(String boardname) {
        this.boardname = boardname;
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