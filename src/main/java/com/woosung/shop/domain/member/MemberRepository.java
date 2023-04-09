package com.woosung.shop.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByEmail(String email);
}