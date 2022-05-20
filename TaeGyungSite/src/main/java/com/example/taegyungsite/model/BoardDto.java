package com.example.taegyungsite.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * packageName : com.example.taegyungsite.model
 * fileName : BoardDto
 * author : ds
 * date : 2022-05-17
 * description : 개발 순서 : Model -> Dao -> xml -> Service -> Controller
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-05-17         ds          최초 생성
 */
// 롬북 라이브러리에서 제공하는 어노테이션
@Setter
@Getter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class BoardDto extends CommonDto{
//    번호(PK : 기본 키)
    private Long idx;
//    제목
    private String title;
//    내용
    private String content;
//    작성자
    private String writer;
//    조회수
    private int viewCnt;
//    공지 여부(Y, N)
    private String noticeYn;
//    비밀 여부(Y, N)
    private String secretYn;
}
