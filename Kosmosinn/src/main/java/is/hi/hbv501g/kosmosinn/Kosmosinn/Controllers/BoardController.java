package is.hi.hbv501g.kosmosinn.Kosmosinn.Controllers;

import is.hi.hbv501g.kosmosinn.Kosmosinn.Entities.Board;
import is.hi.hbv501g.kosmosinn.Kosmosinn.Entities.Topic;
import is.hi.hbv501g.kosmosinn.Kosmosinn.Services.BoardService;
import is.hi.hbv501g.kosmosinn.Kosmosinn.Services.TopicService;
import is.hi.hbv501g.kosmosinn.Kosmosinn.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
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

    @RequestMapping(value="/addboard", method = RequestMethod.GET)
    public String createTopicForm(Topic topic) {
        return "add-board";
    }

    @RequestMapping(value="/addboard", method = RequestMethod.POST)
    public String addUser(@Valid Board board, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-board";
        }
        boardService.save(board);
        return "redirect:/";
    }
}
