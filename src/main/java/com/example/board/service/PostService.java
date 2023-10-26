package com.example.board.service;

import com.example.board.domain.SearchType;
import com.example.board.dto.PostDto;
import com.example.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class PostService {

    private final PostRepository postRepository;

    @Transactional(readOnly = true)
    public Page<PostDto> searchPosts(SearchType searchType, String searchKeyword) {


        return Page.empty();
    }

    @Transactional(readOnly = true)
    public PostDto searchPosts(long id) {
        return null;
    }
}
