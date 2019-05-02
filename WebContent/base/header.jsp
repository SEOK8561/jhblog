<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>

<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet"
		href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<!-- 메뉴바 시작 -->
	<nav class="navbar navbar-expand-md bg-dark navbar-dark">
		<!-- Brand -->
		<a class="navbar-brand" href="index.jsp">jhblog</a>

		<!-- Toggler/collapsibe Button -->
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#collapsibleNavbar">
			<span class="navbar-toggler-icon"></span>
		</button>

		<!-- Navbar links -->
		<div class="collapse navbar-collapse" id="collapsibleNavbar">		
			<ul class="navbar-nav">	
				<c:choose>
						<c:when test="${empty sessionScope.userID}">
						<li class="nav-item">
							<a class="nav-link" href="/jhblog/member?cmd=memberLogin">로그인</a></li>
							<li class="nav-item"><a class="nav-link" href="/jhblog/member?cmd=memberJoin">회원가입</a></li>
						</c:when>
						<c:otherwise>
						<li class="nav-item">
							<a class="nav-link" href="/jhblog/member?cmd=memberLogout">로그아웃</a></li>
							<li class="nav-item"><a class="nav-link" href="/jhblog/member?cmd=memberEdit">회원정보수정</a></li>
						</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</nav>
	<!-- 메뉴바 끝 -->