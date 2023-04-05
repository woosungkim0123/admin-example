package com.woosung.shop.domain.member;

import com.woosung.shop.config.JpaConfig;
import com.woosung.shop.domain.Address;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Import(JpaConfig.class)
@DataJpaTest
class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository;

    @PersistenceContext EntityManager em;

    @Test
    @DisplayName("멤버를 추가하고 조회하는 테스트")
    public void addMember() {
        String name = "nameTest";
        String email = "emailTest@email.com";
        String password = "testPassword";
        String picture = "202304051603333test";

        Member member = Member.builder()
                .name(name)
                .email(email)
                .password(password)
                .picture(picture)
                .role(Role.USER)
                .build();

        Member savedMember = memberRepository.save(member);

        em.flush();
        em.clear();

        Member findMember = memberRepository.findById(savedMember.getId()).get();

        assertAll("member",
                () -> assertEquals(findMember.getName(), savedMember.getName()),
                () -> assertEquals(findMember.getPicture(), savedMember.getPicture()),
                () -> assertEquals(findMember.getPassword(), savedMember.getPassword()),
                () -> assertEquals(findMember.getPicture(), savedMember.getPicture()),
                () -> assertEquals(findMember.getRole(), savedMember.getRole()),
                () -> assertEquals(findMember.getEmail(), savedMember.getEmail()),
                () -> assertEquals(findMember.getId(), savedMember.getId()),
                () -> assertThat(findMember.getCreatedDate()).isNotNull(),
                () -> assertThat(findMember.getModifiedDate()).isNotNull()
        );
    }
}