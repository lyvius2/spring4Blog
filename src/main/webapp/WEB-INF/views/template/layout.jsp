<%--
  Created by IntelliJ IDEA.
  User: yhwang131
  Date: 2016-08-08
  Time: 오후 5:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Default</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/semantic/dist/semantic.min.css" />
	<style type="text/css">
		.ui.menu .item img.logo {
			margin-right: 1.5em;
		}
		.main.container {
			margin-top: 7em;
		}
		.wireframe {
			margin-top: 2em;
		}
		.ui.footer.segment {
			margin: 5em 0em 0em;
			padding: 5em 0em;
		}
	</style>
</head>
<body>
	<tiles:insertAttribute name="header" />
	<div class="ui main text container">
		<tiles:insertAttribute name="body" />
	</div>
	<tiles:insertAttribute name="footer" />
	<script src="${pageContext.request.contextPath}/resources/scripts/vendor.js"></script>
	<script src="${pageContext.request.contextPath}/resources/semantic/dist/semantic.min.js"></script>
</body>
</html>
