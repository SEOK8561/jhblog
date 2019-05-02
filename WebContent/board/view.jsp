<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="/base/header.jsp"></c:import>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link rel="stylesheet" href="/jhblog/css/styles.css" >
</head>
<body>

<a href="board?cmd=boardDelete&num=${board.num}" >삭제</a>
<a href="board?cmd=boardUpdate&num=${board.num}" >수정</a>
<a href="board?cmd=boardListPage&num=${board.num}" >back</a>
<table border="1">
    	<tr>
			<td>번호</td>
			<td>제목</td>
			<td>내용</td>
			<td>작성자아이디</td>
			<td>조회수</td>
			<td>작성일</td>
			<td>수정일</td>
		</tr>
		<tr>	
			<td>${board.num}</td>
			<td>${board.title}</td>
			<td>${board.content}</td>
			<td>${board.userID}</td>
			<td>${board.readCount}</td>
			<td>${board.createDate}</td>
			<td>${board.updateDate}</td>
		</tr>	
	</table>
	<textarea id="reply" rows="3" cols="45"></textarea>
	<button onclick="boardReplyWrite()">댓글입력</button> <br />
	<div class="reply-box">
		<!--  for문이 돌아야 하는 곳  -->
		<div class="reply-item">
			<span class="reply-userid">ssar</span> 
			<span class="reply-createdate">2019-05-02 10:00:53</span>
			<div class="reply-delete">
				<i class="material-icons">clear</i>
			</div>
			<p class="reply-content">반갑습니다.</p>
		</div>
	</div>

	<c:import url="/base/footer.jsp"></c:import>
	<script>
		function boardReplyWrite(){
			//댓글
			let reply_el = document.querySelector("#reply");
			let content = reply_el.value;
			//아이디
			let userID = "${sessionScope.userID}";
			//시간 2019-05-02 14:21:05
			let createDate = currentDate();
			//게시글 번호
			let boardNum = "${board.num}";
			//넘길 객체
			let replyDto = {
					"content": content,
					"userID": userID,
					"boardNum": boardNum
			};
			//서버구축되면 fetch()돌리기
			fetch("http://localhost:8000/jhblog/reply", {
				method:"POST",
				headers: {
					"Accept" : "text/plain",
					"Content-Type" : "application/json"
				},
				body : JSON.stringify(replyDto)
			}).then(function(res){
				return res.text();
			}).then(function(result){
				if(result == "ok"){
					let newDiv = document.createElement("div");
					newDiv.className = "reply-item";
					newDiv.innerHTML = '<span class="reply-userid">'+userID+'</span><span class="reply-createdate">'+createDate+'</span><div class="reply-delete"><i class="material-icons">clear</i></div><p class="reply-content">'+content+'</p>';
					document.querySelector(".reply-box").prepend(newDiv);
				}
			});
		}
	</script>
	<script src="/jhblog/js/util.js" ></script>
</body>
</html>