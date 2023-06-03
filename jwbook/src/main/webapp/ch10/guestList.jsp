<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
<meta charset="UTF-8">
<title>목록</title>
<style>
	h2{ text-align: center;}
</style>
</head>
<body>
	<div class="position-absolute top-0 start-50 translate-middle-x">
	<h2>방명록 정보</h2>
	
	<hr>
	<table class="table table.bordered w-auto" border="1">
		<tr>
			<th>id</th>
			<th>작성자</th>
			<th>이메일</th>
			<th>날짜</th>
			<th>제목</th>

		</tr>
		<c:forEach items="${guestList}" var="g">
			<tr class="table-success">
				<td>${g.id}</td>
				<td>${g.writer}</td>
				<td>${g.email}</td>
				<td>${g.date}</td>
				<td><a href="guestControl?action=getGuest&id=${g.id}">${g.title}</a></td>
			</tr>
		</c:forEach>
	</table>
	<hr>
	<div class="col text-center">
		<button class="btn btn-outline-info mb-3" type="button" onclick="location.href='/jwbook/guestControl?action=insert'" >작성자 등록</button>
	</div>
	
	</div>
	
</body>
</html>