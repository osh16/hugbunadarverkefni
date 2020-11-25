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
    public String addBoardForm(Board board, HttpSession session) {
        User currentUser = (User) session.getAttribute("loggedinuser");
        boolean isAdmin = userService.isAdmin(currentUser);
        if (!isAdmin) {
            return "redirect:/";
        }
        return "add-board";
    }

    /**
     * Function addBoard
     * Creates a new baord through the boardService and redirects you to the home page.
     */
    @RequestMapping(value="addboard", method = RequestMethod.POST)
    public String addBoard(@Valid Board board, BindingResult result, Model model, HttpSession session) {
        User currentUser = (User) session.getAttribute("loggedinuser");
        boolean isAdmin = userService.isAdmin(currentUser);
        if (!isAdmin) {
            return "redirect:/";
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


    @RequestMapping(value="{id}/delete")
    public String deleteBoard(@PathVariable("id") long id, HttpSession session) {
        User currentUser = userService.findByUserame(((User) session.getAttribute("loggedinuser")).getUsername());
        boolean isAdmin = userService.isAdmin(currentUser);
        Board board =  boardService.findById(id).get();
        if (isAdmin) {
            boardService.delete(board);
        }
        return "redirect:/";
    }

    @RequestMapping(value="{id}/edit", method = RequestMethod.GET)
    public String editBoardForm(@PathVariable("id") long id, HttpSession session, Model model) {
        boolean isAdmin = userService.isAdmin(((User) session.getAttribute("loggedinuser")));
        if (!isAdmin) {
            return "redirect:/";
        }
        Board board = boardService.findById(id).get();
        model.addAttribute("board", board);
        return "edit-board";
    }

    @RequestMapping(value="{id}/edit", method = RequestMethod.POST)
    public String editBoard(@Valid Board board, @PathVariable("id") long id, HttpSession session, Model model) {
        boolean isAdmin = userService.isAdmin(((User) session.getAttribute("loggedinuser")));
        Board originalBoard = boardService.findById(id).get();
        if (isAdmin) {
            if (board.getName() != null) {
               originalBoard.setName(board.getName());
            }
            if (board.getDescription() != null) {
                originalBoard.setDescription(board.getDescription());
            }
            boardService.save(originalBoard);
        }
        return "redirect:/";
    }
}
