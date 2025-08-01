package com.example.SpringBootBoard.service;

import com.example.SpringBootBoard.dto.BoardDTO;
import com.example.SpringBootBoard.entity.BaseEntity;
import com.example.SpringBootBoard.entity.BoardEntity;
import com.example.SpringBootBoard.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    @Transactional //이건 영속성 머시기 하는데 결국 JPA가 제공하는 게 아닌 우리가 튜닝해서 쓰는 함수니까 트랜잭션에 담아서 안정성을 챙기게 하는 거임
    //애초에 여기있는 다른 함수도 내부적으로 @Transactional가 붙음.
    public void updateHits(Long id) {
        boardRepository.updateHits(id);
    }

    public BoardDTO findById(Long id) {
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(id); //findById는 무조건 Optional 객체를 반환하기에 Optional을 쓰는 게 필수
        // Optional는 값이 있을 수도 없을 수도 있는 객체를 감싸는 wrapper임 -> 안전한 null 체크
        if(optionalBoardEntity.isPresent()){
            BoardEntity boardEntity = optionalBoardEntity.get();
            BoardDTO boardDTO = BoardDTO.toBoardDTO(boardEntity);
            return boardDTO;
        }
        else {
            return null;
        }
    }
}
