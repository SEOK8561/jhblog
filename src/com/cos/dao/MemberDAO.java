package com.cos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.cos.domain.Board;
import com.cos.domain.Member;
import com.cos.dto.MemberEditDTO;
import com.cos.dto.BoardUpdateDTO;
import com.cos.util.DBManager;
import com.cos.util.MyUtils;

//Date Access Object
public class MemberDAO {
	
	//Injection 해킹 공격을 안당해!!
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	// 1 = 정상, -1 = 비정상
	public int save(Member member) {
		final String SQL = "INSERT INTO member VALUES(member_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)";
		Connection conn = DBManager.getConnection();
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, member.getUserID());
			pstmt.setString(2, member.getUserPassword());
			pstmt.setString(3, member.getUserEmail());
			pstmt.setString(4, member.getUserPhone());
			pstmt.setString(5, member.getUserGender());
			pstmt.setInt(6, member.getUserState());
			pstmt.setString(7, member.getCreateDate().toString());
			pstmt.setString(8, member.getUpdateDate().toString());
			pstmt.setString(9, member.getAddr1());
			pstmt.setString(10, member.getAddr2());
			pstmt.setString(11, member.getAddr3());
			
			//result = 0 fail, result = 1 success
			int result = pstmt.executeUpdate(); //트랜잭션 commit가지고 있다.
			return result;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//함수호출
			DBManager.close(conn, pstmt);
		}
		//서버 오류가 난거에요.
		return -1;
	}
	public int findByUserIDAndUserPassword(String userID, String userPassword) {
		final String SQL = "SELECT count(*) FROM member WHERE userID = ? AND userPassword = ?";
		Connection conn = DBManager.getConnection();
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			pstmt.setString(2, userPassword);
			rs = pstmt.executeQuery(); //rs는 첫번째 커서를 가리킨다.
			
			if(rs.next()) {
				int result= rs.getInt(1);//count(*)1이면 인증, 0이면 미인증
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return -1;//서버 에러
	}
	
	public int findByUserID(String userID) {
		final String SQL = "SELECT count(*) FROM member WHERE userID = ?";
		Connection conn = DBManager.getConnection();
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery(); //rs는 첫번째 커서를 가리킨다.
			
			if(rs.next()) {
				int result= rs.getInt(1);//count(*)1이면 인증, 0이면 미인증
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return -1;//서버 에러
	}
	public int edit(MemberEditDTO bDto) {

		final String SQL = "UPDATE member SET userPassword = ? , userEmail = ?, userPhone = ?, userGender = ?, updateDate = ? WHERE UserID = ?";
		Connection conn = DBManager.getConnection();
		try {
			pstmt = conn.prepareStatement(SQL);
			
			pstmt.setString(1, bDto.getUserPassword());
			pstmt.setString(2, bDto.getUserEmail());
			pstmt.setString(3, bDto.getUserPhone());
			pstmt.setString(4, bDto.getUserGender());
			pstmt.setString(5, LocalDate.now().toString());
			
			pstmt.setString(6, bDto.getUserID()); 
			
			
			//result = 0 fail, result = 1 success
			int result = pstmt.executeUpdate(); //트랜잭션 commit가지고 있다.
			return result;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//함수호출
			DBManager.close(conn, pstmt);
		}
		//서버 오류가 난거에요.
		return -1;
	}
	

}
