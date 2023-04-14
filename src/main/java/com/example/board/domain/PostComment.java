package com.example.board.domain;

import java.time.LocalDateTime;

public class PostComment {
    private Long id;
    private Post post; // 게시글 ID
    private String content; // 내용

    private LocalDateTime createdAt; // 작성일
    private String createdBy; // 작성자
    private LocalDateTime modifiedAt; // 수정일
    private String modifiedBy; // 수정자
}
