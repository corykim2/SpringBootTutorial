package com.example.SpringBootBoard.repository;

import com.example.SpringBootBoard.dto.BoardDTO;
import com.example.SpringBootBoard.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

//얘는 db랑 상호작용 하면서 데이터를 저장하고 꺼내는 역할
//JpaRepository 덕분에 save(), findAll(), findById() 등을 자동 제공함
//인터페이스로 이름만 정해주면 sql이랑 이런 걸 다 만들어준다는 뜻
public interface BoardRepository extends JpaRepository<BoardEntity, Long> { //엔티티 클래스 이름이랑 pk dataTpye을 넣어줘야 함
}
