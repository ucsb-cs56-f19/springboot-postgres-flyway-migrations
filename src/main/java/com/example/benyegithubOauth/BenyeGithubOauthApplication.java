package com.example.benyegithubOauth;

import java.security.Principal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableOAuth2Sso
@RestController //for the GetMapping stuff and redirects
public class BenyeGithubOauthApplication {

	@GetMapping("/") //Principal is security interface, GetMapping is endpoint tracker
	public String message(Principal principal) {
		return "Thanks for logging in " + principal.getName() + "!\n";
	}
	public static void main(String[] args) {
		SpringApplication.run(BenyeGithubOauthApplication.class, args);
	}

}
