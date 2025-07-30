package com.example.SpringBootBoard.service;

import com.example.SpringBootBoard.dto.BoardDTO;
import com.example.SpringBootBoard.entity.BaseEntity;
import com.example.SpringBootBoard.entity.BoardEntity;
import com.example.SpringBootBoard.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//컨트롤러, 리포지토리, 서비스는 요청에 대한 응답을 위한 계층임.
//controller 요청 받음 -> service 호출(DTO > Entity 변환, 비즈니스 로직 처리) -> service가 repository 호출해서 db에 저장
//그래서 여기서는 변환을 하는데, entity -> DTO (DTO Class에서 진행), DTO -> Entity (Entity Class에서 진행)

@Service
@RequiredArgsConstructor //여기도 생성자 자동으로 만들어줌
public class BoardService {
    private final BoardRepository boardRepository;

    public void save(BoardDTO boardDTO) {
        BoardEntity saveEntity = BoardEntity.toSaveEntity(boardDTO);
        boardRepository.save(saveEntity);
    }

    public List<BoardDTO> findAll() {
        List<BoardEntity> boardEntityList = boardRepository.findAll(); //리포지토리에서 뭘 가져오면 대부분 엔티티형태로 가져와짐
        List<BoardDTO> boardDTOList = new ArrayList<>();
        for(BoardEntity boardEntity: boardEntityList){
            boardDTOList.add(BoardDTO.toBoardDTO(boardEntity));
        }
        return boardDTOList;
    }
}
