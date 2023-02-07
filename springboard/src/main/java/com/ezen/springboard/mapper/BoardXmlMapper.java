package com.ezen.springboard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.ezen.springboard.dto.Board;
import com.ezen.springboard.dto.BoardAndCommented;
import com.ezen.springboard.dto.Commented;

public interface BoardXmlMapper {
	
	// 글쓰기
	Integer addWrite(Board board);
	
	// 댓글쓰기
	Integer replyAdd(Commented comment);
	
	// 제목 클릭 후 화면
	Board getContents(Integer board_id);
	
	// 댓글 수정
	Integer replyModifyUpdate(Commented comment);
	
	List<Board> getAll();
	
	List<BoardAndCommented> getListAll();
	
	List<BoardAndCommented> selectReply(Integer board_id);
	
	// board 테이블 select
	Board modifyBoardById(Integer board_id);
	
	// commented 테이블 select
	Commented replyCommentById(Integer comment_no);
	
	// 글 수정
	Integer modifyUpdate(Board board);
	
	// Board 글삭제
	Integer deleteDo(Board board);

	// 댓글 삭제
	Commented replyDelete(Integer comment_no);
	
}
