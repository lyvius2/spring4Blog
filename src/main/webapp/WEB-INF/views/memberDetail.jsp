<%--
  Created by IntelliJ IDEA.
  User: yhwang131
  Date: 2016-08-10
  Time: 오후 4:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>회원 정보</title>
</head>
<body>
	<div class="ui grid">
		<div class="column">
			<div class="ui raised segment">
				<a href="#" class="ui red ribbon label"><c:out value="${member.AME_MemID}"/></a>
				<span><c:out value="${member.AME_Name}"/></span>
				<div class="ui list">
					<div class="item">
						<i class="mail icon"></i>
						<div class="content">
							<a href="mailto:${member.AME_Email}"><c:out value="${member.AME_Email}"/></a>
						</div>
					</div>
					<div class="item">
						<i class="mobile icon"></i>
						<div class="content">
							<c:out value="${member.AME_Phone}"/>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
