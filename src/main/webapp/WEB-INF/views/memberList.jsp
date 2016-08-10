<%--
  Created by IntelliJ IDEA.
  User: yhwang131
  Date: 2016-08-10
  Time: 오후 2:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>회원 리스트</title>
</head>
<body>
	<div class="ui relaxed divided list">
		<c:forEach var="member" items="${memberList}" varStatus="list">
			<div class="item">
				<i class="large github middle aligned icon"></i>
				<div class="content">
					<a href="<c:url value="/member/${member.AME_SEQ}"/>" class="header"><c:out value="${member.AME_MemID}"/>/<c:out value="${member.AME_Name}"/></a>
					<div class="description"><c:out value="${member.AME_Email}"/></div>
				</div>
			</div>
		</c:forEach>
	</div>
</body>
</html>
