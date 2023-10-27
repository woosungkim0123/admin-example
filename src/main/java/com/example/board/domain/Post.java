package com.example.board.domain;

import jakarta.persistence.*;
import lombok.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

import static lombok.AccessLevel.*;

@Getter
@ToString(callSuper = true)
@NoArgsConstructor(access = PROTECTED)
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "hashtag"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy"),
})
@Entity
public class Post extends AuditingFields {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false, length = 1000)
    private String title; // 제목

    @Setter
    @Column(nullable = false, length = 10000)
    private String content; // 내용

    @Setter
    private String hashtag; // 해시태그

    @Setter
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private UserAccount userAccount;

    @ToString.Exclude
    @OrderBy("createdAt DESC")
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private final Set<PostComment> postComments = new LinkedHashSet<>();

    private Post(UserAccount userAccount, String title, String content, String hashtag) {
        this.userAccount = userAccount;
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
    }

    public static Post of(UserAccount userAccount,String title, String content, String hashtag) {
        return new Post(userAccount, title, content, hashtag);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post post)) return false;
        return id != null && id.equals(post.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
