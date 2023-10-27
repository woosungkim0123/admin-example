package com.example.board.dto;

public record PostUpdateDto(
        String title,
        String content,
        String hashtag
) {
    public static PostUpdateDto of(String title, String content, String hashtag) {
        return new PostUpdateDto(title, content, hashtag);
    }
}