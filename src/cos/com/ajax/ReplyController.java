package cos.com.ajax;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.domain.Reply;
import com.google.gson.Gson;

@WebServlet("/reply")
public class ReplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ReplyController() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		BufferedReader br = request.getReader();
		
		String res = "";
		StringBuffer bf = new StringBuffer();
		while((res = br.readLine()) != null) {
			bf.append(res);
		}

		System.out.println(bf.toString());
		Gson gson = new Gson();
		Reply reply = gson.fromJson(bf.toString(), Reply.class);
		System.out.println("reply : "+reply.getContent());
		System.out.println("reply : "+reply.getUserID());
		System.out.println("reply : "+reply.getBoardNum());
		
		
		//DB에 insert하고 out.print("ok");
		RestUtil r = new RestUtil();
		int result = r.save(reply);
		
		PrintWriter out = response.getWriter();
		
		if(result == 1) {
			out.print("ok");
		}else {
			out.print("error");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
