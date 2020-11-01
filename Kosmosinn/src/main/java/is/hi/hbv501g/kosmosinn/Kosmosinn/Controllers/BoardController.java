package is.hi.hbv501g.kosmosinn.Kosmosinn.Controllers;

import is.hi.hbv501g.kosmosinn.Kosmosinn.Entities.Board;
import is.hi.hbv501g.kosmosinn.Kosmosinn.Entities.Topic;
import is.hi.hbv501g.kosmosinn.Kosmosinn.Entities.User;
import is.hi.hbv501g.kosmosinn.Kosmosinn.Services.BoardService;
import is.hi.hbv501g.kosmosinn.Kosmosinn.Services.TopicService;
import is.hi.hbv501g.kosmosinn.Kosmosinn.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * This is the controller for the Boards of the project.
 * Boards are filled with topics.
 */
@Controller
@RequestMapping("/board/")
public class BoardController {
    private UserService userService;
    private TopicService topicService;
    private BoardService boardService;

    @Autowired
    public BoardController(UserService userService, TopicService topicService, BoardService boardService) {
        this.userService = userService;
        this.topicService = topicService;
        this.boardService = boardService;
    }

    /**
     * Function addBoardForm
     * Redirects you to the addboard site so you can fill in the form for a new Board.
     */
    @RequestMapping(value="addboard", method = RequestMethod.GET)
    public String addBoardForm(Board board) {
        return "add-board";
    }

    /**
     * Function addBoard
     * Creates a new baord through the boardService and redirects you to the home page.
     */
    @RequestMapping(value="addboard", method = RequestMethod.POST)
    public String addBoard(@Valid Board board, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-board";
        }
        boardService.save(board);
        return "redirect:/";
    }

    /**
     * Function viewBoard()
     * viewBoard recieves a PathVariable id, wich is the id number of a board.
     * It searches for said board id and adds it as an attribute to the model.
     * It sets the HttpSession's currentboardid to the corresponding id.
     * If it finds topics related to said board it fills the content of the board with said topics.
     * Returns you to the current viewed board's site.
     */
    @RequestMapping(value="{id}")
    public String viewBoard(@PathVariable("id") long id, Model model, HttpSession session) {
        model.addAttribute("board", boardService.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid ID")));
        session.setAttribute("currentboardid", id);

        if (topicService.findAllByBoardId(id) != null) {
            model.addAttribute("topics", topicService.findAllByBoardId(id));
        }
        return "board-content";
    };
}
