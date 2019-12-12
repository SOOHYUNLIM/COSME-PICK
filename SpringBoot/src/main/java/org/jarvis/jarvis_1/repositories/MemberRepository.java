package org.jarvis.jarvis_1.repositories;

import org.jarvis.jarvis_1.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * MemberRepository
 */
public interface MemberRepository extends JpaRepository<Member, String> {

    
}