package com.example.board.dto;

import com.example.board.domain.Post;
import com.example.board.domain.PostComment;

import java.time.LocalDateTime;

public record PostCommentDto(
        Long id,
        Long articleId,
        UserAccountDto userAccountDto,
        String content,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {
    public static PostCommentDto of(Long id, Long articleId, UserAccountDto userAccountDto, String content, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new PostCommentDto(id, articleId, userAccountDto, content, createdAt, createdBy, modifiedAt, modifiedBy);
    }

    public static PostCommentDto from(PostComment entity) {
        return new PostCommentDto(
                entity.getId(),
                entity.getPost().getId(),
                UserAccountDto.from(entity.getUserAccount()),
                entity.getContent(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }

    public PostComment toEntity(Post entity) {
        return PostComment.of(
                entity,
                userAccountDto.toEntity(),
                content
        );
    }
}