package com.example.board.service;

import com.example.board.dto.PostCommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class PostCommentService {

    @Transactional(readOnly = true)
    public Page<PostCommentDto> searchPostComments(Long postId) {
        return Page.empty();
    }
}
