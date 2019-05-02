package com.cos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.cos.domain.Board;
import com.cos.dto.BoardUpdateDTO;
import com.cos.util.Code;
import com.cos.util.DBManager;
import com.cos.util.MyUtils;

//Date Access Object
public class BoardDAO {
	
	//Injection 해킹 공격을 안당해!!
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public List<Board> findAll(){
		final String SQL = "SELECT num, title, content, userID, readCount, createDate, updateDate FROM board ORDER BY num DESC";
		Connection conn = DBManager.getConnection();
		
		List<Board> list = new ArrayList<Board>();
		try {
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Board board = new Board();
				board.setNum(rs.getInt("num")); //컬럼명
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setUserID(rs.getString("userID"));
				board.setReadCount(rs.getInt("readCount"));
				
				//LocalDate로 타입변환
				board.setCreateDate(MyUtils.StringToLocalDate(rs.getString("createDate")));
				board.setUpdateDate(MyUtils.StringToLocalDate(rs.getString("updateDate")));
				
				list.add(board);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//함수호출
			DBManager.close(conn, pstmt, rs);
		}
		return null;
	}
	
	public List<Board> findAll(int start,int end){
		final String SQL = "SELECT (select count(*) from board),\r\n" + 
				"num, title, content, userid, readcount, createdate, updatedate, mynum\r\n" + 
				"from\r\n" + 
				"(\r\n" + 
				"SELECT\r\n" + 
				"num, title, content, userid, readcount, createdate, updatedate, rownum as mynum\r\n" + 
				"FROM board\r\n" + 
				"order by num DESC\r\n" + 
				")where mynum >? and mynum < ?";
		Connection conn = DBManager.getConnection();
		
		List<Board> list = new ArrayList<Board>();
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Board board = new Board();
				board.setNum(rs.getInt("num")); //컬럼명
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setUserID(rs.getString("userID"));
				board.setReadCount(rs.getInt("readCount"));
				
				//LocalDate로 타입변환
				board.setCreateDate(MyUtils.StringToLocalDate(rs.getString("createDate")));
				board.setUpdateDate(MyUtils.StringToLocalDate(rs.getString("updateDate")));
				//rs는 결과값을 가지고 잇는 객체가아니다.
				//rs는 커서를 가지고있다.  결과값에 첫번째 직전에 있음.
				//rs.next()는 커서를 한칸씩 옮기면서 DB에 있는 데이터를 가지고 오는 함숨
				//rs.isFirst(),rs.isLast()는 커서를 옮기는 함수가아님
				//커서가 현재 First에 있으면 True,커서가 현재 last에 있으며 True
				if(rs.isFirst()) {
					Code.setMaxListNum(rs.getInt(1));
					System.out.println("1MaxListNum="+rs.getInt(1));
				}
				list.add(board);
			}
			
			
			//System.out.println("2MaxListNum="+rs.getInt(1));
			return list;   
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//함수호출
			DBManager.close(conn, pstmt, rs);
		}
		return null;
	}
	
	// 1 = 정상, -1 = 비정상
	public int save(Board board) {
		final String SQL = "INSERT INTO board(num, title, content, userID, readCount, createDate, updateDate) VALUES(board_seq.nextval, ?, ?, ?, ?, ?, ?)";
		Connection conn = DBManager.getConnection();
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setString(3, board.getUserID());
			pstmt.setInt(4, board.getReadCount());
			pstmt.setString(5, board.getCreateDate().toString());
			pstmt.setString(6, board.getUpdateDate().toString());
			
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
	
	public Board findByID(int num) {
		final String SQL = "SELECT * FROM board WHERE num = ?";
		Connection conn = DBManager.getConnection();
		Board board = new Board();
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery(); //rs는 첫번째 커서를 가리킨다.
			
			if(rs.next()) {
				board.setNum(rs.getInt("num"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setUserID(rs.getString("userID"));
				board.setReadCount(rs.getInt("readCount"));
				board.setCreateDate(MyUtils.StringToLocalDate(rs.getString("createDate")));
				board.setUpdateDate(MyUtils.StringToLocalDate(rs.getString("updateDate")));
				return board;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return null;
	}
	
	//트랜잭션이 있는 DELETE, INSERT, UPDATE
	public int delete(int num) {
		final String SQL = "DELETE FROM board WHERE num = ?";
		Connection conn = DBManager.getConnection();
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, num);
			
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
	
	//조회수 증가
	public int updateReadCount(int num) {
		final String SQL = "UPDATE board SET readCount = readCount + 1 WHERE num = ?";
		Connection conn = DBManager.getConnection();
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, num);
			
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
	
	public int update(BoardUpdateDTO bDto) {

		final String SQL = "UPDATE board SET title = ?, content = ? WHERE num = ?";
		Connection conn = DBManager.getConnection();
		try {
			pstmt = conn.prepareStatement(SQL);
			
			pstmt.setString(1, bDto.getTitle());
			pstmt.setString(2, bDto.getContent());
			pstmt.setInt(3, bDto.getNum());
			
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
