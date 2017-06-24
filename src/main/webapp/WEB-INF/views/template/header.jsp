<%@ page import="org.springframework.web.util.UrlPathHelper" %><%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %><%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><header class="header">
	<div role="navigation" class="navbar navbar-default">
		<div class="container">
			<div class="navbar-header">
				<a href="index.html" class="navbar-brand">Walter's Home
					<security:authorize access="isAuthenticated()">
						<security:authentication property="principal.kr_name"/>
					</security:authorize>
				</a>
				<div class="navbar-buttons">
					<button type="button" data-toggle="collapse" data-target=".navbar-collapse" class="navbar-toggle navbar-btn">Menu<i class="fa fa-align-justify"></i></button>
				</div>
			</div>
			<c:set var="path" value="${pageContext.request.servletPath}"/>
			<div id="navigation" class="collapse navbar-collapse navbar-right">
				<ul class="nav navbar-nav">
					<li <c:if test="${path == '/'}">class="active"</c:if>><a href="/">Home</a></li>
					<li class="dropdown"><a href="#" data-toggle="dropdown" class="dropdown-toggle">Blog <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<c:forEach var="category" items="${categories}" varStatus="status">
							<li><a href="#" data-target="${category.category_cd}"><c:out value="${category.category_name}"/></a></li>
							</c:forEach>
						</ul>
					</li>
					<li <c:if test="${path == '/resume'}">class="active"</c:if>><a href="/resume">Profile</a></li>
					<li <c:if test="${path == '/config'}">class="active"</c:if>><a href="/config">Config</a></li>
				</ul>
				<security:authorize access="!isAuthenticated()">
				<a href="#" data-toggle="modal" data-target="#login-modal" class="btn navbar-btn btn-ghost">
					<i class="fa fa-sign-in"></i>Sign in
				</a>
				</security:authorize>
				<security:authorize access="isAuthenticated()">
				<a href="/signOut" class="btn navbar-btn btn-ghost">
					<i class="fa fa-sign-out"></i>Sign out
				</a>
				</security:authorize>
			</div>
		</div>
	</div>
</header>

<%--
<security:authorize access="isAuthenticated()">
	<div class="item right floated"><c:out value="${pageContext.request.remoteUser}"/>(<c:out value="${sessionScope.userInfo.kr_name}"/>) 님 안녕하세요.</div>
</security:authorize>
--%>

<!-- *** LOGIN MODAL *** -->
<div id="login-modal" tabindex="-1" role="dialog" aria-labelledby="Login" aria-hidden="true" class="modal fade">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" data-dismiss="modal" aria-hidden="true" class="close">×</button>
				<h4 id="Login" class="modal-title">Sign In</h4>
			</div>
			<div class="modal-body">
				<div id="sign-in-div">
					<form action="/signIn" method="post" id="sign-in-form">
						<div class="form-group">
							<input id="username" name="username" value="${username}" type="text" placeholder="ID" class="form-control"/>
						</div>
						<div class="form-group">
							<input id="password" name="password" value="${password}" type="password" placeholder="Password" class="form-control"/>
							<input id="returnUrl" name="returnUrl" value="<%= new UrlPathHelper().getOriginatingRequestUri(request) %>" type="hidden"/>
						</div>
						<p class="text-center">
							<button id="sign-in" type="submit" class="btn btn-primary"><i class="fa fa-sign-in"></i> Sign In</button>
						</p>
					</form>
					<p class="text-center text-muted text-danger"></p>
				</div>
				<div id="sign-in-facebook">
					<form action="/connect/facebook" method="post" id="facebook-form">
						<input type="hidden" name="scope" value="public_profile"/>
						<button type="submit" class="btn btn-primary-invert btn-block">
							<i class="fa fa-facebook-square"></i> Sign In with Facebook
						</button>
					</form>
				</div>
				<div id="sign-up-div">
					<p class="text-center text-muted">Not registered yet?</p>
					<p class="text-center text-muted"><a href="#"><strong>Register now</strong></a>! It is easy and done in 1 minute and gives you access to special discounts and much more!</p>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- *** LOGIN MODAL END ***-->