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

public class BoardWriteAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//write.jsp 이동!!
		String url = "board/write.jsp";

		
		HttpSession session = request.getSession();
	    String sessionID = (String)session.getAttribute("userID");
	    
	    if(sessionID != null) {
		      RequestDispatcher dis = 
		          request.getRequestDispatcher(url);
		      dis.forward(request, response);
		    }else {
			      MyUtils.script("권한이 없습니다", response);
		    }
		
	}
}
