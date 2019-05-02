package cos.com.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/rest")
public class RestController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public RestController() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		RestUtil r = new RestUtil();
		String result = r.duplicateId(id);
		System.out.println(id);
		
		PrintWriter out = response.getWriter();
		out.print(result);	//println은 \n이 붙어있기때문에 동작하지 않는다.
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
