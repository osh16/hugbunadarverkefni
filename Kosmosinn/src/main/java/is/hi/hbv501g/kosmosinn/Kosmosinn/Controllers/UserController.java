package is.hi.hbv501g.kosmosinn.Kosmosinn.Controllers;

import is.hi.hbv501g.kosmosinn.Kosmosinn.Entities.User;
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

@Controller
public class UserController {
    private TopicService topicService;
    private UserService userService;

    @Autowired
    public UserController(UserService userService, TopicService topicService) {
        this.userService = userService;
        this.topicService = topicService;
    }

    @RequestMapping(value="/adduser", method = RequestMethod.POST)
    public String addUser(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            System.out.println("adduser post error");
            return "add-user";
        }
        userService.save(user);
        model.addAttribute("users", userService.findAll());
        model.addAttribute("topics", topicService.findAll());
        System.out.println("adduser post");
        return "Velkominn";
    }

    @RequestMapping(value="/adduser", method = RequestMethod.GET)
    public String addUserForm(User user) {
        System.out.println("adduser get");
        return "add-user";
    }

    @RequestMapping(value="/deleteuser/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable("id") long id, Model model) {
        User user = userService.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid ID"));
        userService.delete(user);
        model.addAttribute("users", userService.findAll());
        return "redirect:/";
    }

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String loginForm(User user) {
        return "login";
    }

    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String login(@Valid User user, BindingResult result, Model model, HttpSession session) {
        if (result.hasErrors()) {
            System.out.println("error login");
            return "login";
        }
        User exists = userService.login(user);
        if (exists != null) {
            session.setAttribute("loggedinuser", user);
            return "redirect:/";
        }
        return "redirect:/";
    }
}