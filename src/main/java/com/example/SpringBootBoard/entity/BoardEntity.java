package com.example.SpringBootBoard.entity;

import com.example.SpringBootBoard.dto.BoardDTO;
import com.example.SpringBootBoard.dto.UpdateDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
//db랑 1대1로 매핑돼서 이 객체를 통해서 db를 관리함
//Spring Data JPA 에서는 필수적으로 써야 함
//흐름은 DTO를 통해서 form을 받아오고, 그걸 toEntity() 매서드를 만들어서 엔티티 객체를 생성, 그 엔티티 객체를 통해서 save

@Entity //이게 있어야지 스프링이 엔티티로 인식함
@Getter
@Setter
@Table(name = "board_table") //저 이름의 테이블에 매핑해줌, db에 이 이름의 table이 없으면 알아서 만들어줌 -> 이게 없으면 이름 때문에 오류 날 수도 있음
public class BoardEntity extends BaseEntity {
    @Id //pk 필드라는 거임 -> 필수적으로 1개 필요
    @GeneratedValue(strategy = GenerationType.IDENTITY) //오토 인크리먼트
    private Long id; //여기까지 3줄이 하나의 필드를 만든 것

    @Column(length = 20, nullable = false) //디폴트는 255에 null 가능임
    private String boardWriter;

    @Column
    private String boardPass;

    @Column
    private String boardTitle;

    @Column
    private String boardContents;

    @Column
    private int boardHits;
    
    //시간에 대한 건 따로 구분해서 클래스를 만듦(상속해서 사용)

    public static BoardEntity toSaveEntity(BoardDTO boardDTO){ //엔티티로 바꿔주는 함수를 여기에 만듦(빌더패턴으로도 가능하다넴)
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setBoardWriter(boardDTO.getBoardWriter());
        boardEntity.setBoardPass(boardDTO.getBoardPass());
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardContents(boardDTO.getBoardContents());
        boardEntity.setBoardHits(0);
        return boardEntity;
    }
}
