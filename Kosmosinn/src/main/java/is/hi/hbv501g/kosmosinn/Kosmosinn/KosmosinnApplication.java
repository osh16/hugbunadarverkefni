package is.hi.hbv501g.kosmosinn.Kosmosinn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class KosmosinnApplication{

	public static void main(String[] args) {
		SpringApplication.run(KosmosinnApplication.class, args);
	}
}
