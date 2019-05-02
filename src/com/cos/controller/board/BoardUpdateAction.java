package com.cos.controller.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.controller.Action;
import com.cos.dao.BoardDAO;
import com.cos.domain.Board;
import com.cos.util.MyUtils;

public class BoardUpdateAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//DB에서 회원정보가져오고 -> request에 담아서 -> update.jsp
		String url = "board/update.jsp";
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		BoardDAO boardDAO = new BoardDAO();
		Board board = boardDAO.findByID(num);
		
		request.setAttribute("board", board);
		
		if(board != null) {
			RequestDispatcher dis = 
					request.getRequestDispatcher(url);
			dis.forward(request, response);
		}else {
			MyUtils.script("에러", response);
		}
	}
}
