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

    @Column(length = 50,nullable = false)
    private String boardTitle;

    @Column(length = 20)
    private String boardWriter;

    @Column(length = 20)
    private String boardPassword;

    @Column(length = 500)
    private String boardContents;

    @Column
    private int boardHits;

    public static BoardEntity toBoardEntity(BoardDTO boardDTO){
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardWriter(boardDTO.getBoardWriter());
        boardEntity.setBoardPassword(boardDTO.getBoardPassword());
        boardEntity.setBoardContents(boardDTO.getBoardContents());
        boardEntity.setBoardHits(boardDTO.getBoardHits());
        return boardEntity;
    }


    public static BoardEntity toSaveEntity(BoardDTO boardDTO) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setBoardTitle(boardEntity.getBoardTitle());
        boardEntity.setBoardWriter(boardEntity.getBoardWriter());
        boardEntity.setBoardPassword(boardEntity.getBoardPassword());
        boardEntity.setBoardContents(boardEntity.getBoardContents());
        boardEntity.setBoardHits(0);
        return boardEntity;
    }
}
