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
	<!-- Bootstrap CSS-->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css">
	<!-- Font Awesome and Pixeden Icon Stroke icon fonts-->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/pe-icon-7-stroke.css">
	<!-- Google fonts - Roboto-->
	<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:300,400,700">
	<!-- lightbox-->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/lightbox.min.css">
	<!-- theme stylesheet-->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.default.css" id="theme-stylesheet">
	<!-- Flags -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/flag.css">
	<!-- Favicon-->
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/images/favicon.png">
	<style>
		.btn-primary-invert {
			color: #ffffff;
			background-color: #2b90d9;
			border-color: #1e6fa9;
		}
		.btn-primary-invert:hover,
		.btn-primary-invert:focus,
		.btn-primary-invert:active,
		.btn-primary-invert.active,
		.open .dropdown-toggle.btn-primary-invert {
			color: #2b90d9;
			background-color: #ebebeb;
			border-color: #2b90d9;
		}
	</style>
	<!-- Tweaks for older IEs--><!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script><![endif]-->
	<decorator:head />
</head>
<body>
	<!-- navbar-->
	<jsp:include page="header.jsp"/>
	<decorator:body />
	<jsp:include page="footer.jsp"/>
	<script src="${pageContext.request.contextPath}/resources/scripts/jquery-3.2.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/scripts/bootstrap.min.js"></script>
	<script src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/scripts/jquery.cookie.js"></script>
	<script src="${pageContext.request.contextPath}/resources/scripts/lightbox.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/scripts/front.js"></script>
	<script>
		$('form#sign-in-form').on('submit', function (e) {
			e.preventDefault()
			$.post('/signIn', $('#sign-in-form').serialize())
				.then(function (data) {
					if (data['authenticated']) window.location.reload()
					else $('#sign-in-div p.text-danger').text(data['errorMsg'])
				})
		})
	</script>
<decorator:getProperty property="page.script" />
</body>
</html>
