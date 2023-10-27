package com.example.board.service;

import com.example.board.domain.Post;
import com.example.board.domain.UserAccount;
import com.example.board.dto.PostCommentDto;
import com.example.board.repository.PostCommentRepository;
import com.example.board.repository.PostRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class PostCommentServiceTest {

    @InjectMocks
    private PostCommentService postCommentService;
    @Mock
    private PostRepository postRepository;
    @Mock
    private PostCommentRepository postCommentRepository;

    @DisplayName("게시글 id로 조회하면 해당하는 댓글 리스트를 반환한다.")
    @Test
    void givenPostId_whenSearchingPostComments_thenReturnPostComments() {
        // given
        Long postId = 1L;
        UserAccount userAccount = null;
        given(postRepository.findById(any(Long.class))).willReturn(Optional.of(Post.of(userAccount, "title", "content", "#hashtag")));

        // when
        Page<PostCommentDto> postComments = postCommentService.searchPostComments(postId);

        // then
        then(postRepository).should().findById(postId);
    }

    // TODO 댓글 저장 테스트
}