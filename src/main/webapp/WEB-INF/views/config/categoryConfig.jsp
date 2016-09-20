<%--
  Created by IntelliJ IDEA.
  User: yhwang131
  Date: 2016-09-13
  Time: 오후 2:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>메뉴 설정</title>
</head>
<body>
	<div class="ui internally celled grid">
		<div class="row">
			<div class="four wide column">
				<img>
			</div>
			<div class="six wide column">
				<table class="ui compact celled definition table">
					<thead class="full-width">
						<tr>
							<th>No.</th>
							<th>카테고리 명</th>
							<th>보기 권한</th>
							<th>활성화</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><input type="checkbox" /></td>
							<td>프로필</td>
							<td>ROLE_ANONYMOUS</td>
							<td class="collapsing">
								<div class="ui fitted slider checkbox">
									<input type="checkbox">
									<label></label>
								</div>
							</td>
						</tr>
					</tbody>
					<tfoot class="full-width">
						<tr>
							<th colspan="4">
								<div class="ui right floated small primary labeled icon button">
									<i class="setting icon"></i>
									설정
								</div>
							</th>
						</tr>
					</tfoot>
				</table>
			</div>
			<div class="six wide column">
				<p>아따따뚜겐</p>
			</div>
		</div>
	</div>
</body>
</html>
