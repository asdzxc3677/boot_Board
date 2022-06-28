package com.its.board.entity;

import com.its.board.dto.BoardDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity // DTO같은 역할을 하는데 스트링부트에서 데이터베이스에 저장할때 쓰는 타입
@Getter @Setter
@Table(name = "board_table") //테이블 이름 변경하고 싶을때
public class BoardEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id") // 컬럼 이름 지정
    private Long id; // @Id, @GeneratedValue, @Column(name = "id") 한셋트

    @Column(length = 50, nullable = false)
    private String boardTitle;

    @Column(length = 20)
    private String boardWriter;

    @Column(length = 20)
    private String boardPassword;

    @Column(length = 500)
    private String boardContents;

    @Column
    private int boardHits;

    @Column
    private String boardFileName;

    //회원-게시글 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;


   //회원엔티티와 연관관계 맺기전
//    public static BoardEntity toSaveEntity(BoardDTO boardDTO) {
//        BoardEntity boardEntity = new BoardEntity();
//        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
//        boardEntity.setBoardWriter(boardDTO.getBoardWriter());
//        boardEntity.setBoardPassword(boardDTO.getBoardPassword());
//        boardEntity.setBoardContents(boardDTO.getBoardContents());
//        boardEntity.setBoardHits(0);
//        boardEntity.setBoardFileName(boardDTO.getBoardFileName());
//        return boardEntity;
//    }
        //회원과 연관관계 맺은 후
        public static BoardEntity toSaveEntity(BoardDTO boardDTO,MemberEntity memberEntity) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
//        boardEntity.setBoardWriter(boardDTO.getBoardWriter());
        boardEntity.setBoardWriter(memberEntity.getMemberEmail()); //회원 이메일을 작성자로 한다면
        boardEntity.setBoardPassword(boardDTO.getBoardPassword());
        boardEntity.setBoardContents(boardDTO.getBoardContents());
        boardEntity.setBoardHits(0);
        boardEntity.setBoardFileName(boardDTO.getBoardFileName());
        return boardEntity;
    }


    public static BoardEntity toUpdateEntity(BoardDTO boardDTO) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setId(boardDTO.getId());
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardWriter(boardDTO.getBoardWriter());
        boardEntity.setBoardPassword(boardDTO.getBoardPassword());
        boardEntity.setBoardContents(boardDTO.getBoardContents());
        boardEntity.setBoardHits(boardDTO.getBoardHits());
        return boardEntity;

    }
}














