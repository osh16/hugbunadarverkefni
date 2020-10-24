package is.hi.hbv501g.kosmosinn.Kosmosinn.Controllers;

import is.hi.hbv501g.kosmosinn.Kosmosinn.Entities.Comment;
import is.hi.hbv501g.kosmosinn.Kosmosinn.Services.CommentService;
import is.hi.hbv501g.kosmosinn.Kosmosinn.Services.TopicService;
import is.hi.hbv501g.kosmosinn.Kosmosinn.Services.UserService;
import org.apache.catalina.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

public class CommentController {
    private HomeController homeController;
    private TopicService topicService;
    private UserService userService;
    private CommentService commentService;

    @Autowired
    public CommentController(UserService userService, TopicService topicService) {
        this.userService = userService;
        this.topicService = topicService;
    }

    @RequestMapping(value="createcomment", method = RequestMethod.POST)
    public String createComment(@Valid Comment comment, BindingResult result, Model model) {
        if (result.hasErrors()) {
            System.out.println("createcomment post error");
            return "topic-content";
        }
        commentService.save(comment);
        model.addAttribute("comments", commentService.findAll());
        //model.addAttribute("users", userService.findAll());
        //model.addAttribute("topics",topicService.findAll());
        System.out.println("createcomment post");

        return "redirect:/";
    }

    @RequestMapping(value="createcomment", method = RequestMethod.GET)
    public String createCommentForm(Comment comment) {
        System.out.println("createcomment get");
        return "topic-content";
    }

}
