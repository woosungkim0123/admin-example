package com.woosung.shop.domain.member;

import com.woosung.shop.config.JpaConfig;
import com.woosung.shop.domain.Address;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

//@Import(JpaConfig.class)
@DataJpaTest
class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository;

    @PersistenceContext EntityManager em;

    @DisplayName("이메일에 맞는 회원 찾기")
    @Test
    public void testFindByEmail() {
        Member testMember = createMember();
        memberRepository.save(testMember);

        Optional<Member> findMember = memberRepository.findByEmail(testMember.getEmail());

        assertAll(
                () -> assertThat(findMember).isPresent(),
                () -> assertThat(findMember.get().getEmail()).isEqualTo(testMember.getEmail()));
    }


    private Member createMember() {
        String name = "test";
        String email = "test@email.com";
        String password = "password!";
        String picture = "test.png";
        Role role = Role.USER;

        return Member.builder()
                .name(name)
                .email(email)
                .password(password)
                .picture(picture)
                .role(role)
                .build();
    }
}