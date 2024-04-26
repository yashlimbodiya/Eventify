package com.project.eventify.controller;

import com.project.eventify.model.Event;
import com.project.eventify.model.User;
import com.project.eventify.model.UserType;
import com.project.eventify.service.EventService;
import com.project.eventify.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
public class HomePageController {

    private final UserService userService;
    private final EventService eventService;


    @Autowired
    public HomePageController(UserService userService, EventService eventService) {
        this.userService = userService;
        this.eventService = eventService;
    }

    @GetMapping("/home")
    public ModelAndView getInformation(HttpServletRequest request, ModelAndView mv) {
        HttpSession session = request.getSession(false);
        User user = null;
        if(session != null && session.getAttribute("username") != null) {
            user = userService.getByEmail((String)session.getAttribute("username"));
        }
        String viewName;
        List<Event> events;
        if(user.getType().toString().equals(UserType.general.toString())) {
            viewName = "homePage";
            events = eventService.getAllEvents();
        } else {
            events = eventService.getEventsById(user.getUserId());
            int totalEvents = eventService.getTotalPostedEventsByOrganizer(user.getUserId());
            mv.addObject("totalEvents", totalEvents);
            viewName = "organizerHomePage";
        }
        mv.addObject("eventList", events);
        mv.setViewName(viewName);
        return mv;
    }

}
