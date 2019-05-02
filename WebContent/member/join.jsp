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
<form action="member?cmd=memberJoinProc" method="POST" onsubmit="return validateJoin()" name="form" id="form">
	<input id="userID" type="text" name="userID" placeholder="아이디" required/> <input type="button" value="중복확인" onclick="validateDuplicateID()"><br />
	<input id="pw1" type="password" name="userPassword" placeholder="비밀번호" required/><br />
	<input id="pw2" type="password" placeholder="비밀번호 확인" required/><br />
	<input type="email" name="userEmail" placeholder="이메일" required/><br />
	<input type="text" name="userPhone" placeholder="전화번호" required/><br />
	남 : <input type="radio" name="userGender" value="남" checked/>
	여 : <input type="radio" name="userGender" value="여"/><br />
	<input type="button" onClick="goPopup();" value="팝업_domainChk"/>
	<div id="list"></div>
	<div id="callBackDiv">
		<table>
			<tr><td>도로명주소 전체(포멧)</td><td><input type="text"  style="width:500px;" id="roadFullAddr"  name="roadFullAddr" /></td></tr>
			<tr><td>도로명주소           </td><td><input type="text"  style="width:500px;" id="roadAddrPart1"  name="roadAddrPart1" /></td></tr>
			<tr><td>고객입력 상세주소    </td><td><input type="text"  style="width:500px;" id="addrDetail"  name="addrDetail" /></td></tr>
		</table>
	</div>
	<input type="submit" class="btn btn-dark" value="회원가입" />
</form>

<script>
	let check = 1;
	function loadAjax(userID) {
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				alert(this.responseText);
				if(this.responseText == "ok"){
					alert("중복된 아이디가 없습니다");
					check = 0;
				}else{
					alert("중복된 아이디가 있습니다");
					check = 1;
				}			
			}
		};
		xhttp.open("GET", "rest?userID="+userID , true);
		xhttp.send();
	}
	
	function validateDuplicateID() {
		var userID_element = document.querySelector("#userID");
		var userID = userID_element.value;
		
		loadAjax(userID);
	}

	function validateJoin() {
		if(check ==1){
			return false;
		}
		var pw1 = document.querySelector("#pw1");
		var pw2 = document.querySelector("#pw2");
		console.log(pw1);
		console.log(pw1.value);
		console.log(pw2.value);

		if (pw1.value === pw2.value) {
			return true;
		} else {
			pw1.value = "";
			pw2.value = "";
			pw1.focus();
			alert("비밀번호가 일치하지 않습니다.")
			return false;
		}
	}
	function goPopup(){
		// 주소검색을 수행할 팝업 페이지를 호출합니다.
		// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(http://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
		var pop = window.open("/jhblog/member/jusoPopup.jsp","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
		
		// 모바일 웹인 경우, 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(http://www.juso.go.kr/addrlink/addrMobileLinkUrl.do)를 호출하게 됩니다.
	    //var pop = window.open("/popup/jusoPopup.jsp","pop","scrollbars=yes, resizable=yes"); 
	}


	function jusoCallBack(roadFullAddr,roadAddrPart1,addrDetail){
			// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
			document.form.roadFullAddr.value = roadFullAddr;
			document.form.roadAddrPart1.value = roadAddrPart1;
			document.form.addrDetail.value = addrDetail;
	}
</script>
<c:import url="/base/footer.jsp"></c:import>
</body>
</html>