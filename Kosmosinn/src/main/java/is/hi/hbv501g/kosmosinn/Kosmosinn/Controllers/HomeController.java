package is.hi.hbv501g.kosmosinn.Kosmosinn.Controllers;

import is.hi.hbv501g.kosmosinn.Kosmosinn.Entities.User;
import is.hi.hbv501g.kosmosinn.Kosmosinn.Services.UserService;
import is.hi.hbv501g.kosmosinn.Kosmosinn.Entities.Topic;
import is.hi.hbv501g.kosmosinn.Kosmosinn.Services.TopicService;
import is.hi.hbv501g.kosmosinn.Kosmosinn.Entities.Board;
import is.hi.hbv501g.kosmosinn.Kosmosinn.Services.BoardService;
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
	private BoardService boardService;

	@Autowired
	public HomeController(UserService userService, TopicService topicService, BoardService boardService) {
		this.userService = userService;
		this.topicService = topicService;
		this.boardService = boardService;
	}

	@RequestMapping("/")
	public String Home(Model model) {
		model.addAttribute("users",userService.findAll());
		//model.addAttribute("topics",topicService.findAll());
		return "Welcome";
	}
}
