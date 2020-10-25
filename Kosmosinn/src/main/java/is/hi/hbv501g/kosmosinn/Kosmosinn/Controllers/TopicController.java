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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/topic/")
public class TopicController {
    private TopicService topicService;
    private UserService userService;
    private BoardService boardService;

    @Autowired
    public TopicController(UserService userService, TopicService topicService, BoardService boardService) {
        this.userService = userService;
        this.topicService = topicService;
        this.boardService = boardService;
    }

    @RequestMapping(value="createtopic", method = RequestMethod.GET)
    public String createTopicForm(Topic topic) {
        //model.addAttribute()
        return "add-topic";
    }
    @RequestMapping(value="createtopic", method = RequestMethod.POST)
    public String createTopic(@Valid Topic topic, BindingResult result, Model model, HttpSession session) {
        if (result.hasErrors()) {
            return "add-topic";
        }
        model.addAttribute("topics", topicService.findAll());
        model.addAttribute("users", userService.findAll());
        User sessionUser = (User) session.getAttribute("loggedinuser");


        System.out.println("================================");
        //System.out.println(board);
        //System.out.println(board.getId());
        //System.out.println(board.getName());
        //System.out.println(board.getDescription());
        //System.out.println("================================");
        //Optional<Board> board = boardService.findById((long) session.getAttribute("currentboardid"));
        topic.setUser(sessionUser);
        //topic.setBoard(boardService.findById(board.getId()).get());
        topic.setBoard(boardService.findById(1).get());
        topicService.save(topic);
        return "redirect:/board/" + topic.getBoard().getId();

    }

    @RequestMapping(value="{id}", method = RequestMethod.GET)
    public String viewTopicContent(@PathVariable("id") long id, Model model) {
        System.out.println("view topic");
        model.addAttribute("topic", topicService.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid ID")));
        return "topic-content";
    }



    @RequestMapping(value="deletetopic/{id}", method = RequestMethod.GET)
    public String deleteTopic(@PathVariable("id") long id, Model model) {
        Topic topic = topicService.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid ID"));
        topicService.delete(topic);
        model.addAttribute("topics", topicService.findAll());
        return "redirect:/";
    }
}
