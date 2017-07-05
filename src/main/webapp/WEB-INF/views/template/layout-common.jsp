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
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title><decorator:title default="Layout Page" /></title>
	<meta name="description" content="">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="robots" content="all,follow">
	<jsp:include page="linkcss.jsp"/>
	<decorator:head />
</head>
<body>
	<!-- navbar-->
	<jsp:include page="header.jsp"/>
	<decorator:body />
	<jsp:include page="footer.jsp"/>
	<jsp:include page="scriptsrc.jsp"/>
<decorator:getProperty property="page.script" />
</body>
</html>
