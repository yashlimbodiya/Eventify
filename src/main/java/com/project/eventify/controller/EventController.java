package com.project.eventify.controller;

import com.project.eventify.model.Event;
import com.project.eventify.model.User;
import com.project.eventify.service.EventRegistrationService;
import com.project.eventify.service.EventService;
import com.project.eventify.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EventController {
    private final EventService eventService;
    private final EventRegistrationService eventRegistrationService;


    @Autowired
    public EventController(EventService eventService, EventRegistrationService eventRegistrationService) {
        this.eventService = eventService;
        this.eventRegistrationService = eventRegistrationService;
    }



    @ResponseBody
    @PostMapping("/saveEvent")
    public String register(@ModelAttribute Event event, HttpServletRequest request) {
        System.out.println("Before EventController - /saveEvent");
        HttpSession session = request.getSession(false);
        if(session != null && session.getAttribute("user_id") != null) {
            event.setOrganizer((int)session.getAttribute("user_id"));
        }
        Event registered = eventService.saveEvent(event);
        if(registered == null) {
            return "Something went wrong";
        }
        System.out.println("After EventController - /saveEvent" + registered.toString());
        return "success";
    }

    @ResponseBody
    @GetMapping("/allEvents")
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @ResponseBody
    @GetMapping("/allEventsAtCity")
    public ResponseEntity<List<Event>> getEventByCity(HttpServletRequest request) {
        String city = request.getParameter("city");
        System.out.println(city);
        List<Event> events = eventService.getEventsByCity(city);
        if(events.isEmpty()) {
            events = eventService.getAllEvents();
        }
        return ResponseEntity.ok().body(events);
    }

    @ResponseBody
    @GetMapping("/eventByCategory")
    public ResponseEntity<List<Event>> getEventsByCategory(HttpServletRequest request) {
        String category = request.getParameter("category");
        System.out.println(category);
        List<Event> events = eventService.getEventsByCategory(category);
        if(events.isEmpty()) {
            events = eventService.getAllEvents();
        }
        return ResponseEntity.ok().body(events);
    }


    @GetMapping("/createEvent")
    public String getCreateEventView() {
        return "createEventPage";
    }

    @GetMapping("/myAccount")
    public ModelAndView getEventsByUserId(HttpServletRequest request, ModelAndView mv) {
         HttpSession session = request.getSession(false);
        System.out.println("In EventController Session : " + session);
         List<Event> events = null;
         if(session != null && session.getAttribute("user_id") != null) {
             events = eventService.getEventsRsvpByUserId((int)session.getAttribute("user_id"));
             int totalRsvps = events.size();
             mv.addObject("totalRsvps",totalRsvps);
             mv.setViewName("userAccountPage");
         } else {
             events = eventService.getAllEvents();
             mv.setViewName("homePage");
         }
         mv.addObject("eventList", events);

         return mv;
    }

    @ResponseBody
    @GetMapping("/getTotalPostedEvents")
    public int getTotalPostedEventsByOrganizer(@RequestParam int id) {
        return eventService.getTotalPostedEventsByOrganizer(id);
    }

    @ResponseBody
    @GetMapping("/getTotalRsvp")
    public Long getTotalRsvpOfAllEvents(@RequestParam int id) {
        return eventService.getTotalRsvpOfAllEvents(id);
    }


}
