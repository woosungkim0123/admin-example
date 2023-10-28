package com.example.board.service;

import com.example.board.domain.Post;
import com.example.board.domain.SearchType;
import com.example.board.dto.PostDto;
import com.example.board.dto.PostUpdateDto;
import com.example.board.repository.PostRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @InjectMocks
    private PostService postService;
    @Mock
    private PostRepository postRepository;

    @DisplayName("게시글을 검색하면 해당 게시글 리스트를 반환한다.")
    @Test
    void givenSearchParameter_whenSearchPosts_thenReturnPosts() {
        SearchType searchType = SearchType.TITLE;
        String searchKeyword = "title";
        Pageable pageable = Pageable.ofSize(20);
        given(postRepository.findByTitleContaining(searchKeyword, pageable)).willReturn(Page.empty());

        // When
        Page<PostDto> articles = postService.searchPosts(searchType, searchKeyword, pageable);

        // Then
        assertThat(articles).isEmpty();
        then(postRepository).should().findByTitleContaining(searchKeyword, pageable);
    }

    @DisplayName("게시글을 조회하면 게시글을 반환한다.")
    @Test
    void givenSearchId_whenSearchPosts_thenReturnPosts() {

        // when
        PostDto dto = postService.searchPosts(1L);

        // then
        assertThat(dto).isNotNull();
    }

    @DisplayName("게시글 정보를 입력하면 게시글을 생성한다.")
    @Test
    void givenPostInfo_whenSavingPost_thenSavesPost() {
        // given
        PostDto dto = PostDto.of(1L, null, "title", "content", "#hastag", LocalDateTime.now(), "createdBy", LocalDateTime.now(), "modifiedBy");
        given(postRepository.save(any(Post.class))).willReturn(null);

        // when
        postService.savePost(dto);

        // then
        then(postRepository).should().save(any(Post.class));
    }

    @DisplayName("게시글 ID와 수정 정보를 입력하면 게시글을 수정한다.")
    @Test
    void givenPostIdAndModifiedInfo_whenUpdatingPost_thenUpdatesPost() {
        // given
        PostUpdateDto dto = PostUpdateDto.of("title", "content", "#hastag");
        given(postRepository.save(any(Post.class))).willReturn(null);

        // when
        postService.updatePost(1L, dto);

        // then
        then(postRepository).should().save(any(Post.class));
    }

    @DisplayName("게시글 ID를 입력하면 게시글을 삭제한다.")
    @Test
    void givenPostId_whenDeletingPost_thenDeletesPost() {
        // given
        willDoNothing().given(postRepository).delete(any(Post.class));

        // when
        postService.deletePost(1L);

        // then
        then(postRepository).should().save(any(Post.class));
    }
}