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
	<title>설정</title>
	<style>
		body.dragging, body.dragging * {
			cursor: move !important; }
		.dragged {
			position: absolute;
			top: 0;
			opacity: 0.5;
			z-index: 2000; }
		.sorted_table tr {
			cursor: pointer; }
		.sorted_table tr.placeholder {
			display: block;
			background: red;
			position: relative;
			margin: 0;
			padding: 0;
			border: none; }
		.sorted_table tr.placeholder:before {
			content: "";
			position: absolute;
			width: 0;
			height: 0;
			border: 5px solid transparent;
			border-left-color: red;
			margin-top: -5px;
			left: -5px;
			border-right: none; }
	</style>
</head>
<body>
	<section>
		<div class="container clearfix">
			<div class="row services">
				<div class="col-md-12">
					<h3 class="h3 heading">Blog Category</h3>
					<div class="row">
						<div class="col-md-6 col-sm-8">
							<table class="table sortable_table">
								<thead>
								<th>#</th>
								<th>카테고리명</th>
								<th width="10%">공개</th>
								</thead>
								<tbody>
								<c:forEach var="category" items="${categories}" varStatus="status">
									<tr>
										<td>${status.index}</td>
										<td>
											<c:out value="${category.category_name}"/>
											<input type="hidden" name="category_name" value="${category.category_name}"/>
										</td>
										<td>
											<input type="checkbox" name="category_cd" data-toggle="toggle" data-size="small"
											       value="${category.category_cd}" ${category.use_yn ? 'checked':''}/>
										</td>
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
	<content tag="script">
		<script src="${pageContext.request.contextPath}/resources/scripts/jquery-sortable-min.js"></script>
		<script>
			$('.sortable_table').sortable({
				containerSelector: 'table',
				itemPath: '> tbody',
				itemSelector: 'tr',
				placeholder: '<tr class="placeholder"/>'
			})

			$('input[name=category_cd]').change(function () {
				console.log($(this).val())
				$.post(
					'/config/setCategoryUsage',
					{category_cd: Number($(this).val()), use_yn: $(this).prop('checked')}
				).then(function (data) {
					console.log('data', data)
					if (!data.success) {
						alert('오류가 발생하였습니다.\n' + data['message'])
					}
				})
			})
		</script>
	</content>
</body>
</html>
