package com.example.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/{postId}")
    public String post(@PathVariable Long postId, Model model) {

        model.addAttribute("post", "");
        model.addAttribute("postComments", List.of());

        return "posts/detail";
    }

}
