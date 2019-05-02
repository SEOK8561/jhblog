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
import com.cos.dto.MemberEditDTO;
import com.cos.util.MyUtils;
import com.cos.util.SHA256;

public class MemberEditProcAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "index.jsp";

		// 회원정보수정 수행 로직!!
		String userPassword = request.getParameter("userPassword");
		String userEmail = request.getParameter("userEmail");
		String userPhone = request.getParameter("userPhone");
		String userGender = request.getParameter("userGender");
		String userID = request.getParameter("userID");

		// 랜덤으로 만들어짐 -> DB에 salt값을 저장
		// String salt = SHA256.generateSalt();
		String salt = "cos";
		userPassword = SHA256.getEncrypt(userPassword, salt);

		MemberEditDTO member = new MemberEditDTO();
		member.setUserPassword(userPassword);
		member.setUserEmail(userEmail);
		member.setUserPhone(userPhone);
		member.setUserGender(userGender);
		member.setUserID(userID);
		

		MemberDAO memberDAO = new MemberDAO();
		int result = memberDAO.edit(member);

		if (result == 1) {
			RequestDispatcher dis = request.getRequestDispatcher(url);
			dis.forward(request, response);
		} else if (result == 0) {
			MyUtils.script("DB오류", response);
		} else if (result == -1) {
			MyUtils.script("서버오류", response);
		}

	}

}
