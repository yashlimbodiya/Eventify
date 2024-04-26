package com.project.eventify.service;


import com.project.eventify.dao.EventDao;
import com.project.eventify.dao.EventRegistrationDao;
import com.project.eventify.dao.UserDao;
import com.project.eventify.model.User;
import com.project.eventify.util.InputEncoderDecoder;
import com.project.eventify.util.InputValidator;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    private final UserDao userDao;
    private final EventRegistrationDao eventRegisDao;


    @Autowired
    public UserService(UserDao userDao, EventRegistrationDao eventRegisDao) {
        this.userDao = userDao;
        this.eventRegisDao = eventRegisDao;
    }

    public User saveUser(User user) {
        userDao.save(user);
        return user;
    }
    public User getByEmail(String email) {
        return userDao.getByEmail(email);
    }

    public User getUserById(int id) {
        return userDao.getById(id);
    }


    public List<User> getAllUser() {
        return userDao.getAllUser();
    }

    public String getUserType(String email) {
        User user = userDao.getByEmail(email);
        return user.getType().toString();
    }

    public boolean authenticate(String email, String password) {
        User user = userDao.getByEmail(email);
        password = InputEncoderDecoder.encode(password);
        if(user == null) {
            return false;
        }

        return user.getPassword().equals(password);
    }

    public User sanitizeUserInputs(User user) {
        user.setFirstName(InputValidator.sanitizeUserInput(user.getFirstName()));
        user.setLastName(InputValidator.sanitizeUserInput(user.getLastName()));
        user.setMobileNo(InputValidator.sanitizeNumericInput(user.getMobileNo()));
        return user;
    }

    public List<Object[]> getListOfUsersRsvpedEvent(int id) {
        System.out.println("In UserService :: getListOfUsersRsvpedEvent");
        return eventRegisDao.getListOfUsersRsvpedEvent(id);
    }

}
