package com.example.board.dto;

import java.time.LocalDateTime;

public record PostCommentDto(
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy,
        String content
) {
    public static PostCommentDto of(LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy, String content) {
        return new PostCommentDto(createdAt, createdBy, modifiedAt, modifiedBy, content);
    }
}