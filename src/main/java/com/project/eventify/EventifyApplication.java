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
		UserDao userDao = context.getBean(UserDao.class);
		/*User u = new User("TestF1","L","987654","4email","12345","123 D Boston","123.png", UserType.general);
		int userId = userDao.save(u);
		if(userId != -1) {
			System.out.println("User saved with ID: " + userId); // Print the generated userId if save successful
		} else {
			System.out.println("Failed to save user."); // Print failure message if save unsuccessful
		}*/
		User n = userDao.getByEmail(userDao.getById(100).getEmail());
		System.out.println(n);
	}

}
