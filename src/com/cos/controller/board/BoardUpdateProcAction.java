package com.cos.controller.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.controller.Action;
import com.cos.dao.BoardDAO;
import com.cos.dto.BoardUpdateDTO;
import com.cos.util.MyUtils;

public class BoardUpdateProcAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//업데이트 수행하고 -> update.jsp
		
		int num = Integer.parseInt(request.getParameter("num"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		String url = "board?cmd=boardView&num="+num;
		
		BoardUpdateDTO bDto = new BoardUpdateDTO();
		bDto.setNum(num);
		bDto.setTitle(title);
		bDto.setContent(content);
		
		BoardDAO boardDAO = new BoardDAO();
		
		int result = boardDAO.update(bDto);
		
		if(result == 1) {
			RequestDispatcher dis = 
					request.getRequestDispatcher(url);
			dis.forward(request, response);
		}else {
			MyUtils.script("error", response);
		}
		
	}
}
