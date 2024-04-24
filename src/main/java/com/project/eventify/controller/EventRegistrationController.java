package com.project.eventify.controller;

import com.project.eventify.model.Event;
import com.project.eventify.model.EventRegistration;
import com.project.eventify.service.EventRegistrationService;
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

    @Autowired
    public EventRegistrationController(EventRegistrationService ers) {
        this.ers = ers;
    }


    @ResponseBody
    @GetMapping("/rsvpEvent")
    public ResponseEntity<String> saveRegistrationDetails(@ModelAttribute EventRegistration er){
        List<EventRegistration> erList = ers.getRsvpDetails(er.getUserId(), er.getEventId());
        if(!erList.isEmpty()) {
            return ResponseEntity.status(200).body("already rsvp");
        }
        EventRegistration er_new = ers.saveEvent(er);
        if(er_new == null) {
            return ResponseEntity.status(200).body("error");
        }
        return ResponseEntity.status(200).body("success");
    }

    @ResponseBody
    @GetMapping("/updateRsvpEvent")
    public ResponseEntity<String> updateRegistrationDetails(@ModelAttribute EventRegistration er){
        List<EventRegistration> erList = ers.getRsvpDetails(er.getUserId(), er.getEventId());
        if(erList.isEmpty()) {
            return ResponseEntity.status(200).body("No RSVP");
        }
        erList.get(0).setResponse(er.getResponse());
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
