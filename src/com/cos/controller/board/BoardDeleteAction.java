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

//board?cmd=boardDelete&num=15
public class BoardDeleteAction implements Action{
  @Override
  public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String url = "index.jsp";
    BoardDAO boardDAO = new BoardDAO();
   
    
    
    //num은 pk = 유일함.
    int num = Integer.parseInt(request.getParameter("num"));

    
    Board board = boardDAO.findByID(num);
    
    HttpSession session = request.getSession();
    String sessionID = (String)session.getAttribute("userID");
    
    //삭제하기전에 권한이있는지 확인및 아이디를 비교를해야됨
	if(!(board.getUserID().equals(sessionID)||("Admin".equals(sessionID)))) {
	MyUtils.script("권한이없습니다", response);
	return;
	}else {
		int result = boardDAO.delete(num);
	    
	    
		  
	    if(result == 1) {
	      RequestDispatcher dis = 
	          request.getRequestDispatcher(url);
	      dis.forward(request, response);
	    }else if(result == 0) {
	      MyUtils.script("DB error", response);
	    }else {
	      MyUtils.script("Server error", response);
	    }
		
	} 
  }
}