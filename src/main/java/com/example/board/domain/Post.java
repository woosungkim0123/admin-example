package com.example.board.domain;

import java.time.LocalDateTime;

public class Post {
    private Long id;
    private String title; // 제목
    private String content; // 내용
    private String hashtag; // 해시태그

    private LocalDateTime createdAt; // 작성일
    private String createdBy; // 작성자
    private LocalDateTime modifiedAt; // 수정일
    private String modifiedBy; // 수정자
}
