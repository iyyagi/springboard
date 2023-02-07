<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>댓글 수정하기</title>
</head>
<body>
	<form action="./replyDo" method="POST">
		<input type="hidden" name="board_id" value=${board_id } />
		<input type="hidden" name="comment_no" value="${commented.comment_no }" />
		글쓴이 : <input type="text" name="comment_id" value="${commented.comment_id }" readonly/><br>
		댓글 내용 : <br>
		<textarea name="comment_view" rows="10" cols="80" >${commented.comment_view }</textarea><br>
		<input type="hidden" name="comment_no" />
		<input type="submit" value="Modify" onclick="return confirm('정말로 수정하시겠습니까?.');"/>
	</form>
</body>
</html>