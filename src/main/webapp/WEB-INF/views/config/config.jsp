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
		.sortable_table tr {
			cursor: pointer; }
		.sortable_table tr.placeholder {
			display: block;
			background: red;
			position: relative;
			margin: 0;
			padding: 0;
			border: none; }
		.sortable_table tr.placeholder:before {
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
								<th width="10%">#</th>
								<th>카테고리명</th>
								<th width="10%">공개</th>
								</thead>
								<tbody>
								<c:forEach var="category" items="${categories}" varStatus="status">
								<tr>
									<td><i class="fa fa-bars" aria-hidden="true"></i></td>
									<td>
										<span><c:out value="${category.category_name}"/></span>
										<span style="display: none;">
											<input type="text" class="form-control input-sm"
											       name="category_name" value="${category.category_name}"
											       onkeypress="if (event.keyCode == 13) {UpdateCategoryName(this)}"/>
										</span>
									</td>
									<td>
										<input type="checkbox" name="category_cd" data-toggle="toggle" data-size="small"
										       value="${category.category_cd}" ${category.use_yn ? 'checked':''}/>
									</td>
								</tr>
								</c:forEach>
								</tbody>
							</table>
							<div class="box-services">
								<p class="text-left">
									<button type="button" class="btn btn-sm btn-primary"
									        data-toggle="modal" data-target="#newCategory">
										<i class="fa fa-plus-circle" aria-hidden="true"> 추가</i>
									</button>
								</p>
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
	<div class="modal fade" id="newCategory" tabindex="-1">
		<div class="modal-dialog">
			<form action="/config/category" method="post">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">카테고리 추가</h4>
				</div>
				<div class="modal-body">
					<input type="text" name="category_name" class="form-control input-lg" placeholder="카테고리 명을 입력하세요.">
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-sm btn-default" data-dismiss="modal">
						<i class="fa fa-times-circle" aria-hidden="true"></i> 닫기
					</button>
					<button type="button" class="btn btn-sm btn-primary">
						<i class="fa fa-check-circle" aria-hidden="true"></i> 저장
					</button>
				</div>
			</div>
			</form>
		</div>
	</div>
	<content tag="script">
		<script src="${pageContext.request.contextPath}/resources/scripts/jquery-sortable-min.js"></script>
		<script>
			function UpdateCategory (attr, params, callback) {
				$.post('/config/setCategory' + attr, params)
					.then(function (data) {
						return callback(null, data)
					}, function (error) {
						return callback(error, null)
					})
			}

			function UpdateCategoryName (el) {
				let params = {
					category_cd: $(el).parent().parent().next().find('input[name=category_cd]').val(),
					category_name: $(el).val()
				}
				UpdateCategory ('Name', params, function (err, result) {
					if (err || !result.success)
						alert('오류가 발생하여 저장이 되지 않았습니다.')
					else
						$(el).parent().parent().find('span:nth-child(1)').text(params['category_name'])
					$(el).parent().slideUp()
					$(el).parent().parent().find('span:nth-child(1)').slideDown()
				})
			}

			$('.sortable_table').sortable({
				group: 'no-drop',
				handle: 'i.fa.fa-bars',
				containerSelector: 'table',
				itemPath: '> tbody',
				itemSelector: 'tr',
				placeholder: '<tr class="placeholder"/>',
				onDragStart: function ($item, container, _super) {
					if (!container.options.drop)
						$item.clone().insertAfter($item)
					_super($item, container)
				},
				onDrop: function ($item, container, _super, event) {
					$item.removeClass(container.group.options.draggedClass).removeAttr('style')
					$('body').removeClass(container.group.options.bodyClass)
					var array = $('.sortable_table input[name=category_cd]').get()
					array.forEach(function (item, index) {
						let params = {category_cd: Number($(item).val()), order_no: index+1}
						UpdateCategory('Order', params, function (err) {
							if (err) console.error(err)
						})
					})
				}
			})

			$('input[name=category_cd]').change(function () {
				let params = {category_cd: Number($(this).val()), use_yn: $(this).prop('checked')}
				UpdateCategory('Usage', params, function (err, result) {
					if (!result.success) {
						alert('오류가 발생하였습니다.\n' + data['message'])
					}
				})
			})

			$('.sortable_table tr > td:nth-child(2)').on('dblclick', function (e) {
				$(this).children('span:first').slideUp()
				$(this).children('span:last').slideDown()
			})
		</script>
	</content>
</body>
</html>
