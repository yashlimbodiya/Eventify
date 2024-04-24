package com.project.eventify.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LandingPageController {

    @RequestMapping({"/"})
    public String getLandingPage() {
        System.out.println("On Landing Page");
        return "landingPage";
    }

    @GetMapping("/login")
    public String login() {
        System.out.println("On Landing Page - login");
        return "userLoginPage";
    }

    @GetMapping("/register")
    public String register() {
        System.out.println("On Landing Page - register");
        return "userRegisterPage";
    }

    @GetMapping("/becameOrganizer")
    public String registerOrganizer() {
        System.out.println("On Landing Page - register");
        return "organizerRegisterPage";
    }
}
