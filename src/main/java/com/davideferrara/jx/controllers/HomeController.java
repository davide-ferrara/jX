package com.davideferrara.jx.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController {
    @GetMapping("/")
    public List<String> hello(){
        return List.of("Greetings X member!");
    }
    @GetMapping("/welcome")
    public List<String> hellos(@RequestParam(value="name") String name){
        return List.of("Welcome " + name + "!");
    }
}
