<%@page import="groupware.beans.employeesDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>그룹웨어 로그인</title>
<style>
.error{
color:red;
}
</style>

</head>
<body>

<h1>GroupWare</h1>
<form action="confirm" method="post">
 아이디 : <input type="text" name="empNo" placeholder="아이디 입력" required><br>
비밀번호 : <input type="password" name="empPw" placeholder="비밀번호 입력" required> <br>
<div class = "error">
아이디 혹은 비밀번호가 잘못되었습니다.
</div>
<input type="submit" value="로그인">

<a href = "http://localhost:8080/groupware/login/signUp.jsp">회원가입(임시)</a>

</form>
</body>
</html>