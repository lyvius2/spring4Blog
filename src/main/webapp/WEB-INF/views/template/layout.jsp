<%--
  Created by IntelliJ IDEA.
  User: yhwang131
  Date: 2016-08-08
  Time: 오후 5:31
  To change this template use File | Settings | File Templates.
--%><%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %><%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %><%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %><!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title><decorator:title default="Layout Page" /></title>
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
		.georgia {
			font-family: Georgia, sans-serif;
			font-size: 1em;
		}
	</style>
	<decorator:head />
</head>
<body ng-app="<decorator:getProperty property="body.ng-app" />">
	<jsp:include page="header.jsp"/>
	<div class="ui main container">
		<decorator:body />
	</div>
	<jsp:include page="footer.jsp"/>
	<script src="${pageContext.request.contextPath}/resources/scripts/vendor.js"></script>
	<script src="${pageContext.request.contextPath}/resources/semantic/dist/semantic.min.js"></script>
<decorator:getProperty property="page.script" />
</body>
</html>
