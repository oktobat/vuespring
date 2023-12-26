package com.office.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @CrossOrigin(origins = "http://localhost:8800")
    @GetMapping("/")
    public String home(){
        return "index";
    }

    @GetMapping("/api")
    public String api(){
        return "Api Data 준비중...";
    }

}
