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

import javax.validation.Valid;
import java.util.List;

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

    @RequestMapping(value="addboard", method = RequestMethod.GET)
    public String addBoardForm(Board board) {
        return "add-board";
    }

    @RequestMapping(value="addboard", method = RequestMethod.POST)
    public String addBoard(@Valid Board board, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-board";
        }
        boardService.save(board);
        return "redirect:/";
    }

    @RequestMapping(value="{id}")
    public String viewBoard(@PathVariable("id") long id, Model model) {
        System.out.println(id);
        model.addAttribute("board", boardService.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid ID")));
        if (topicService.findAllByBoardId(id) != null) {
            model.addAttribute("topics", topicService.findAllByBoardId(id));
        }
        return "board-content";
    };
}
