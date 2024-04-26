package com.project.eventify.controller;

import com.project.eventify.model.Event;
import com.project.eventify.model.User;
import com.project.eventify.service.EventService;
import com.project.eventify.service.UserService;
import com.project.eventify.util.InputEncoderDecoder;
import com.project.eventify.util.InputValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@Controller
public class UserController {

    private final UserService userService;
    private final EventService eventService;

    @Autowired
    public UserController(UserService userService, EventService eventService) {
        this.userService = userService;
        this.eventService = eventService;
    }

    @ResponseBody
    @PostMapping("/saveUser")
    public String register(@ModelAttribute  User user/*, BCryptPasswordEncoder encoder, HttpServletRequest req*/) {
        System.out.println("Before UserController - /saveUser = " + user);
        if(InputValidator.isEmailValid(user.getEmail())) {
            return "Invalid email address";
        }
        user = userService.sanitizeUserInputs(user);
        User existingUser = userService.getByEmail(user.getEmail());
        System.out.println("After UserController - existing User " + existingUser);
        if(existingUser != null) {
            return "Sign in to this account or enter an email address that isn't already in use.";
        }
        user.setPassword(InputEncoderDecoder.encode(user.getPassword()));
        User new_user = userService.saveUser(user/*, encoder*/);
        if(new_user == null) {
            return "Technical error - Something went wrong";
        }
        System.out.println("After UserController - /saveUser" + new_user);
        return "success";
    }

    @ResponseBody
    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticateUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("UserAuthController::authenticateUser");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if(InputValidator.isEmailValid(username)) {
            return ResponseEntity.status(200).body("Invalid username and password");
        }
        boolean isAuthSuccess = userService.authenticate(username, password);
        if(!isAuthSuccess) {
            return ResponseEntity.status(200).body("Invalid username and password");
        }
        HttpSession session = request.getSession(true);
        session.setAttribute("username", request.getParameter("username"));
        User user = userService.getByEmail(username);
        session.setAttribute("user_id", user.getUserId());
        session.setAttribute("userFirstName", user.getFirstName());
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0
        response.setHeader("Expires", "0"); // Proxies
        return ResponseEntity.status(200).body("authorize");
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // Invalidate the session
        }
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0
        response.setHeader("Expires", "0"); // Proxies
        return "redirect:/login";
    }

    @ResponseBody
    @GetMapping("/allUser")
    public String getAllUser() {
        List<User> user = userService.getAllUser();
        StringBuilder res = new StringBuilder();
        for(User u : user) {
            res.append(u.toString());
        }
        return res.toString();
    }

    @GetMapping("/rsvpedUsers")
    public ModelAndView getListOfUsersRsvpedEvent(@RequestParam String eventId, ModelAndView mv) {
        int eId = Integer.parseInt(eventId);
        mv.setViewName("viewEventDetailsPage");
        List<Object[]> rsvpedUsers = userService.getListOfUsersRsvpedEvent(eId);
        Event event = eventService.getEvent(eId);
        mv.addObject("event", event);
        mv.addObject("usersArray", rsvpedUsers);
        return mv;
    }

}
