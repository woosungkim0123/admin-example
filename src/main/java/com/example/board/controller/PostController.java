package com.example.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/posts")
@Controller
public class PostController {

    @GetMapping
    public String posts(Model model) {
        model.addAttribute("posts", List.of());
        return "posts/index";
    }
}
