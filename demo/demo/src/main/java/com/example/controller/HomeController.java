package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.User;
import com.example.service.UserService;
import com.example.service.UserServiceImpl;

@Controller
public class HomeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    
    @RequestMapping("/")
    public String getHomePage() {
        LOGGER.debug("Home page url triggered.");
        return "home.jsp";
    }

}
