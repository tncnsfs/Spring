<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<ul>
		<%-- <li>번호:${board.seq } 를 그냥 seq 로 수정 </li> --%>
		<li>번호: ${board.seq }</li>
		<li>제목: ${board.title }</li>
		<li>작성자: ${board.writer }</li>		
		<li><a href="board_download?fname=${board.fname}">${board.fname}</a></li>
		<li>내용: ${board.contents }</li>
		<li>
		
		
		</li>
	</ul>
	
</body>
</html>










