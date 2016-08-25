<%--
  Created by IntelliJ IDEA.
  User: yhwang131
  Date: 2016-08-22
  Time: 오전 11:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
	<title>회원 정보</title>
</head>
<body>
	<div class="ui">
		<div class="column">
			<div class="ui raised segment">
				<a class="ui red ribbon label"><c:out value="${member.username}"/></a>
				<span><c:out value="${member.kr_name}"/></span>
				<div class="ui list">
					<div class="item">
						<i class="translate icon"></i>
						<div class="content">
							<c:out value="${member.first_name}"/> <c:out value="${member.last_name}"/>
						</div>
					</div>
					<div class="item">
						<i class="call icon"></i>
						<div class="content">
							<c:out value="${member.phone}"/>
						</div>
					</div>
					<div class="item">
						<i class="mail icon"></i>
						<div class="content">
							<a href="mailto:<c:out value="${member.email}"/>"><c:out value="${member.email}"/></a>
						</div>
					</div>
					<div class="item">
						<i class="marker icon"></i>
						<div class="content">
							<c:out value="${member.nationality_name}"/>
							<i class="${fn:toLowerCase(member.nationality)} flag"></i>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
