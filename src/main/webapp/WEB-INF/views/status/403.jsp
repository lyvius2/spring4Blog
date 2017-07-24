<%--
  Created by IntelliJ IDEA.
  User: yhwang131
  Date: 2016-09-02
  Time: 오후 6:08
  To change this template use File | Settings | File Templates.
--%><%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %><!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>403 Error</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.default.css" id="theme-stylesheet">
</head>
<body>
	<div class="container">
		<section>
			<p class="lead text-center">
				권한이 없어 <span v-html="timerRanges"></span>초 후에 기본 화면으로 이동합니다...
			</p>
		</section>
	</div>
	<script src="${pageContext.request.contextPath}/resources/scripts/vue.min.js"></script>
	<script>
		var cnt = new Vue({
			el: '.container',
			data: {
				timerRanges: 5
			}
		})
		setInterval(function () {
			cnt.timerRanges--
			if (cnt.timerRanges == 0) location.replace('/');
		}, 1000)
	</script>
</body>
</html>
