package com.example.board.dto;

import com.example.board.domain.Post;

import java.time.LocalDateTime;

public record PostDto(
        Long id,
        UserAccountDto userAccountDto,
        String title,
        String content,
        String hashtag,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {
    public static PostDto of(Long id, UserAccountDto userAccountDto, String title, String content, String hashtag, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new PostDto(id, userAccountDto, title, content, hashtag, createdAt, createdBy, modifiedAt, modifiedBy);
    }

    public static PostDto from(Post entity) {
        return new PostDto(
                entity.getId(),
                UserAccountDto.from(entity.getUserAccount()),
                entity.getTitle(),
                entity.getContent(),
                entity.getHashtag(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }

    public Post toEntity() {
        return Post.of(
                userAccountDto.toEntity(),
                title,
                content,
                hashtag
        );
    }
}

/*
\ㆍ 불변(immutable) 데이터 객체를 쉽게 생성할 수 있도록 하는 새로운 유형의 클래스
* ㆍ레코드의 제한

     - 레코드는 암묵적으로 final 클래스(상속불가)이고, abstract 선언 불가

     - 다른 클래스를 상속(extends) 받을 수 없음, 인터페이스 구현(implements)은 가능
* */
