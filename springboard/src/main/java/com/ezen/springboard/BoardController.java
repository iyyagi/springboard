package com.ezen.springboard;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ezen.springboard.dto.Board;
import com.ezen.springboard.dto.BoardAndCommented;
import com.ezen.springboard.dto.Commented;
import com.ezen.springboard.mapper.BoardXmlMapper;


import lombok.extern.log4j.Log4j2;
import oracle.net.aso.b;


@Log4j2
@Controller
@RequestMapping("/")
public class BoardController {

	@Autowired
	BoardXmlMapper board_mapper;

	@GetMapping("/index")
	public String board(Model model) {

		return "board/index";
	}

	@GetMapping("/list")
	public String boardList(Model model, HttpServletRequest req) {
		String pageStr = req.getParameter("page");
		int page;
		List<Board> boards = board_mapper.getAll();

		if (pageStr == null) {
			page = 1;
		} else {
			page = Integer.parseInt(pageStr);
		}

		int page_size = 10;
		int board_size = boards.size();
		int start_index = (page - 1) * page_size;
		int end_index = page * page_size;
		end_index = end_index > board_size ? board_size : end_index;

		int max_page = board_size % page_size == 0 ?  board_size / page_size : board_size / page_size + 1;
		int pagination_start = (page / page_size) * page_size + 1;
		int pagination_end = (page / page_size + 1) * page_size;
		pagination_end = pagination_end > max_page ? max_page : pagination_end;

		model.addAttribute("boards", board_mapper.getListAll().subList(start_index, end_index));
		model.addAttribute("pagination_start", pagination_start);
		model.addAttribute("pagination_end", pagination_end);
		// service로 변경해보기
		return "board/list";
	}

	@GetMapping("/write")
	public String writeForm() {

		return "board/write_form";
	}

	@PostMapping("/write")
	public String completeWrite(Model model, Board board) {
		int row = board_mapper.addWrite(board);
	

		return "redirect:/list";
	}

	@GetMapping("/contents")
	public String clickContents(Model model, int board_id) {

		model.addAttribute("board", board_mapper.getContents(board_id));
		model.addAttribute("replys", board_mapper.selectReply(board_id));
		return "board/contents";
	}

	@PostMapping("/reply")
	public String replyAdd(HttpServletRequest req, Commented comment) {
		int board_id = Integer.parseInt(req.getParameter("board_id"));
		int row = board_mapper.replyAdd(comment);
	
		return "redirect:/contents?board_id=" + board_id;
	}

	@GetMapping("/modify")
	public String modifyForm(HttpServletRequest req) {

		int board_id = Integer.parseInt(req.getParameter("board_id"));
		req.setAttribute("type", "modify");
		req.setAttribute("board_id", board_id);

		return "board/password";
	}

	@PostMapping("/modify")
	public String modifyPasswordCheck(HttpServletRequest req, Model model, Board board) {

		int board_id = Integer.parseInt(req.getParameter("board_id"));
		String user_pw = req.getParameter("user_pw");

		board = board_mapper.modifyBoardById(board_id);

		if (board.getWriter_pw().equals(user_pw)) {
			model.addAttribute("board", board_mapper.modifyBoardById(board_id));
			return "board/modi_form";
		} else {
			return "redirect:/list";
		}
	}

	@PostMapping("/modify/do")
	public String modifyDoUpdateService(HttpServletRequest req, Board board) {
		int board_id = Integer.parseInt(req.getParameter("board_id"));
		String write_title = req.getParameter("write_title");
		String write_content = req.getParameter("write_content");
		
		board.setBoard_id(board_id);
		board.setWrite_title(write_title);
		board.setWrite_content(write_content);

		board_mapper.modifyUpdate(board);

		return "redirect:/contents?board_id=" + board_id;
	}

	@GetMapping("/delete")
	public String deletePasswordCheck(HttpServletRequest req, Board board) {

		int board_id = Integer.parseInt(req.getParameter("board_id"));
		req.setAttribute("type", "delete");
		req.setAttribute("board_id", board_id);

		return "board/password";
	}

	@PostMapping("/delete")
	public String deleteDoSuccess(HttpServletRequest req, Board board) {
		int board_id = Integer.parseInt(req.getParameter("board_id"));
		String user_pw = req.getParameter("user_pw");

		board = board_mapper.modifyBoardById(board_id);

		if (board != null & board.getWriter_pw().equals(user_pw)) {
			board_mapper.deleteDo(board);
			return "redirect:/list";
		} else {
			return "redirect:/contents?board_id=" + board_id;
		}
	}

	@GetMapping("/replyModify")
	public String replyCheckPassword(HttpServletRequest req) {
		int board_id = Integer.parseInt(req.getParameter("board_id"));
		int comment_no = Integer.parseInt(req.getParameter("no"));
		
		req.setAttribute("type", "reply");
		req.setAttribute("board_id", board_id);
		req.setAttribute("comment_no", comment_no);
		
		return "board/password";
	}

	@PostMapping("/replyModify")
	public String replyModifySuccess(HttpServletRequest req, Model model, Commented comment) {
		int board_id = Integer.parseInt(req.getParameter("board_id"));
		int comment_no = Integer.parseInt(req.getParameter("comment_no"));
		String user_pw = req.getParameter("user_pw");
		log.info(comment_no);
		comment = board_mapper.replyCommentById(comment_no);

		if (comment.getComment_pw().equals(user_pw)) {
			model.addAttribute("commented", board_mapper.replyCommentById(comment_no));
			req.setAttribute("board_id", board_id);
			return "board/reply_form";
		} else {
			return "redirect:/list";
		}
	}
	
	@PostMapping("/replyDo")
	public String replyModifyDoUpdate(HttpServletRequest req, Commented comment) {
		int board_id = Integer.parseInt(req.getParameter("board_id"));	
		String comment_view = req.getParameter("comment_view");

		comment.setComment_view(comment_view);
		
		board_mapper.replyModifyUpdate(comment);
		
		return "redirect:/contents?board_id=" + board_id;
	}
	
	@GetMapping("/replyDelete")
	public String replyDeleteCheckPassword(HttpServletRequest req) {
		int board_id = Integer.parseInt(req.getParameter("board_id"));
		int comment_no = Integer.parseInt(req.getParameter("no"));
		req.setAttribute("type", "delete");
		req.setAttribute("board_id", board_id);
		req.setAttribute("comment_no", comment_no);
		
		return "board/password";
	}
	
	@PostMapping("/replyDelete")
	public String replyDeleteSuccess(HttpServletRequest req, Commented comment) {
		int board_id = Integer.parseInt(req.getParameter("board_id"));
		int comment_no = Integer.parseInt(req.getParameter("comment_no"));
		String user_pw = req.getParameter("user_pw");
		
		comment = board_mapper.replyCommentById(comment_no);
		if (comment != null & comment.getComment_pw().equals(user_pw)) {
			board_mapper.replyDelete(comment_no);
			return "redirect:/contents?board_id=" + board_id;
		} else {
			return "redirect:/contents";
		}
		
	}
	
}
