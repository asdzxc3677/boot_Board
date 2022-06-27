package com.its.board.service;

import com.its.board.common.PagingConst;
import com.its.board.dto.BoardDTO;
import com.its.board.entity.BoardEntity;
import com.its.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    public Long save(BoardDTO boardDTO) throws IOException {
        // 글쓰기및 파일업로드가 안되었던 이유 : 1.서비스쪽 오타. 2.파일경로 설정안했음 D드라이브에 저장할 파일 springboot_img 설정안함  3.테이블 컬럼 잘못설정 4. dto에 파일 업로드 추가를 안했음
        MultipartFile boardFile = boardDTO.getBoardFile(); // 파일업로드 추가
        String boardFileName = boardFile.getOriginalFilename();
        boardFileName = System.currentTimeMillis() + "-" + boardFileName;
        String savePath = "D:\\springboot_img\\" + boardFileName;
        if(!boardFile.isEmpty()){
            boardFile.transferTo(new File(savePath));
        }
        boardDTO.setBoardFileName(boardFileName);

        Long savedId = boardRepository.save(BoardEntity.toSaveEntity(boardDTO)).getId();
        return savedId;
    }

    public List<BoardDTO> findAll() {
        List<BoardEntity> boardEntityList = boardRepository.findAll();
        List<BoardDTO> boardDTOList = new ArrayList<>();
        for (BoardEntity boardEntity: boardEntityList) {
            boardDTOList.add(BoardDTO.toBoardDTO(boardEntity));
        }
        return boardDTOList;
    }

    @Transactional
    public BoardDTO findById(Long id) { // findById 에는 @Transactional을 붙여야 한다.
        // 조회수 처리
        // native sql: update board_table set boardHits=boardHits+1 where id=?
        boardRepository.boardHits(id);
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(id);
        if (optionalBoardEntity.isPresent()) {
            return BoardDTO.toBoardDTO(optionalBoardEntity.get());
        } else {
            return null;
        }
    }

    public void update(BoardDTO boardDTO) { //수정처리
        boardRepository.save(BoardEntity.toUpdateEntity(boardDTO));
    }

    public void delete(Long id) {
        boardRepository.deleteById(id);
    }

    public Page<BoardDTO> paging(Pageable pageable) {
        int page = pageable.getPageNumber(); //요청 페이지값 가져옴.
        // 요청한 페이지가 1이면 페이지값을 0으로 하고 1이 아니면 요청 페이지에서 1을 뺀다.
//        page = page - 1;
        //삼항연산자
        page = (page == 1)? 0: (page-1);
        Page<BoardEntity> boardEntities = boardRepository.findAll(PageRequest.of(page, PagingConst.PAGE_LIMIT, Sort.by(Sort.Direction.DESC, "id")));
        // Page<BoardEntity> => Page<BoardPagingDTO>
        Page<BoardDTO> boardList = boardEntities.map(
                // BoardEntity 객체 -> BoardDTO 객체 변환
                // board: BoardEntity 객체
                // new BoardDTO() 생성자
                board -> new BoardDTO(board.getId(),
                        board.getBoardTitle(),
                        board.getBoardWriter(),
                        board.getBoardHits(),
                        board.getCreatedTime()
                ));
        return boardList;
    }
}












