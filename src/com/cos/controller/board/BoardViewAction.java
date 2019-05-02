package com.cos.controller.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.controller.Action;
import com.cos.dao.BoardDAO;
import com.cos.domain.Board;
import com.cos.util.MyUtils;

public class BoardViewAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "board/view.jsp";
		
		//request.setAttribute() -> Object
		//request.getAttribute() -> Object -> EL표현식 ${}
		
		//request.getParameter() -> String
		//Integer.parseInt는 문자를 숫자로 변환해주는 함수이다.
		int num = Integer.parseInt(request.getParameter("num"));
		System.out.println("num : "+num);
		
		//session얻음
//		HttpSession session = request.getSession();
//		String sessionID = (String)session.getAttribute("userID");
//		System.out.println(session.getAttribute("userID"));
		
		
		//DAO -> Data Access Object 객체 생성 - 함수 만들어주고!!
		BoardDAO boardDAO = new BoardDAO();
		
		//상세보기시에 조회수 증가.
		int result = boardDAO.updateReadCount(num);
		
		//상세보기를 위해서 객체하나 들고옴.
		Board board = boardDAO.findByID(num);
		
		System.out.println(board.getUserID());
		//권한검사
//		if(!board.getUserID().equals(sessionID)) {
//			MyUtils.script("권한이없습니다", response);
//			return;
//		}
		
		if(board != null && result == 1) {
			request.setAttribute("board", board);
			RequestDispatcher dis =
					request.getRequestDispatcher(url);
			dis.forward(request, response);
		}else {
			MyUtils.script("server error","board?cmd=boardList", response);
		}
		
	}
}
