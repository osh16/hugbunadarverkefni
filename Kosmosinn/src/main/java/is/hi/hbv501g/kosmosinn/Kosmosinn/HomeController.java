package is.hi.hbv501g.kosmosinn.Kosmosinn;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String Home() {
		return "Velkominn";
	}

	@RequestMapping("login") 
	public String Login() {
		return "Login";
	}
}
