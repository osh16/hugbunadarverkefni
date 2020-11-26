package is.hi.hbv501g.kosmosinn.Kosmosinn.Controllers;

import is.hi.hbv501g.kosmosinn.Kosmosinn.Entities.Comment;
import is.hi.hbv501g.kosmosinn.Kosmosinn.Entities.User;
import is.hi.hbv501g.kosmosinn.Kosmosinn.Services.BoardService;
import is.hi.hbv501g.kosmosinn.Kosmosinn.Services.CommentService;
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

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.Instant;
import java.util.Date;

/**
 * This is the controller for the Home page.
 * The page shows you a login and signup buttons,
 * a user list and a button to add new users manually,
 * a list of all boards and, IF you are logged in, a button to add new boards.
 */
@Controller
public class HomeController {

	private UserService userService;
	private TopicService topicService;
	private BoardService boardService;
	private CommentService commentService;

	@Autowired
	public HomeController(UserService userService, TopicService topicService, BoardService boardService, CommentService commentService) {
		this.userService = userService;
		this.topicService = topicService;
		this.boardService = boardService;
		this.commentService = commentService;
	}

	@RequestMapping("/")
	public String Home(Model model) {
	    // fyrsta keyrsla
	    if (userService.findByUserame("oskar") == null) {
	    	User oskar = new User("oskar","oskar","ADMIN");
			userService.save(oskar);

			boardService.save(new Board("Fréttir af Matt Damon", "Matthew Paige Damon (f. 8. október 1970), best þekktur sem Matt Damon, er bandarískur leikari og handritshöfundur. Hann er af ensku, skosku, sænsku og finnsku ætterni. Hann stundaði enskunám í Harvard-háskóla á árunum 1988 - 1992 en útskrifaðist ekki. Hann og Ben Affleck eru æskuvinir."));
			boardService.save(new Board("Íslensk Tónlist", "Eigum við eitthvað að ræða nýju plötuna hans Bubba? Halló!"));
			boardService.save(new Board("Rafmyntir", "Hver er næsta 1000x"));
			boardService.save(new Board("hacker_board", "here_we_are_the_hackers_of_the_world_hue_hue_hue"));
		}
		model.addAttribute("boards", boardService.findAll());
		return "welcome";
	}
}
