<%--
  Created by IntelliJ IDEA.
  User: yhwang131
  Date: 2016-08-26
  Time: 오후 4:42
  To change this template use File | Settings | File Templates.
--%><%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %><%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>로그인</title>
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
<div class="ui main text container">
	<div class="ui middle aligned center aligned grid">
		<div class="column" style="max-width: 450px;">
			<h2 class="ui icon header">
				<i class="plane icon"></i>
				<div class="content">
					Sign In.
					<div class="sub header">Log-in to your account. Enjoy yhwang131's blog contents.</div>
				</div>
			</h2>
			<form action="/signIn" method="post" class="ui large form">
				<div class="ui stacked segment">
					<div class="field">
						<div class="ui left icon input">
							<i class="user icon"></i>
							<input type="text" name="username" placeholder="ID" required>
						</div>
					</div>
					<div class="field">
						<div class="ui left icon input">
							<i class="lock icon"></i>
							<input type="password" name="password" placeholder="Password" required>
						</div>
					</div>
					<button class="fluid ui blue icon button">
						<i class="sign in icon"></i>
						Login
					</button>
				</div>
			</form>
			<c:if test="${not empty param.fail}">
				<div class="ui negative message">
					<h4 class="ui header">Your login information was invalid, try again.</h4>
					<p>${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</p>
				</div>
				<c:remove scope="session" var="SPRING_SECURITY_LAST_EXCEPTION"/>
			</c:if>
			<div class="ui message">
				New to us? <a href="/member/register">Sign Up</a>
			</div>
		</div>
	</div>
</div>
</body>
</html>
