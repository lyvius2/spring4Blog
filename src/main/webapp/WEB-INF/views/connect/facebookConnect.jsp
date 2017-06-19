<%--
  Created by IntelliJ IDEA.
  User: yhwang131
  Date: 2017-05-15
  Time: 오후 1:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Hello Facebook</title>
</head>
<body>
	<h3>Connect to Facebook</h3>
	<form action="/connect/facebook" method="POST">
		<input type="hidden" name="scope" value="user_posts, public_profile">
		<div class="formInfo">
			<p>You aren't connected to Facebook yet. Click the button to connect this application with your Facebook account.</p>
		</div>
		<p><button type="submit">Connect to Facebook</button></p>
	</form>
</body>
</html>
