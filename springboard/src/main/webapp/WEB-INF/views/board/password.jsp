<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:choose>
		<c:when test="${type == 'modify' }">
		${type }비밀번호를 입력해 주세요. <br>
			<form action="./modify" method="POST">
				<input type="password" name="user_pw" /> 
				<input type="hidden" name="board_id" value="${board_id }"/>
				<input type="submit" value="confirm"   />
			</form>
		</c:when>
	
		<c:when test="${type == 'delete' }">
		${type }비밀번호를 입력해 주세요. <br>
			<form action="./delete" method="POST">
				<input type="password" name="user_pw" /> 
				<input type="hidden" name="board_id" value="${board_id }" />
				<input type="submit" value="confirm" onclick="return confirm('비밀번호가 정확합니까?');"/>
			</form>
		</c:when>
	
		<c:when test="${type == 'reply' }">
		${type } 비밀번호를 입력해 주세요.<br>
			<form action="./replyModify" method="POST">
				<input type="password" name="user_pw" />
				<input type="hidden" name="board_id" value=${board_id } />
				<input type="hidden" name="comment_no" value=${comment_no }  />
				<input type="submit" value="confirm" onclick="return confirm('비밀번호가 정확합니까?');" />	
			</form>
		</c:when>
		<c:when test="${type == 'replyDelete' }">
		${type } 비밀번호를 입력해 주세요.<br>
			<form action="./replyDelete" method="POST">
				<input type="password" name="user_pw" />
				<input type="hidden" name="board_id" value=${board_id } />
				<input type="hidden" name="comment_no" value=${comment_no } />
				<input type="submit" value="confirm" onclick="return confirm('비밀번호가 정확합니까?');" />	
			</form>
		</c:when>
	
		<c:otherwise>
			잘못된 접근입니다.
			<a href="./list">목록으로 돌아가기</a>
		</c:otherwise>
	</c:choose>
</body>
</html>