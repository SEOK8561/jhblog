package com.cos.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.servlet.http.HttpServletResponse;

public class MyUtils {
	public static LocalDate StringToLocalDate(String target) {
		LocalDate result = 
				LocalDate.parse(
						target, 
						DateTimeFormatter.
						ofPattern("yyyy-MM-dd HH:mm:ss"));
		return result;
	}
	
	public static void script(String msg, HttpServletResponse response) {
		try {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('"+msg+"')");
			script.println("history.back()");
			script.println("</script>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void script(String msg, String url, HttpServletResponse response) {
		try {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('"+msg+"')");
			script.println("location.href='"+url+"'");
			script.println("</script>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
