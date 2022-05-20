package com.example.taegyungsite.controller;

import com.example.taegyungsite.model.BoardDto;
import com.example.taegyungsite.model.CommentDto;
import com.example.taegyungsite.service.BoardServiceImpl;
import com.example.taegyungsite.service.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * packageName : com.example.taegyungsite.controller
 * fileName : BoardApiController
 * author : ds
 * date : 2022-05-18
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-05-18         ds          최초 생성
 */
// @RestController : Json / Text 로 프론트엔드로 전송, Vue, React
// 비교) @Controller : Html, Jsp, 타임리프 파일로 바로 출력
// 명시적으로 api인걸 알 수 있게 api를 집어 넣음
// 예) http://localhost:8000/api/board/
// @RequestMapping : 위의 api를 모든 쉨히들한테 자동으로 설정 해줌
@RestController
@RequestMapping("/api")
public class BoardApiController {

//    @Autowired 이 샛기를 걸어줘야 스프링에서 객체를 받아서 아래 맴버 변수에 넣을 수 있음
    @Autowired
    BoardServiceImpl boardService;

    @Autowired
    CommentServiceImpl commentService;

    @GetMapping("/board/write/{idx}")
    public BoardDto openBoardWrite(@PathVariable("idx") Long idx){
//        상세목록 보기 서비스를 호출(Select : 1건)
        BoardDto detail = boardService.getBoardDetail(idx);

        return detail;
    }

//    @PostMapping : insert 할 때 사용하는 어노테이션
//    @RequestBody : 입력 테스트시 Json 형태로 데이터를 전송 할 수 있음
    @PostMapping("/board/register")
    public List<BoardDto> registerBoard(@RequestBody BoardDto boardDto){

        try{
//            insert 문 실행
            boolean isRegistered = boardService.registerBoard(boardDto);
        } catch (DataAccessException e) {
//            DB 관련된 에러는 여기로 들어옴
//            TODO => DB 처리 과정에 문제가 발생했다는 메세지를 출력
        } catch (Exception e) {
//            그 외 에러는 다 여기로 들어옴
//            TODO => 시스템에 문제가 발생했다는 메세지를 출력
        }
//        insert 완료 후 데이터 확인을 위한 전체 조회(Select)
        return boardService.getBoardAllList();
    }
    
//    @PutMapping : update 문 실행
    @PutMapping(value = "/board/delete/{idx}")
    public List<BoardDto> deleteBoard(@PathVariable("idx") Long idx){

//        삭제 서비스 호출
        boolean isDeleted = boardService.deleteBoard(idx);
        
//        삭제 되었는지 전체 조회 함
        return boardService.getBoardAllList();
    }

//    페이징 처리를 위한 게시물 검색 메뉴
    @GetMapping("/board/list/cur-page-no/{currentPageNo}/recods-per-page/{recordsPerPage}")
    public List<BoardDto> openBoardList(BoardDto params){

        return boardService.getBoardList(params);
    }

    //    페이징 처리를 위한 게시물 검색 메뉴2
    @GetMapping("/board/list/cpage/{currentPageNo}/rpage/{recordsPerPage}/sword/{searchKeyword}/stype/{searchType}")
    public List<BoardDto> openBoardList2(BoardDto params){

        return boardService.getBoardList(params);
    }

//    -------------------------------------------------
//    -------------------댓글 메뉴들---------------------
//    -------------------------------------------------

    @PostMapping("/board/comments")
    public List<CommentDto> registerBoard(@RequestBody CommentDto commentDto){

        boolean isRegistered = commentService.registerComment(commentDto);
        return commentService.getCommentList(commentDto);
    }
}
