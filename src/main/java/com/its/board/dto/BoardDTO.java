package com.its.board.dto;

import com.its.board.entity.BoardEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {
    private Long id;
    private String boardTitle; // 제목
    private String boardWriter; // 작성자
    private String boardPassword; //비밀번호
    private String boardContents; //내용
    private int boardHits; // 조회수

    private LocalDateTime createdTime; //작성시간
    private LocalDateTime updatedTime; //업데이트시간

    public BoardDTO(String boardTitle, String boardWriter, String boardPassword, String boardContents, int boardHits, LocalDateTime createdTime, LocalDateTime updatedTime) {
        this.boardTitle = boardTitle;
        this.boardWriter = boardWriter;
        this.boardPassword = boardPassword;
        this.boardContents = boardContents;
        this.boardHits = boardHits;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
    }

    public static BoardDTO toBoardDTO(BoardEntity boardEntity){
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setId(boardEntity.getId());
        boardDTO.setBoardTitle(boardEntity.getBoardTitle());
        boardDTO.setBoardWriter(boardEntity.getBoardWriter());
        boardDTO.setBoardPassword(boardEntity.getBoardPassword());
        boardDTO.setBoardContents(boardEntity.getBoardContents());
        boardDTO.setBoardHits(boardEntity.getBoardHits());
        boardDTO.setCreatedTime(boardEntity.getCreatedTime());
        boardDTO.setUpdatedTime(boardEntity.getUpdatedTime());
        return boardDTO;

    }
}
