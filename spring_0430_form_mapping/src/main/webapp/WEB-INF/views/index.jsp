<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	post 방식
	<form action="student" method="post">
		student id : <input type="text" name="id"> <br />
		<input type="submit" value="전송">
	</form>
	
	get 방식
	<form action="student" method="get">
		student id : <input type="text" name="id"> <br />
		<input type="submit" value="전송">
	</form>	
</body>
</html>