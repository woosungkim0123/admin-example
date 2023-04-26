package com.example.board.controller;


import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("View Controller - Post")
@WebMvcTest(PostController.class)
class PostControllerTest {

    private final MockMvc mvc;

    public PostControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

    @DisplayName("[view][GET] List Post Page - success")
    @Test
    public void requestingPostsView_thenReturnPostsView() throws Exception {
        // given

        // when & then
        mvc.perform(get("/posts"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("posts/index"))
                .andExpect(model().attributeExists("posts"));
    }

    @DisplayName("[view][GET] Detail Post Page - success")
    @Test
    public void requestingPostDetailView_thenReturnPostDetailView() throws Exception {
        // given

        // when & then
        mvc.perform(get("/posts/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("posts/detail"))
                .andExpect(model().attributeExists("post"))
                .andExpect(model().attributeExists("postComments"));
    }

    @Disabled("developing")
    @DisplayName("[view][GET] Search Post Page - success")
    @Test
    public void requestingPostSearchView_thenReturnPostSearchView() throws Exception {
        // given

        // when & then
        mvc.perform(get("/posts/search"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("posts/search"));
    }

    @Disabled("developing")
    @DisplayName("[view][GET] Search Post to Hashtag Page - success")
    @Test
    public void requestingPostSearchHashtagView_thenReturnPostSearchHashtagView() throws Exception {
        // given

        // when & then
        mvc.perform(get("/posts/search-hashtag"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("posts/search-hashtag"));;
    }
}