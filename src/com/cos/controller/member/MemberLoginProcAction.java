package com.cos.controller.member;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.controller.Action;
import com.cos.dao.MemberDAO;
import com.cos.domain.Member;
import com.cos.util.MyUtils;
import com.cos.util.SHA256;

public class MemberLoginProcAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "index.jsp";
		
		//회원가입 수행 로직!!
		String userID = request.getParameter("userID");
		String userPassword = request.getParameter("userPassword");
		String idSave = request.getParameter("idSave");

		if(idSave != null) {
			if(idSave.equals("on")) {
				Cookie cookie = new Cookie("cookieID",userID);
				cookie.setMaxAge(3600);
				response.addCookie(cookie);
			}
		}else {
			Cookie cookie = new Cookie("cookieID",null);
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}	
		
		//userPassword 암호화 해서 DB
		String salt = "cos";
		userPassword = SHA256.getEncrypt(userPassword, salt);
		
		
		
		MemberDAO memberDAO = new MemberDAO();
		int result = memberDAO.findByUserIDAndUserPassword(userID,userPassword);
		
		if(result == 1) {
			//로그인완료 세션
			HttpSession session = request.getSession();
			session.setAttribute("userID", userID);
			
			//index페이지로이동
			RequestDispatcher dis =
					request.getRequestDispatcher(url);
			
			dis.forward(request, response);
		}else if(result == 0) {
			MyUtils.script("아이디 혹은 비밀번호가 일치하지 않습니다", response);
		}else{
			MyUtils.script("서버오류", response);
		}

	}
	
}
