package com.cos.controller.member;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.controller.Action;
import com.cos.dao.MemberDAO;
import com.cos.domain.Member;
import com.cos.util.MyUtils;
import com.cos.util.SHA256;

public class MemberJoinProcAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "index.jsp";
		
		//회원가입 수행 로직!!
		String userID = request.getParameter("userID");
		String userPassword = request.getParameter("userPassword");
		String userEmail = request.getParameter("userEmail");
		String userPhone = request.getParameter("userPhone");
		String userGender = request.getParameter("userGender");
		String addr1 = request.getParameter("roadFullAddr");
		String addr2 = request.getParameter("roadAddrPart1");
		String addr3 = request.getParameter("addrDetail");
		
		//랜덤으로 만들어짐 -> DB에 salt값을 저장
		//String salt = SHA256.generateSalt();
		String salt = "cos";
		userPassword = SHA256.getEncrypt(userPassword, salt);
		
		Member member = new Member();
		member.setUserID(userID);
		member.setUserPassword(userPassword);
		member.setUserEmail(userEmail);
		member.setUserPhone(userPhone);
		member.setUserGender(userGender);
		member.setUserState(1); //1활성, 0휴먼
		member.setCreateDate(LocalDate.now());
		member.setUpdateDate(LocalDate.now());
		member.setAddr1(addr1);
		member.setAddr2(addr2);
		member.setAddr3(addr3);
		
		
		MemberDAO memberDAO = new MemberDAO();
		int result = memberDAO.save(member);
		
		if(result == 1) {
			RequestDispatcher dis = 
					request.getRequestDispatcher(url);
			dis.forward(request, response);
		}else if(result == 0) {
			MyUtils.script("DB오류", response);
		}else if(result == -1) {
			MyUtils.script("서버오류", response);
		}

	}
	
}
