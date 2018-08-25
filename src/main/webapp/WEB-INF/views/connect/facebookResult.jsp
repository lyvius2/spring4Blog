<%--
  Created by IntelliJ IDEA.
  User: Walter
  Date: 2017-05-16
  Time: 오후 10:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>Facebook Result</title>
</head>
<body>
	<h3>Hello, <span>${facebookProfile.name}</span>!</h3>
	<h4>Here is your feed: ${facebookProfile.link}</h4>
	<c:forEach var="post" items="${feed}">
	<div>
		<b>${post.from.name} from</b> wrote:
		<p>${post.message}</p>
		<c:if test="${post.picture != null}">
			<img src="${post.picture}"/>
		</c:if>
		<hr/>
	</div>
	</c:forEach>
</body>
</html>
