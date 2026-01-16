package com.example.demo;

import java.util.Date;

import com.example.demo.configuration.AppUser;
import com.example.demo.configuration.LoggedInUser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

	}
       
	@GetMapping("/")
	public String home(){
		return "<html><body style='text-align: center; font-family: Arial, sans-serif; padding: 20px;'>" +
		       "<h1>Bem-vindo! 🐶</h1>" +
		       "<img src='doguinho.png' alt='Doguinho Fofo' style='border-radius: 10px; box-shadow: 0 4px 8px rgba(0,0,0,0.2);'>" +
		       "</body></html>";
	}

	@GetMapping("/healthCheck")
        public String healthCheck(){
                return "HEALTH CHECK OK!";
        }

	@GetMapping("/secured")
	public Object secured(@LoggedInUser AppUser appUser){
		return appUser.getUser();
	}

	@GetMapping("/secured-admin")
	@PreAuthorize("hasRole('ROLE_admin')")
	public String securedAdmin(){
		return "Only admin can see  this";
	}
	
	@GetMapping("/public")
	public String pub(){
		
		return "This is public endpoint";
	}

	@GetMapping("/what-is-the-time")
	String time(){
		return new Date().toString();
	}
	
	@GetMapping("/devops")
	String turma(){
		return "Zumbi";
	}
	
	@GetMapping("/autoglass")
	String autoglass(){
		return "https://www.autoglassonline.com.br/";
	}
	
	@GetMapping("/10CLDR")
	String cldr(){
		return "Turma 10 MBA Cloud 2025";
	}
	
}	
