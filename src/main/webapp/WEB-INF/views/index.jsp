<%--
  Created by IntelliJ IDEA.
  User: yhwang131
  Date: 2016-08-08
  Time: 오후 5:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>새로운 시작</title>
</head>
<body>
	<h1 class="ui header">Hello Spring 4 & Semantic UI World!</h1>
	<p>This is a basic fixed menu template using fixed size containers.</p>
	<p>A text container is used for the main container, which is useful for single column layouts</p>
	<p>Railways Hi! Current Time is ${serverTime}.</p>
	<img class="wireframe" src="${pageContext.request.contextPath}/resources/images/faroeIslands.jpg">
	<content tag="script">
		<script>
			console.log('Hello! We are the world');
		</script>
	</content>
</body>
</html>
