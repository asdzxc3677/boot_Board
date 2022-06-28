package com.its.board.entity;

import com.its.board.dto.MemberDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "member_table")
public class MemberEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memebr_id")
    private Long id;

    @Column
    private String memberEmail;
    @Column
    private String memberPassword;
    @Column
    private String memberName;
    
    // 회원- 게시글 연관관계
    @OneToMany(mappedBy = "memberEntity")
    private List<BoardEntity> boardEntityList = new ArrayList<>();

    public static MemberEntity toSaveEntity(MemberDTO memberDTO){
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberEmail(memberDTO.getMemberEmail());
        memberEntity.setMemberPassword(memberDTO.getMemberPassword());
        memberEntity.setMemberName(memberDTO.getMemberName());
        return memberEntity;
    }
}
