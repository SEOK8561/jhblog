<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:import url="/base/header.jsp"></c:import>
<%
	application.setAttribute("test","어플리케이션");
%>
<form action="member?cmd=memberLoginProc" method="POST">
<div class="container"><br>

	<input type="text" name="userID" placeholder="로그인" class="form-control" required/><br />
	<input type="password" name="userPassword" placeholder="비밀번호" class="form-control" required/><br />
	Remember me?<input type="checkbox" name="idSave" value="on" /><br />
	<input class="btn btn-dark" type="submit" value="로그인" />
</div>	
</form>
<c:import url="/base/footer.jsp"></c:import>
</body>
</html>