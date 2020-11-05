<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>입력페이지</title>
</head>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	// 인원에 따른 생일입력 추가
	$(function() {
		$("#headnurse").change(function() {
		});
		$("#staffnurse").change(function() {
		});
		$("#assistnurse").change(function() {
		});
		
	});
</script>
<script type="text/javascript">
	function go_submit(f) {
		f.action="schedule.do";
		f.submit();
	}
</script>
<body>
	<form method="post">
		<span>수간호사의 수 : </span><input type="number" name="n1" id="headnurse"> <span>일반간호사의 수 : </span><input
			type="number" name="n2" id="staffnurse"> <span>간호조무사의 수 : </span><input
			type="number" name="n3" id="assistnurse">
		<br>
		<span>시작일 : </span><input type="date" name="start"> <span> ~ 종료일 : </span><input type="date" name="end"> 
		<br><hr>
		<input type="button" value="교대근무시간표 생성" onclick="go_submit(this.form)">
	</form>
</body>
</html>