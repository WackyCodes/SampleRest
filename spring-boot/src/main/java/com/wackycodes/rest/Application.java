package com.wackycodes.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RestController;

@ServletComponentScan
@SpringBootApplication(scanBasePackages = "com.wackycodes.rest")

@EnableJpaRepositories("com.wackycodes.rest.repo")
@EntityScan("com.wackycodes.rest.entity")
@RestController
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

//	@RequestMapping("/")
//	public String home() {
//		return "Hello WackyCodes!! \n Check : https://linktr.ee/wackycodes";
//	}

	/**
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			System.out.println("Let's inspect the beans provided by Spring Boot:");

			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (String beanName : beanNames) {
				System.out.println(beanName);
			}

		};
	}
	*/
}
