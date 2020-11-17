package is.hi.hbv501g.kosmosinn.Kosmosinn.Controllers;

import is.hi.hbv501g.kosmosinn.Kosmosinn.Entities.Board;
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

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    private TopicService topicService;
    private UserService userService;
    private BoardService boardService;

    @Autowired
    public UserController(UserService userService, TopicService topicService, BoardService boardService) {
        this.boardService = boardService;
        this.userService = userService;
        this.topicService = topicService;
    }

    @RequestMapping(value="/userlist", method = RequestMethod.POST)
    public String getUserList(@Valid User user, BindingResult result, Model model, HttpSession session) {
        User currentUser = (User) session.getAttribute("loggedinuser");
        boolean isAdmin = userService.isAdmin(currentUser);
        if (!isAdmin) {
            return "redirect:/";
        }
        model.addAttribute("users", userService.findAll());
        return "user-list";

    }
    @RequestMapping(value="/adduser", method = RequestMethod.POST)
    public String addUser(@Valid User user, BindingResult result, Model model, HttpSession session) {
        User currentUser = (User) session.getAttribute("loggedinuser");
        boolean isAdmin = userService.isAdmin(currentUser);
        if (!isAdmin) {
            return "redirect:/";
        }
        userService.save(user);
        return "redirect:/adduser";
    }

    @RequestMapping(value="/adduser", method = RequestMethod.GET)
    public String addUserForm(User user, HttpSession session) {
        User currentUser = (User) session.getAttribute("loggedinuser");
        boolean isAdmin = userService.isAdmin(currentUser);
        if (!isAdmin) {
            return "redirect:/";
        }
        return "add-user";

    }

    @RequestMapping(value="/deleteuser/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable("id") long id, Model model, HttpSession session) {
        User currentUser = (User) session.getAttribute("loggedinuser");
        boolean isAdmin = userService.isAdmin(currentUser);
        if (isAdmin) {
            User user = userService.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid ID"));
            userService.delete(user);
            model.addAttribute("users", userService.findAll());
        }
        return "redirect:/";
    }

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String loginForm(User user) {
        return "login";
    }

    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String login(@Valid User user, BindingResult result, Model model, HttpSession session) {
        List<String> errors = new ArrayList<>();
        User exists = userService.findByUserame(user.getUsername());
        if (exists != null) {
            if (user.getPassword().equals(exists.getPassword())) {
                session.setAttribute("loggedinuser", user);
                if (userService.isAdmin(user)) {
                    session.setAttribute("loggedinadmin", user);
                }
                return "redirect:/";
            } else {
                errors.add("Incorrect password");
                model.addAttribute("errors",errors);
            }
        } else {
            errors.add("User not found");
            model.addAttribute("errors",errors);
        }
        return "login";
    }

    @RequestMapping(value="/signout", method=RequestMethod.GET)
    public String logout(@Valid User user, BindingResult result, Model model, HttpSession session) {
        User currentUser = (User) session.getAttribute("loggedinuser");
        if (currentUser != null) {
            session.removeAttribute( "loggedinuser");
            session.removeAttribute("loggedinadmin");
        }
        return "redirect:/";
    }

    @RequestMapping(value="/signup", method = RequestMethod.GET)
    public String signupForm(User user) {
        return "signup";
    }

    @RequestMapping(value="/signup", method = RequestMethod.POST)
    public String signup(@Valid User user, BindingResult result, Model model, HttpSession session) {
        List<String> errors = new ArrayList<>();
        // getum baett inn fleiri villum med errors.add("....") osfrv
        if (userService.findByUserame(user.getUsername()) != null) {
            System.out.println("Username already exists");
            errors.add("Username already exists");
        }

        if (errors.size() == 0) {
            session.setAttribute("loggedinuser", user);
            userService.save(user);
            return "redirect:/";
        } else {
            System.out.println("errors");
            model.addAttribute("errors", errors);
            return "signup";
        }
    }
}

