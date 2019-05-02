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
<%@ include file="/base/header.jsp" %>
<form action="member?cmd=memberEditProc" method="POST" onsubmit="return validateJoin()">
	<input id="userID" type="text" name="userID" value="${sessionScope.userID}" readonly/><br />
	<input id="pw1" type="password" name="userPassword" placeholder="비밀번호" required/><br />
	<input id="pw2" type="password" placeholder="비밀번호 확인" required/><br />
	<input type="email" name="userEmail" placeholder="이메일" required/><br />
	<input type="text" name="userPhone" placeholder="전화번호" required/><br />
	남 : <input type="radio" name="userGender" value="남" checked/>
	여 : <input type="radio" name="userGender" value="여"/><br />
	
	<input type="submit" value="수정완료" />
</form>
</body>
</html>