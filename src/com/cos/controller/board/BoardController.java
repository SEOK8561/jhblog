package com.cos.controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.controller.Action;
import com.cos.controller.ActionFactory;

//http://localhost:8000/blog/board
@WebServlet("/board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardController() {
        super();
    }

    //http://localhost:8000/blog/board?cmd=
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8"); 
		
		//파라메터 검증!!
		
		String cmd = null;
		request.getParameter("cmd");
		
		if(request.getParameter("cmd") != null) {
			cmd = request.getParameter("cmd");
		}
		
		ActionFactory af = ActionFactory.getInstance();
		Action action = af.getAction(cmd);
		if(action != null) action.execute(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}
	
}
