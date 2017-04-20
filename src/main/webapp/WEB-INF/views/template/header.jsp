<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %><%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><header class="header">
	<div role="navigation" class="navbar navbar-default">
		<div class="container">
			<div class="navbar-header"><a href="index.html" class="navbar-brand">Walter's Home</a>
				<div class="navbar-buttons">
					<button type="button" data-toggle="collapse" data-target=".navbar-collapse" class="navbar-toggle navbar-btn">Menu<i class="fa fa-align-justify"></i></button>
				</div>
			</div>
			<div id="navigation" class="collapse navbar-collapse navbar-right">
				<ul class="nav navbar-nav">
					<li class="active dropdown"><a href="#" data-toggle="dropdown" class="dropdown-toggle">Blog <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="#">Dropdown item 1</a></li>
							<li><a href="#">Dropdown item 2</a></li>
							<li><a href="#">Dropdown item 3</a></li>
							<li><a href="#">Dropdown item 4</a></li>
						</ul>
					</li>
					<li><a href="index.html">Home</a></li>
					<li><a href="text.html">Profile</a></li>
					<li><a href="contact.html">Contact</a></li>
				</ul><a href="#" data-toggle="modal" data-target="#login-modal" class="btn navbar-btn btn-ghost"><i class="fa fa-sign-in"></i>Log in</a>
			</div>
		</div>
	</div>
</header>

<%--
<security:authorize access="isAuthenticated()">
	<div class="item right floated"><c:out value="${pageContext.request.remoteUser}"/>(<c:out value="${sessionScope.userInfo.kr_name}"/>) 님 안녕하세요.</div>
</security:authorize>
--%>

<!-- *** LOGIN MODAL ***_________________________________________________________
-->
<div id="login-modal" tabindex="-1" role="dialog" aria-labelledby="Login" aria-hidden="true" class="modal fade">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" data-dismiss="modal" aria-hidden="true" class="close">×</button>
				<h4 id="Login" class="modal-title">Customer login</h4>
			</div>
			<div class="modal-body">
				<form action="customer-orders.html" method="post">
					<div class="form-group">
						<input id="email_modal" type="text" placeholder="email" class="form-control">
					</div>
					<div class="form-group">
						<input id="password_modal" type="password" placeholder="password" class="form-control">
					</div>
					<p class="text-center">
						<button type="button" class="btn btn-primary"><i class="fa fa-sign-in"></i> Log in</button>
					</p>
				</form>
				<p class="text-center text-muted">Not registered yet?</p>
				<p class="text-center text-muted"><a href="#"><strong>Register now</strong></a>! It is easy and done in 1 minute and gives you access to special discounts and much more!</p>
			</div>
		</div>
	</div>
</div>
<!-- *** LOGIN MODAL END ***-->