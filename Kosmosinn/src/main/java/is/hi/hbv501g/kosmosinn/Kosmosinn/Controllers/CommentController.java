package is.hi.hbv501g.kosmosinn.Kosmosinn.Controllers;

import is.hi.hbv501g.kosmosinn.Kosmosinn.Entities.Comment;
import is.hi.hbv501g.kosmosinn.Kosmosinn.Entities.Topic;
import is.hi.hbv501g.kosmosinn.Kosmosinn.Entities.User;
import is.hi.hbv501g.kosmosinn.Kosmosinn.Services.CommentService;
import is.hi.hbv501g.kosmosinn.Kosmosinn.Services.TopicService;
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

/**
* This was supposed to be the controller for comments,
* we decided to use the controller for topics for that matter since the comments are closely linked to topics.
*/
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

}
