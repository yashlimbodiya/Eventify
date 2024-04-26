package com.project.eventify;

import com.project.eventify.dao.UserDao;
import com.project.eventify.model.User;
import com.project.eventify.model.UserType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class EventifyApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(EventifyApplication.class, args);
		System.out.println("Hello World");
	}

}
