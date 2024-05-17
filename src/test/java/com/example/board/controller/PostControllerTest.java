package com.example.board.controller;


import com.example.board.config.SecurityConfig;
import com.example.board.dto.PostWithCommentDto;
import com.example.board.dto.UserAccountDto;
import com.example.board.service.PostService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;


import static com.mysema.commons.lang.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("View Controller - Post")
@Import(SecurityConfig.class)
@WebMvcTest(PostController.class)
class PostControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private PostService postService;


    @DisplayName("[view][GET] List Post Page - success")
    @Test
    public void requestingPostsView_thenReturnPostsView() throws Exception {
        // given
        given(postService.searchPosts(eq(null), eq(null), any(Pageable.class))).willReturn(Page.empty());

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
        Long postId = 1L;
        given(postService.searchPosts(postId)).willReturn(null);

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

    private PostWithCommentDto createArticleWithCommentsDto() {
        return PostWithCommentDto.of(
                1L,
                createUserAccountDto(),
                Set.of(),
                "title",
                "content",
                "#java",
                LocalDateTime.now(),
                "uno",
                LocalDateTime.now(),
                "uno"
        );
    }

    private UserAccountDto createUserAccountDto() {
        return UserAccountDto.of(
                1L,
                "uno",
                "pw",
                "uno@mail.com",
                "Uno",
                "memo",
                LocalDateTime.now(),
                "uno",
                LocalDateTime.now(),
                "uno"
        );
    }
}