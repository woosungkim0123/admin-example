package com.example.board.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.util.Objects;

import static lombok.AccessLevel.PROTECTED;

@Getter
@ToString(callSuper = true)
@NoArgsConstructor(access = PROTECTED)
@Table(indexes = {
        @Index(columnList = "content"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy"),
})
@Entity
public class PostComment extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne(optional = false)
    private Post post; // 게시글 ID

    @Setter
    @Column(nullable = false, length = 500)
    private String content; // 내용

    @Setter
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private UserAccount userAccount;

    private PostComment(Post post, UserAccount userAccount, String content) {
        this.post = post;
        this.userAccount = userAccount;
        this.content = content;
    }

    public static PostComment of(Post post, UserAccount userAccount,String content) {
        return new PostComment(post, userAccount, content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PostComment that)) return false;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
