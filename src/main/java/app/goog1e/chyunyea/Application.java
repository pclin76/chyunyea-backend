package app.goog1e.chyunyea;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

// We use direct @Import instead of @ComponentScan to speed up cold starts
@Import({
	app.goog1e.chyunyea.controller.WelcomeController.class
})
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(
			Application.class,
			args
		);
	}
}
