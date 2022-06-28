package com.its.board.repository;

import com.its.board.entity.CommentEntity;
import com.its.board.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.Member;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<CommentEntity,Long> {
    Optional<MemberEntity> findByMemberEmail(String memberEmail);
}
