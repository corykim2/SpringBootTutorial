package com.example.SpringBootBoard.repository;

import com.example.SpringBootBoard.dto.BoardDTO;
import com.example.SpringBootBoard.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//얘는 db랑 상호작용 하면서 데이터를 저장하고 꺼내는 역할
//JpaRepository 덕분에 save(), findAll(), findById() 등을 자동 제공함
//인터페이스로 이름만 정해주면 sql이랑 이런 걸 다 만들어준다는 뜻
public interface BoardRepository extends JpaRepository<BoardEntity, Long> { //엔티티 클래스 이름이랑 pk dataTpye을 넣어줘야 함
    //조회수 증가는 따로 쿼리를 짜줘야 함
    //update board_table set board_hits=board_hits+1 where id=?
    //여기서는 JPQL문법인데 엔티티와 엔티티 필드 이름을 기준으로 sql을 적용하는 거임
    @Query(value = "update BoardEntity b set b.boardHits=b.boardHits+1 where b.id=:id") //nativeQuery = true 해주면 sql로 짤 수도 있음
    //근데 여기서 엔티티를 조회하지 않고 지가 쿼리를 짜서 업데이트를 갈기는데 이게 어떻게 가능하냐면 엔티티 이름과 필드 이름으로 이게 가능하냐면 스프링이 처음에
    //엔티티를 다 메타데이터로 관리하는데 그걸 JPA가 보고서 알아서 매핑해서 쿼리를 짜는 거임.
    @Modifying //수정일 경우에는 이게 붙어야됨. @Query만 있으면 조회로 인식
    void updateHits(@Param("id") Long id);
    //아니면 @Transactional을 이용해서 엔티티 객체의 값을 변경하고 마지막에 JPA에서 자동으로 변경을 감지해서 업데이트를 날리게 하는 방법도 있음
    //이게 findById()를 통해서 객체를 영속상태로 만들고, 그 객체의 값을 수정해서 업뎃하는 방법.

    @Modifying
    @Query(value = "update BoardEntity b set b.boardTitle = :title, b.boardContents = :contents where b.id = :id")
    int update(@Param("id") Long id, @Param("title") String title, @Param("contents") String contents);
}