package is.hi.hbv501g.kosmosinn.Kosmosinn.Controllers;

import is.hi.hbv501g.kosmosinn.Kosmosinn.Entities.Comment;
import is.hi.hbv501g.kosmosinn.Kosmosinn.Entities.Topic;
import is.hi.hbv501g.kosmosinn.Kosmosinn.Services.CommentService;
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

@Controller
public class TopicController {
    private HomeController homeController;
    private TopicService topicService;
    private UserService userService;
    private CommentService commentService;

    @Autowired
    public TopicController(UserService userService, TopicService topicService, CommentService commentService) {
        this.userService = userService;
        this.topicService = topicService;
        this.commentService = commentService;
    }

    @RequestMapping(value="createtopic", method = RequestMethod.POST)
    public String createTopic(@Valid Topic topic, BindingResult result, Model model) {
        if (result.hasErrors()) {
            System.out.println("createtopic post error");
            return "create-topic";
        }
        topicService.save(topic);
        model.addAttribute("topics", topicService.findAll());
        model.addAttribute("users", userService.findAll());
        System.out.println("createtopic post");

        return "redirect:/";
    }

    @RequestMapping(value="createtopic", method = RequestMethod.GET)
    public String createTopicForm(Topic topic) {
        System.out.println("createtopic get");
        return "create-topic";
    }

    @RequestMapping(value="/topic", method = RequestMethod.GET)
    public String viewTopicContent(Topic topic, Model model) {
        System.out.println("view topic");
        model.addAttribute("topics", topicService.findAll());
        return "topic-content";
    }

    @RequestMapping(value="/topic/{id}", method = RequestMethod.GET)
    public String viewTopicContent(@PathVariable("id") long id, Model model) {
        System.out.println("view topic");
        /*Comment comment = new Comment("halló");
        commentService.save(comment);
        System.out.println(commentService.findAll());*/
        
        model.addAttribute("comment", new Comment());
        //Topic topic = topicService.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid ID"));
        model.addAttribute("topic", topicService.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid ID")));
        model.addAttribute("comments", commentService.findAll());
        return "topic-content";
    }

    @RequestMapping(value="/deletetopic/{id}", method = RequestMethod.GET)
    public String deleteTopic(@PathVariable("id") long id, Model model) {
        Topic topic = topicService.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid ID"));
        topicService.delete(topic);
        model.addAttribute("topics", topicService.findAll());
        return "redirect:/";
    }
}
