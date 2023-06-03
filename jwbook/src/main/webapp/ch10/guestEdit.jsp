<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
	
<meta charset="UTF-8">
<title>수정</title>
<style>
	h2{ text-align: center;}
</style>
</head>
<body>
	<div class ="position-absolute top-0 start-50 translate-middle-x" >
			<h2>방명록 수정</h2>
			<br>
			<form method="post" action="/jwbook/guestControl?action=change&id=${guestList.id}">
				<table class = "table table-striped" >
					<tr>
						<th>작성자</th>
						<td><input type="text" value ="${guestList.writer}" name="writer"><br></td>
					</tr>
					<tr>
						<th>이메일</th>
						<td><input type="text" value ="${guestList.email}" name="email"><br></td>
					</tr>
					<tr>
						<th>제목</th>
						<td><input type="text" value ="${guestList.title}" name="title"><br></td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td><input type="text" value ="${guestList.password}" name="password"><br></td>
					</tr>
				
				</table>

				<textArea cols="33" rows="5" name="content">${guestList.content}</textArea><br>
				
				<c:if test="${error != null}">
					<div class = "alert alert-danger alert-dismissible fade show mt-3">
						에러발생: ${error}
						<button type ="button" class="btn-close" data-bs-dismiss="alert"></button>
					</div>
				</c:if>
				
				<div class="col text-center">
				<button type="submit"  class="btn btn-outline-info mb-3">수정</button>
				<button type="button"  class="btn btn-outline-info mb-3" onclick = "location.href='guestControl?action=deleteGuest&id=${guestList.id}'">삭제</button>
				<button type="submit"  class="btn btn-outline-info mb-3" onclick= "location.href='guestControl?action=menu'">목록</button>
				</div>
			</form>
		</div>
</body>
</html>