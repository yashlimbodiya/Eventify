package com.project.eventify.controller;

import com.project.eventify.model.Event;
import com.project.eventify.model.User;
import com.project.eventify.service.EventService;
import com.project.eventify.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class OrganizerController {
    private final EventService eventService;

    @Autowired
    public OrganizerController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/home1")
    public ModelAndView getInformation(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        ModelAndView mv = new ModelAndView();
        int user_id;
        List<Event> myEvents = null;
        if(session != null && session.getAttribute("user_id") != null) {
            user_id = (int) session.getAttribute("user_id");
            myEvents = eventService.getEventsById(user_id);
        }
        mv.setViewName("organizerHomePage");
        mv.addObject("eventList", myEvents);
        return mv;
    }
}
