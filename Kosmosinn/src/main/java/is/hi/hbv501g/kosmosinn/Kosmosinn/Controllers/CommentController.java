package is.hi.hbv501g.kosmosinn.Kosmosinn.Controllers;

import is.hi.hbv501g.kosmosinn.Kosmosinn.Entities.Comment;
import is.hi.hbv501g.kosmosinn.Kosmosinn.Services.CommentService;
import is.hi.hbv501g.kosmosinn.Kosmosinn.Entities.Topic;
import is.hi.hbv501g.kosmosinn.Kosmosinn.Services.TopicService;
import is.hi.hbv501g.kosmosinn.Kosmosinn.Entities.User;
import is.hi.hbv501g.kosmosinn.Kosmosinn.Services.UserService;
import org.apache.catalina.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

public class CommentController {
    private HomeController homeController;
    private TopicService topicService;
    private UserService userService;
    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService, UserService userService, TopicService topicService) {
        this.commentService = commentService;
        this.userService = userService;
        this.topicService = topicService;
    }

    @RequestMapping(value="@{createcomment}", method = RequestMethod.POST)
    public String createComment(@Valid Comment comment, BindingResult result, Model model, long id, HttpSession session) {
        if (result.hasErrors()) {
            System.out.println("createcomment post error");
            return "redirect:/topic/{id}(id=${topic.id})}";
        }
        model.addAttribute("topic", topicService.findAll());
        model.addAttribute("users", userService.findAll());
        User sessionUser = (User) session.getAttribute("loggedinuser");
        Topic topic = topicService.findById((long) session.getAttribute("currenttopicid")).get();
        commentService.save(comment);
        //model.addAttribute("comments", commentService.findAll());
        //model.addAttribute("users", userService.findAll());
        //model.addAttribute("topics",topicService.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid id")));

        System.out.println("createcomment post");
        Topic currentTopic = comment.getTopic();
        return "redirect:/topic/" + currentTopic.getId();
    }

    @RequestMapping(value="@{createcomment}", method = RequestMethod.GET)
    public String createCommentForm(Comment comment) {
        System.out.println("createcomment get");
        return "topic-content";
    }

}
