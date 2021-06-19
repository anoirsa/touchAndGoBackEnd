package com.example.springtouchgo;

import com.example.springtouchgo.model.appuser.*;
import com.example.springtouchgo.repository.appuser.AppUserRepository;
import com.example.springtouchgo.repository.appuser.QuestionRepository;
import com.example.springtouchgo.service.email.SendEmailService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SpringtouchgoApplication {
	public BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder() ;

	public static void main(String[] args) {
		SpringApplication.run(SpringtouchgoApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(AppUserRepository appUserRepository, QuestionRepository questionRepository) {

		return args -> {
			appUserRepository.save(new AppUser("admin",passwordEncoder.encode("15111996"),"gg@gmail.com",
					true,true,true,true,
					AppUserRole.ADMIN));
			appUserRepository.save(new AppUser("jamal",passwordEncoder.encode("15111996"),"osp@gmail.com",
					true,true,true,true,
					AppUserRole.NORMAL_USER));
			questionRepository.save(new
					Question("question1","o1","o2","o3","o4", QuestionLevel.EASY, Category.SPORTS));
			questionRepository.save(new
					Question("question2","o1","o2","o3","o4", QuestionLevel.EASY,Category.SPORTS));
			questionRepository.save(new
					Question("question3","o1","o2","o3","o4", QuestionLevel.EASY,Category.SPORTS));
			questionRepository.save(new
					Question("question4","o1","o2","o3","o4", QuestionLevel.EASY,Category.SPORTS));

		};
	}

}
