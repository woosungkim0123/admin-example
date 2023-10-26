package com.example.board.service;

import com.example.board.domain.SearchType;
import com.example.board.dto.PostDto;
import com.example.board.repository.PostRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @InjectMocks
    private PostService postService;
    @Mock
    private PostRepository postRepository;

    @DisplayName("게시글을 검색하면 해당 게시글 리스트를 반환한다.")
    @Test
    void givenSearchParameter_whenSearchPosts_thenReturnPosts() {


        Page<PostDto> posts = postService.searchPosts(SearchType.TITLE, "search keyword"); // 제목,본문,ID, 닉네임,해시태그

        // then
        assertThat(posts).isNotNull();
    }

    @DisplayName("게시글을 조회하면 게시글을 반환한다.")
    @Test
    void givenSearchId_whenSearchPosts_thenReturnPosts() {


        PostDto posts = postService.searchPosts(1L);

        // then
        assertThat(posts).isNotNull();
    }
}