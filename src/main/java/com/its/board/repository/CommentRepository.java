package com.its.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<MemberRepository, Long> {
}
