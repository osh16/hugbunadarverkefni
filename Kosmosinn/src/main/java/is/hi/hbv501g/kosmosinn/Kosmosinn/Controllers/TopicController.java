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

import javax.naming.Binding;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

/**
 * This controller handles all topics. Topics are how users interact with certain boards.
 * A text object that is the starting point of a "conversation" with comments.
 */
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

    /**
     * Function createTopicForm directs you to a page where you 
     * can create a new Topic using a Form.
     */
    @RequestMapping(value="createtopic", method = RequestMethod.GET)
    public String createTopicForm(Topic topic) {
        //model.addAttribute()
        return "add-topic";
    }

    /**
     * Function createTopic takes the input from the form in the function above
     * and translates it redirectsto a Topic object and  you back to the original board.
     */
    @RequestMapping(value="createtopic", method = RequestMethod.POST)
    public String createTopic(@Valid Topic topic, BindingResult result, Model model, HttpSession session) {
        if (result.hasErrors()) {
            return "add-topic";
        }
        model.addAttribute("topics", topicService.findAll());
        model.addAttribute("users", userService.findAll());
        User sessionUser = (User) session.getAttribute("loggedinuser");
        Board board = boardService.findById((long) session.getAttribute("currentboardid")).get();
        topic.setUser(sessionUser);
        topic.setBoard(board);
        topicService.save(topic);
        Board currentBoard = topic.getBoard();
        return "redirect:/board/" + currentBoard.getId();

    }

    /**
     * Function deleteTopic reads the PathVariable wich corresponds to a topics ID.
     * It searches the database for this topic and utilizing the TopicService it deletes it.
     * The function redirects you to the.
     */
    @RequestMapping(value="deletetopic/{id}", method = RequestMethod.GET)
    public String deleteTopic(@PathVariable("id") long id, Model model) {
        Topic topic = topicService.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid ID"));
        topicService.delete(topic);
        model.addAttribute("topics", topicService.findAll());
        Comment comment = new Comment();
        model.addAttribute("comment", comment);
        return "redirect:/";
    }

    /**
     * Function viewTopicContent, reads the PathVariable id of the topic to view.
     * Returns the content of said topic to the page.
     */
    @RequestMapping(value="{id}", method = RequestMethod.GET)
    public String viewTopicContent(@PathVariable("id") long id, Model model, HttpSession session) {
        session.setAttribute("currenttopicid", id);
        model.addAttribute("topic", topicService.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid ID")));
        if (commentService.findAllByTopicId(id) != null) {
            model.addAttribute("comments", commentService.findAllByTopicId(id));
        }
        model.addAttribute("comment", new Comment());

        return "topic-content";
    }

    /**
     * Function addCommentToTopic, reads the PathVariable id of the current topic and 
     * the input of the "Comment" form beneath the topic. The function sets the current user as an owner of the topic,
     * it links the comment to the current topic and saves the Comment using the CommentService.
     * It then redirects you again to the current site you have been viewing now with the new comment.
     */
    @RequestMapping(value = "{id}", method = RequestMethod.POST)
    public String addCommentToTopic(@Valid Comment comment, @PathVariable("id") long id, BindingResult result, Model model, HttpSession session) {
        User currentUser = (User) session.getAttribute("loggedin");
        Topic currentTopic = (Topic) topicService.findById((long) session.getAttribute("currenttopicid")).get();
        comment.setUser(currentUser);
        comment.setTopic(currentTopic);
        commentService.save(comment);
        return "redirect:/topic/" + session.getAttribute("currenttopicid");

    }
}
