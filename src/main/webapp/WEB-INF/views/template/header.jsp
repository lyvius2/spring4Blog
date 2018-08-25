<%@ page import="org.springframework.web.util.UrlPathHelper" %><%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %><%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %><header class="header">
		<div role="navigation" class="navbar navbar-default">
			<div class="container">
				<div class="navbar-header">
					<a href="/" class="navbar-brand">Walter's Home</a>
					<div class="navbar-buttons">
						<button type="button" data-toggle="collapse" data-target=".navbar-collapse" class="navbar-toggle navbar-btn">Menu<i class="fa fa-align-justify"></i></button>
					</div>
				</div>
				<c:set var="path" value="${requestScope['javax.servlet.forward.servlet_path']}"/>
				<div id="navigation" class="collapse navbar-collapse navbar-right" style="padding-right: 0px;">
					<security:authorize access="!isAuthenticated()">
						<a href="#" id="sign-in-headerBtn" data-toggle="modal" data-target="#login-modal" class="btn navbar-btn btn-ghost">
							<i class="fa fa-sign-in"></i>Sign in
						</a>
					</security:authorize>
					<security:authorize access="isAuthenticated()">
						<a href="/signOut" id="sign-out-headerBtn" class="btn navbar-btn btn-ghost">
							<i class="fa fa-sign-out"></i>Sign out
						</a>
					</security:authorize>
				</div>
				<div id="navigation" class="collapse navbar-collapse navbar-right" style="padding-right: 0px;">
					<ul class="nav navbar-nav">
						<li class="<c:if test="${fn:indexOf(path, '/post') == 0}">active</c:if> dropdown"><a href="/post" class="dropdown-toggle">Blog <b class="caret"></b></a>
							<ul class="dropdown-menu">
								<c:forEach var="category" items="${categories}" varStatus="vs">
									<c:if test="${category.use_yn == true}">
									<li><a href="/post/category/${category.category_cd}" data-target="${category.category_cd}"><c:out value="${category.category_name}"/></a></li>
									</c:if>
								</c:forEach>
							</ul>
						</li>
						<li<c:if test="${path == '/resume'}"> class="active"</c:if>><a href="/resume">Profile</a></li>
						<li<c:if test="${path == '/config'}"> class="active"</c:if>><a href="/config">Config</a></li>
					</ul>
				</div>
			</div>
		</div>
	</header><%--
	<security:authorize access="isAuthenticated()">
		<div class="item right floated"><security:authentication property="principal.kr_name"/>) 님 안녕하세요.</div>
	</security:authorize>--%>
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
						<form action="/connect/facebook" method="post" id="facebook-form" style="margin-bottom: 5px">
							<input type="hidden" name="scope" value="public_profile, email"/>
							<button type="submit" class="btn btn-primary-invert btn-block">
								<i class="fa fa-facebook-square"></i> Sign In with Facebook
							</button>
						</form>
					</div>
					<div id="sign-in-google">
						<form action="/connect/google" method="post" id="google-form" style="margin-bottom: 5px">
							<input type="hidden" name="scope" value="email"/>
							<button type="submit" class="btn btn-danger btn-block">
								<i class="fa fa-google-plus-square"></i> Sign In with Google+
							</button>
						</form>
					</div>
					<div id="sign-in-github">
						<form action="/connect/github" method="post" id="github-form" style="margin-bottom: 5px">
							<input type="hidden" name="scope" value="user:email"/>
							<button type="submit" class="btn btn-grey btn-block">
								<i class="fa fa-github"></i> Sign In with GitHub
							</button>
						</form>
					</div>
					<div id="sign-in-linkedin">
						<form action="/connect/linkedin" method="post" id="linkedin-form">
							<input type="hidden" name="scope" value="r_emailaddress"/>
							<button type="submit" class="btn btn-info btn-block">
								<i class="fa fa-linkedin-square"></i> Sign In with LinkedIn
							</button>
						</form>
					</div>
					<div id="sign-in-naver">
						<form action="/connect/naver" method="post" id="naver-form">
							<input type="hidden" name="scope" value="email, nickname, id, name"/>
							<button type="submit" class="btn btn-success btn-block">
								Sign In with Naver
							</button>
						</form>
					</div>
					<div id="sign-up-div">
						<p class="text-center text-muted">사이트 관리자 이외 소셜 로그인만 지원합니다.<br/>개인정보는 DB에 저장하지 않습니다!</p>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- *** LOGIN MODAL END ***-->