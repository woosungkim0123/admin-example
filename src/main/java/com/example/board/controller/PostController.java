package com.example.board.controller;

import com.example.board.domain.SearchType;
import com.example.board.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/posts")
@Controller
public class PostController {

    private final PostService postService;

    @GetMapping
    public String posts(
            @RequestParam(required = false) SearchType searchType,
            @RequestParam(required = false) String searchValue,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
            Model model
    ) {
        postService.searchPosts(searchType, searchValue, pageable);

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
