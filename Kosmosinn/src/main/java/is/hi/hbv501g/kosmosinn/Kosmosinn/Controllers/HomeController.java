package is.hi.hbv501g.kosmosinn.Kosmosinn.Controllers;

import is.hi.hbv501g.kosmosinn.Kosmosinn.Entities.User;
import is.hi.hbv501g.kosmosinn.Kosmosinn.Services.UserService;
import is.hi.hbv501g.kosmosinn.Kosmosinn.Entities.Topic;
import is.hi.hbv501g.kosmosinn.Kosmosinn.Services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.validation.Valid;


@Controller
public class HomeController {

	private UserService userService;
	private TopicService topicService;

	@Autowired
	public HomeController(UserService userService, TopicService topicService) {
		this.userService = userService;
		this.topicService = topicService;
	}

	@RequestMapping("/")
	public String Home(Model model) {
		model.addAttribute("users",userService.findAll());
		//model.addAttribute("topics",topicService.findAll());
		return "Velkominn";
	}

	//
	// USER DRASL
	//

	@RequestMapping(value="/adduser", method = RequestMethod.POST)
    public String addUser(@Valid User user, BindingResult result, Model model) {
		if (result.hasErrors()) {
		    System.out.println("adduser post error");
			return "add-user";
		}
		userService.save(user);
		model.addAttribute("users", userService.findAll());
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
		return "Velkominn";
	}

	@RequestMapping("login") 
	public String Login() {
		return "Login";
	}


	//
	// TOPIC DRASL
	//

	@RequestMapping(value="/createtopic", method = RequestMethod.POST)
	public String addUser(@Valid Topic topic, BindingResult result, Model model) {
		if (result.hasErrors()) {
			System.out.println("createtopic post error");
			return "create-topic";
		}
		topicService.save(topic);
		model.addAttribute("topics", topicService.findAll());
		System.out.println("createtopic post");
		return "Velkominn";
	}

	@RequestMapping(value="/createtopic", method = RequestMethod.GET)
	public String createTopicForm(Topic topic) {
		System.out.println("createtopic get");
		return "create-topic";
	}

	@RequestMapping(value="/topic", method = RequestMethod.GET)
	public String viewTopicContent(Topic topic) {
		System.out.println("view topic");
		return "topic-content";
	}

	@RequestMapping(value="/deletetopic/{id}", method = RequestMethod.GET)
	public String deleteTopic(@PathVariable("id") long id, Model model) {
		Topic topic = topicService.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid ID"));
		topicService.delete(topic);
		model.addAttribute("topics", topicService.findAll());
		return "Velkominn";
	}


}
