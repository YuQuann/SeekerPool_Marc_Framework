package com.example.seekerpool_springboot.marc.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class test {

    @GetMapping("/test1")
    public String test1(){
        return "Hello";
    }

}
