package com.example.board.dto;

import com.example.board.domain.Post;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public record PostWithCommentDto(
        Long id,
        UserAccountDto userAccountDto,
        Set<PostCommentDto> postCommentDtos,
        String title,
        String content,
        String hashtag,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {
    public static PostWithCommentDto of(Long id, UserAccountDto userAccountDto, Set<PostCommentDto> articleCommentDtos, String title, String content, String hashtag, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new PostWithCommentDto(id, userAccountDto, articleCommentDtos, title, content, hashtag, createdAt, createdBy, modifiedAt, modifiedBy);
    }

    public static PostWithCommentDto from(Post entity) {
        return new PostWithCommentDto(
                entity.getId(),
                UserAccountDto.from(entity.getUserAccount()),
                entity.getPostComments().stream()
                        .map(PostCommentDto::from)
                        .collect(Collectors.toCollection(LinkedHashSet::new)),
                entity.getTitle(),
                entity.getContent(),
                entity.getHashtag(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }
}