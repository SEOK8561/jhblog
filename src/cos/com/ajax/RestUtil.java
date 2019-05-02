package cos.com.ajax;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.cos.dao.MemberDAO;
import com.cos.domain.Board;
import com.cos.domain.Reply;
import com.cos.util.DBManager;
import com.cos.util.MyUtils;

public class RestUtil {
	
	private PreparedStatement pstmt;
	
	public String duplicateId(String userID) {
		MemberDAO memberDAO = new MemberDAO();
		int result = memberDAO.findByUserID(userID);
		if(result == 0) {
			return "ok";	//중복된 아이디가 없다.
		} else {
			return "error";	//중복된 아이디가 있다.
		}
	}
	
	public int save(Reply reply) {
		final String SQL = "INSERT INTO reply(num, content, userID, boardNum) VALUES(reply_seq.nextval, ?, ?, ?)";
		Connection conn = DBManager.getConnection();
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, reply.getContent());
			pstmt.setString(2, reply.getUserID());
			pstmt.setInt(3, reply.getBoardNum());
			
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
