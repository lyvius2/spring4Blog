<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %><%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><div class="ui fixed inverted menu">
	<div class="ui container">
		<a href="#" class="header item">
			<img class="logo" src="${pageContext.request.contextPath}/resources/images/logo.png">
			Project Name
		</a>
		<a href="#" class="item">Home</a>
		<div class="ui simple dropdown item">
			Dropdown <i class="dropdown icon"></i>
			<div class="menu">
				<a class="item" href="#">Link Item</a>
				<a class="item" href="#">Link Item</a>
				<div class="divider"></div>
				<div class="header">Header Item</div>
				<div class="item">
					<i class="dropdown icon"></i>
					Sub Menu
					<div class="menu">
						<a class="item" href="#">Link Item</a>
						<a class="item" href="#">Link Item</a>
					</div>
				</div>
				<a class="item" href="#">Link Item</a>
			</div>
		</div>
		<security:authorize access="isAuthenticated()">
			<div class="item right floated"><c:out value="${sessionScope.userName}" /> 님 안녕하세요</div>
		</security:authorize>
	</div>
</div>