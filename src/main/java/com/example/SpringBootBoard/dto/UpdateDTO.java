package com.example.SpringBootBoard.dto;

import com.example.SpringBootBoard.entity.BoardEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter//게터세터는 롬복에서 자동으로 달아줌(어노테이션으로 써주면)
@Setter
@ToString //필드값 확인할 때 씀
@NoArgsConstructor //기본생성자 만들어줌
@AllArgsConstructor //모든 필드를 매개변수로 하는 생성자 만들어줌
public class UpdateDTO {
    private Long id;
    private String boardPass;
    private String boardTitle;
    private String boardContents;
    //이 클래스는 수정된 글을 받을 때 문제가 생기지 않도록 다른 변수를 배제한 클래스임.
    //때문에 Post 요청을 받을 때만 쓰니까, Entity -> DTO 함수는 사용할 일이 없어서 안만듦.

}
