package com.example.SpringBootBoard.dto;

import lombok.*;

import java.time.LocalDateTime;



//DTO data transfer object -> 그냥 여기다가 from data 파라미터 여러개를 담아서 보내기 위한 클래스
@Getter//게터세터는 롬복에서 자동으로 달아줌(어노테이션으로 써주면)
@Setter
@ToString //필드값 확인할 때 씀
@NoArgsConstructor //기본생성자 만들어줌
@AllArgsConstructor //모든 필드를 매개변수로 하는 생성자 만들어줌
public class BoardDTO {
    private Long id;
    private String boardWriter;
    private String boardPass;
    private String boardTitle;
    private String boardContents;
    private int boardHits; //조회수
    private LocalDateTime boardCreatedTime;
    private LocalDateTime boardUpdatedTime;
}

//롬복을 쓰지 않았으면 아래 코드를 입력해야지 동일한 효과를 볼 수 있음
//참고로 모든 어노테이션이 롬복이랑 관련이 있는 건 아님. 스프링이 제공하는 어노테이션도 많음 ex) @Controller같은 거
/*
public class BoardDTO {
    private Long id;
    private String boardWriter;
    private String boardPass;
    private String boardTitle;
    private String boardContents;
    private int boardHits; // 조회수
    private LocalDateTime boardCreatedTime;
    private LocalDateTime boardUpdatedTime;

    // @NoArgsConstructor: 기본 생성자
    public BoardDTO() {
    }

    // @AllArgsConstructor: 모든 필드를 매개변수로 받는 생성자
    public BoardDTO(Long id, String boardWriter, String boardPass, String boardTitle,
                    String boardContents, int boardHits, LocalDateTime boardCreatedTime,
                    LocalDateTime boardUpdatedTime) {
        this.id = id;
        this.boardWriter = boardWriter;
        this.boardPass = boardPass;
        this.boardTitle = boardTitle;
        this.boardContents = boardContents;
        this.boardHits = boardHits;
        this.boardCreatedTime = boardCreatedTime;
        this.boardUpdatedTime = boardUpdatedTime;
    }

    // @Getter & @Setter: 각 필드의 getter/setter 메서드

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBoardWriter() {
        return boardWriter;
    }

    public void setBoardWriter(String boardWriter) {
        this.boardWriter = boardWriter;
    }

    public String getBoardPass() {
        return boardPass;
    }

    public void setBoardPass(String boardPass) {
        this.boardPass = boardPass;
    }

    public String getBoardTitle() {
        return boardTitle;
    }

    public void setBoardTitle(String boardTitle) {
        this.boardTitle = boardTitle;
    }

    public String getBoardContents() {
        return boardContents;
    }

    public void setBoardContents(String boardContents) {
        this.boardContents = boardContents;
    }

    public int getBoardHits() {
        return boardHits;
    }

    public void setBoardHits(int boardHits) {
        this.boardHits = boardHits;
    }

    public LocalDateTime getBoardCreatedTime() {
        return boardCreatedTime;
    }

    public void setBoardCreatedTime(LocalDateTime boardCreatedTime) {
        this.boardCreatedTime = boardCreatedTime;
    }

    public LocalDateTime getBoardUpdatedTime() {
        return boardUpdatedTime;
    }

    public void setBoardUpdatedTime(LocalDateTime boardUpdatedTime) {
        this.boardUpdatedTime = boardUpdatedTime;
    }

    // @ToString: 객체 정보를 문자열로 반환
    @Override
    public String toString() {
        return "BoardDTO{" +
                "id=" + id +
                ", boardWriter='" + boardWriter + '\'' +
                ", boardPass='" + boardPass + '\'' +
                ", boardTitle='" + boardTitle + '\'' +
                ", boardContents='" + boardContents + '\'' +
                ", boardHits=" + boardHits +
                ", boardCreatedTime=" + boardCreatedTime +
                ", boardUpdatedTime=" + boardUpdatedTime +
                '}';
    }
}

 */