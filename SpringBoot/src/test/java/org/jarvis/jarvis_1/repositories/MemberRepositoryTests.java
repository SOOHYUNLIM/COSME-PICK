package org.jarvis.jarvis_1.repositories;

import org.jarvis.jarvis_1.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * MemberRepositoryTests
 */
@SpringBootTest
public class MemberRepositoryTests {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void registerMember() {
        memberRepository.save(Member.builder().mid("user00").mpw("1234").build());
    }
}