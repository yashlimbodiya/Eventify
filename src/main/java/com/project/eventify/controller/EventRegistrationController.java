package com.project.eventify.controller;

import com.project.eventify.model.Event;
import com.project.eventify.model.EventRegistration;
import com.project.eventify.model.User;
import com.project.eventify.model.UserResponseType;
import com.project.eventify.service.EventRegistrationService;
import com.project.eventify.service.EventService;
import com.project.eventify.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class EventRegistrationController {

    private final EventRegistrationService ers;
    private final UserService userService;
    private  final EventService eventService;

    @Autowired
    public EventRegistrationController(EventRegistrationService ers, UserService userService, EventService eventService) {
        this.ers = ers;
        this.userService = userService;
        this.eventService = eventService;
    }


    @ResponseBody
    @GetMapping("/rsvpEvent")
    public ResponseEntity<String> saveRegistrationDetails(@ModelAttribute EventRegistration er, HttpServletRequest request){
        User user = userService.getUserById(Integer.parseInt(request.getParameter("userId")));
        Event event = eventService.getEvent(Integer.parseInt(request.getParameter("eventId")));
        List<EventRegistration> erList = ers.getRsvpDetails(user, event);
        if(!erList.isEmpty()) {
            return ResponseEntity.status(200).body("already rsvp");
        }
        EventRegistration er_new = ers.saveEvent(er, user, event);
        if(er_new == null) {
            return ResponseEntity.status(200).body("error");
        }
        return ResponseEntity.status(200).body("success");
    }

    @ResponseBody
    @GetMapping("/updateRsvpEvent")
    public ResponseEntity<String> updateRegistrationDetails(HttpServletRequest request){
        User user = userService.getUserById(Integer.parseInt(request.getParameter("userId")));
        Event event = eventService.getEvent(Integer.parseInt(request.getParameter("eventId")));
        List<EventRegistration> erList = ers.getRsvpDetails(user, event);
        if(erList.isEmpty()) {
            return ResponseEntity.status(200).body("No RSVP");
        }

        erList.get(0).setResponse(UserResponseType.Declined);
        EventRegistration er_new = ers.updateRsvpDetails(erList.get(0));
        if(er_new == null) {
            return ResponseEntity.status(200).body("error");
        }
        return ResponseEntity.status(200).body("success");
    }

    @ResponseBody
    @GetMapping("/allRsvps")
    public List<EventRegistration> getAllRsvp(@RequestParam String uID) {
        int uid = Integer.parseInt(uID);
        return ers.getAllRsvp(uid);
    }



}
