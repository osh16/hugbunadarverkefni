package is.hi.hbv501g.kosmosinn.Kosmosinn.Controllers;

import is.hi.hbv501g.kosmosinn.Kosmosinn.Entities.Comment;
import is.hi.hbv501g.kosmosinn.Kosmosinn.Entities.Topic;
import is.hi.hbv501g.kosmosinn.Kosmosinn.Services.CommentService;
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

import javax.jws.soap.SOAPBinding;
import javax.naming.Binding;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/topic/")
public class TopicController {
    private TopicService topicService;
    private UserService userService;
    private BoardService boardService;
    private CommentService commentService;

    @Autowired
    public TopicController(UserService userService, TopicService topicService, BoardService boardService, CommentService commentService) {
        this.userService = userService;
        this.topicService = topicService;
        this.boardService = boardService;
        this.commentService = commentService;
    }

    @RequestMapping(value="createtopic", method = RequestMethod.GET)
    public String createTopicForm(Topic topic, HttpSession session) {
        String currentBoardId = (String) session.getAttribute("currentboardid");
        User currentUser = (User) session.getAttribute("loggedinuser");
        if (currentUser.equals(null)) {
            if (currentBoardId.equals(null)) {
                return "redirect:/";
            }
            return "redirect:/board" + currentBoardId;
        }
        return "add-topic";
    }
    @RequestMapping(value="createtopic", method = RequestMethod.POST)
    public String createTopic(@Valid Topic topic, BindingResult result, Model model, HttpSession session) {
        String currentBoardId = (String) session.getAttribute("currentboardid");
        User currentUser = (User) session.getAttribute("loggedinuser");
        if (currentUser.equals(null)) {
            if (currentBoardId.equals(null)) {
                return "redirect:/";
            }
            return "redirect:/board" + currentBoardId;
        }

        topic.setBoard((Board) boardService.findById(Long.parseLong(currentBoardId)).get());
        topic.setUser((User) userService.findByUserame(currentUser.getUsername()));
        topicService.save(topic);
        return "redirect:/board" + currentBoardId;
    }

    @RequestMapping(value="deletetopic/{id}", method = RequestMethod.GET)
    public String deleteTopic(@PathVariable("id") long id, Model model, HttpSession session) {
        Topic topic = topicService.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid ID"));
        User topicCreator = userService.findByUserame(topic.getUser().getUsername());
        User currentUser = (User) session.getAttribute("loggedinuser");
        boolean isAdmin = userService.isAdmin(currentUser);
        String currentBoardId = (String) session.getAttribute("currentboardid");

        if (currentUser.equals(topicCreator) || isAdmin) {
            topicService.delete(topic);
        }
        return "redirect:/board/" + currentBoardId;
    }

    @RequestMapping(value="{id}", method = RequestMethod.GET)
    public String viewTopicContent(@PathVariable("id") long id, Model model, HttpSession session) {
        User currentUser = (User) session.getAttribute("loggedinuser");
        Topic currentTopic = topicService.findById(id).get();
        if (currentTopic.equals(null)) {
            return "redirect:/";
        }

        session.setAttribute("currenttopicid", id);
        model.addAttribute("topic", topicService.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid ID")));

        if (!commentService.findAllByTopicId(id).equals(null)) {
            model.addAttribute("comments", commentService.findAllByTopicId(id));
        }

        // Only logged in users can comment
        if (currentUser.equals(null)) {
            model.addAttribute("comment", new Comment());
        }

        return "topic-content";
    }

    @RequestMapping(value = "{id}", method = RequestMethod.POST)
    public String addCommentToTopic(@Valid Comment comment, @PathVariable("id") long id, BindingResult result, Model model, HttpSession session) {
        User sessionUser = (User) session.getAttribute("loggedinuser");
        if (sessionUser.equals(null)) {
            return "redirect:/topic/" + id;
        }
        Topic currentTopic = (Topic) topicService.findById((long) session.getAttribute("currenttopicid")).get();
        comment.setUser(sessionUser);
        comment.setTopic(currentTopic);
        commentService.save(comment);
        return "redirect:/topic/" + id;

    }
}
