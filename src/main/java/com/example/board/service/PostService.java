package com.example.board.service;

import com.example.board.domain.SearchType;
import com.example.board.dto.PostDto;
import com.example.board.dto.PostUpdateDto;
import com.example.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@RequiredArgsConstructor
@Transactional
@Service
public class PostService {

    private final PostRepository postRepository;

    @Transactional(readOnly = true)
    public Page<PostDto> searchPosts(SearchType searchType, String searchKeyword, Pageable pageable) {
        if (searchKeyword == null || searchKeyword.isBlank()) {
            return postRepository.findAll(pageable).map(PostDto::from);
        }

        return switch (searchType) {
            case TITLE -> postRepository.findByTitleContaining(searchKeyword, pageable).map(PostDto::from);
            case CONTENT -> postRepository.findByContentContaining(searchKeyword, pageable).map(PostDto::from);
            case ID -> postRepository.findByUserAccount_UserIdContaining(searchKeyword, pageable).map(PostDto::from);
            case NICKNAME -> postRepository.findByUserAccount_NicknameContaining(searchKeyword, pageable).map(PostDto::from);
            case HASHTAG -> postRepository.findByHashtag("#" + searchKeyword, pageable).map(PostDto::from); // #을 넣고검색하면 두개가 들어가는 문제
        };
    }

    @Transactional(readOnly = true)
    public PostDto searchPosts(long id) {
        return null;
    }

    public void savePost(PostDto postDto) {

    }

    public void updatePost(long postId, PostUpdateDto postUpdateDto) {

    }

    public void deletePost(long postId) {

    }
}
