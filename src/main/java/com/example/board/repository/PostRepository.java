package com.example.board.repository;

import com.example.board.domain.Post;

import com.example.board.domain.QPost;

import com.querydsl.core.types.dsl.DateTimeExpression;

import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PostRepository extends
        JpaRepository<Post, Long>,
        QuerydslPredicateExecutor<Post>, // 도메인에 대한 검색기능을 추가해줌 (정확해야함)
        QuerydslBinderCustomizer<QPost> { // 입맛에 맞는 검색기능을 만들기위해  (like)

    Page<Post> findByTitleContaining(String title, Pageable pageable);
    Page<Post> findByContentContaining(String content, Pageable pageable);
    Page<Post> findByUserAccount_UserIdContaining(String userId, Pageable pageable);
    Page<Post> findByUserAccount_NicknameContaining(String nickname, Pageable pageable);
    Page<Post> findByHashtag(String hashtag, Pageable pageable);



    // 인터페이스라 구현할수없음 디폴트메서드로 변경해서 가능
    @Override
    default void customize(QuerydslBindings bindings, QPost root) {
        bindings.excludeUnlistedProperties(true); // 현재 QuerydslPredicateExecutor로 인해서 모든 필드들에 대한 검색이 열려있음, 리스링을 하지않은 프로퍼티는 검색에서 제외
        // 원하는 필드를 추가
        bindings.including(root.title, root.content, root.hashtag, root.createdAt, root.createdBy);

        // 정확해야하는걸로 동작하는데 그걸 변경 (파라미터를 하나만 받으니까 first를 사용하고)
        bindings.bind(root.title).first(StringExpression::containsIgnoreCase); // 대소문자를 구분안하고  like '%${v}%'
        // 와일드카드를 넣고 안넣고를 수동으로 정하고 싶을 떄 likeIngnoreCase를 사용
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.hashtag).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.createdAt).first(DateTimeExpression::eq);
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase);
    }
}