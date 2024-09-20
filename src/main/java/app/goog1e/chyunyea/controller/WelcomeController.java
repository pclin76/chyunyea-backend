package app.goog1e.chyunyea.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.HashMap;
import java.util.Map;

@EnableWebMvc
@RequestMapping("/")
@RestController
@Slf4j
public class WelcomeController {

	@GetMapping
	public Map<String, String> index() {
		Map<String, String> pong = new HashMap<>();
		pong.put("pong", "Hello, World!");
		return pong;
	}
}
