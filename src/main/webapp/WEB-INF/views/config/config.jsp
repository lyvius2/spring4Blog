<%--
  Created by IntelliJ IDEA.
  User: yhwang131
  Date: 2017-05-23
  Time: 오후 4:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>설정</title>
</head>
<body>
	<section>
		<div class="container clearfix">
			<div class="row services">
				<div class="col-md-12">
					<h3 class="h3 heading">Blog Category</h3>
					<div class="row">
						<div class="col-md-6 col-sm-8">
							<table class="table">
								<thead>
								<th>#</th>
								<th>카테고리명</th>
								<th>공개설정</th>
								</thead>
								<tbody>
								<c:forEach var="category" items="${categories}" varStatus="status">
									<tr>
										<td>${status.index}</td>
										<td><c:out value="${category.category_name}"/></td>
										<td><input type="checkbox" value="${category.use_yn}"/></td>
									</tr>
								</c:forEach>
								</tbody>
							</table>
							<div class="box box-services">
							</div>
						</div>
					</div>
					<%--
					<h3 class="h3 heading">Code</h3>
					<div class="row">
					</div>
					--%>
				</div>
			</div>
		</div>
	</section>
</body>
</html>
