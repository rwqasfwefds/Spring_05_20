package com.example.taegyungsite.service;

import com.example.taegyungsite.dao.CommentDao;
import com.example.taegyungsite.model.CommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * packageName : com.example.taegyungsite.service
 * fileName : CommentServiceImpl
 * author : ds
 * date : 2022-05-20
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-05-20         ds          최초 생성
 */
@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentDao commentDao;

    @Override
    public boolean registerComment(CommentDto params) {
        int queryResult = 0;

//        댁슬 새글 쓰기 : idx(댓글 번호)가 null이면 새글 쓰기가 됨
        if(params.getIdx() == null){
            queryResult = commentDao.insertComment(params);
        } else {
            queryResult = commentDao.updateComment(params);
        }

        return (queryResult == 1) ? true : false;
    }

//    댓글 삭제 서비스 구현
    @Override
    public boolean deleteComment(Long idx) {
        int queryResult = 0;
        
//        데이터가 있는지 없는지 확인
        CommentDto comment = commentDao.selectCommentDetail(idx);
        
//        comment에 null이 아니면 댓글번호가 있음 + Delete_YN = 'N' 삭제 진행
        if(comment != null && "N".equals(comment.getDeleteYn())){
            queryResult = commentDao.deleteComment(idx);
        }

        return (queryResult == 1) ? true : false;
    }

    @Override
    public List<CommentDto> getCommentList(CommentDto params) {
//        빈 값으로 초기화
        List<CommentDto> commentList = Collections.emptyList();
        
//        댓글 테이블에 데이터가 있는지 없는지 먼저 확인
//        주건 : 게시물 번호 => 댓글이 있어야 하기 때문에
//        참조 무결성 체크
        int commentTotalCount = commentDao.selectCommentTotalCount(params);

        if(commentTotalCount > 0){
            commentList = commentDao.selectCommentList(params);
        }
        
        return commentList;
    }

    @Override
    public CommentDto getCommentDetail(Long idx) {

        CommentDto commentDto = commentDao.selectCommentDetail(idx);

        return commentDto;
    }
}
