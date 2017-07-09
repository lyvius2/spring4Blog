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
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
		<div class="container clearfix" role="tabpanel">
			<ul class="nav nav-tabs" role="tablist">
				<li role="presentation" class="active">
					<a href="#settings" aria-controls="settings" role="tab" data-toggle="tab">Settings</a>
				</li>
				<li role="presentation">
					<a href="#resume" aria-controls="resume" role="tab" data-toggle="tab">Resume History</a>
				</li>
				<li role="presentation">
					<a href="#logs" aria-controls="logs" role="tab" data-toggle="tab">Logs</a>
				</li>
				<li role="presentation">
					<a href="#exceptions" aria-controls="exceptions" role="tab" data-toggle="tab">Exceptions</a>
				</li>
			</ul>

			<div class="tab-content">
				<div role="tabpanel" class="tab-pane active" id="settings">
					<section>
						<div class="container clearfix">
							<div class="row services">
								<div class="col-md-5">
									<h3 class="h3 heading">Admin</h3>
									<div class="row">
										<div class="col-md-12">
											<table class="table">
												<thead>
												<th width="10%">#</th>
												<th>ID</th>
												<th width="10%">사용</th>
												</thead>
												<tbody>
												<c:forEach var="member" items="${memberList}" varStatus="vs">
													<tr>
														<td class="georgia-num">${vs.index + 1}</td>
														<td>
															<a href="javascript:openModifyLayer(${member.seq}, '${member.name}');" class="add-click">
																<c:out value="${member.username}"/>
															</a>
														</td>
														<td>
															<input type="checkbox" name="seq" data-toggle="toggle" data-size="small"
															       value="${member.seq}" ${member.use_yn ? 'checked':''}/>
														</td>
													</tr>
												</c:forEach>
												</tbody>
											</table>
											<div class="box-services">
												<p class="text-left">
													<button type="button" class="btn btn-sm btn-primary"
													        data-toggle="modal" data-target="#newAdmin">
														<i class="fa fa-plus-circle" aria-hidden="true"> 추가</i>
													</button>
												</p>
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-7">
									<h3 class="h3 heading">Blog Category</h3>
									<div class="row">
										<div class="col-md-11">
											<table class="table sortable_table">
												<thead>
												<th width="10%">#</th>
												<th>카테고리명</th>
												<th width="10%">공개</th>
												</thead>
												<tbody>
												<c:forEach var="category" items="${categories}" varStatus="status">
													<tr>
														<td>
															<i class="fa fa-bars" aria-hidden="true"></i>
															<i class="fa fa-minus-square text-danger" aria-hidden="true" style="display:none;"></i>
														</td>
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
													<button type="button" class="btn btn-sm btn-danger" id="btn-remove">
														<i class="fa fa-times" aria-hidden="true"> 삭제</i>
													</button>
												</p>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</section>
				</div>
				<div role="tabpanel" class="tab-pane" id="resume">resume</div>
				<div role="tabpanel" class="tab-pane" id="logs">logs</div>
				<div role="tabpanel" class="tab-pane" id="exceptions">exceptions</div>
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
						<input type="text" name="category_name" class="form-control input-lg" placeholder="카테고리 명을 입력하세요." required/>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-sm btn-default" data-dismiss="modal">
							<i class="fa fa-times-circle" aria-hidden="true"></i> 닫기
						</button>
						<button type="submit" class="btn btn-sm btn-primary">
							<i class="fa fa-check-circle" aria-hidden="true"></i> 저장
						</button>
					</div>
				</div>
			</form>
		</div>
	</div>

	<div class="modal fade" id="newAdmin" tabindex="-1">
		<div class="modal-dialog">
			<form:form cssClass="form-horizontal" action="/config/admin" method="post" commandName="memberVO" autocomplete="false">
				<input type="hidden" id="mode" name="mode" value="C"/>
				<input type="hidden" id="seq" name="seq" value="0"/>
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">관리자 계정 <span>추가</span></h4>
					</div>
					<div class="modal-body">
						<div class="form-group" id="form-username">
							<label for="username" class="col-sm-3 control-label">ID (E-Mail)</label>
							<div class="col-sm-9">
								<form:input type="email" cssClass="form-control" path="username" maxlength="50" placeholder="E-Mail"/>
								<form:errors path="username" element="div" cssClass="alert alert-danger"></form:errors>
							</div>
						</div>
						<div class="form-group">
							<label for="name" class="col-sm-3 control-label">이름</label>
							<div class="col-sm-9">
								<form:input type="text" cssClass="form-control" path="name" placeholder="성명"/>
								<form:errors path="name" element="div" cssClass="alert alert-danger"></form:errors>
							</div>
						</div>
						<div class="form-group">
							<label for="password" class="col-sm-3 control-label">비밀번호</label>
							<div class="col-sm-9">
								<form:password cssClass="form-control" path="password" placeholder="비밀번호 입력"/>
								<form:errors path="password" element="div" cssClass="alert alert-danger"></form:errors>
							</div>
						</div>
						<div class="form-group">
							<label for="passwordConfirm" class="col-sm-3 control-label">비밀번호 확인</label>
							<div class="col-sm-9">
								<input type="password" class="form-control" id="passwordConfirm" name="passwordConfirm" placeholder="비밀번호 확인"/>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-sm btn-default" data-dismiss="modal">
							<i class="fa fa-times-circle" aria-hidden="true"></i> 닫기
						</button>
						<button type="submit" class="btn btn-sm btn-primary">
							<i class="fa fa-check-circle" aria-hidden="true"></i> 저장
						</button>
					</div>
				</div>
			</form:form>
		</div>
	</div>
	<content tag="script">
	<script src="${pageContext.request.contextPath}/resources/scripts/jquery-sortable-min.js"></script>
	<script>
		function openModifyLayer (seq, name) {
			document.getElementById('mode').value = 'U'
			document.getElementById('seq').value = seq
			document.getElementById('name').value = name
			$('#form-username').hide()
			$('#newAdmin .modal-title span').text('수정')
			$('#newAdmin').modal('show')
		}

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

		$('#newAdmin').on('hide.bs.modal', function() {
			document.getElementById('mode').value = 'C'
			document.getElementById('username').value = null
			document.getElementById('name').value = null
			document.getElementById('password').value = null
			document.getElementById('passwordConfirm').value = null
			$(this).find('.alert-danger').remove()
			$('#form-username').show()
			$('#newAdmin .modal-title span').text('추가')
		})

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

		$('table input[name=seq]').change(function () {
			let params = {seq: Number($(this).val()), use_yn: $(this).prop('checked')}
			$.post('/config/setAdminUsage', params).then(function () {})
		})

		$('.sortable_table tr > td:nth-child(2)').on('dblclick', function (e) {
			$(this).children('span:first').slideUp()
			$(this).children('span:last').slideDown()
		})

		$('#btn-remove').on('click', function () {
			if (!$(this).hasClass('active')) {
				$('.fa-bars').hide()
				$('.fa-minus-square').show()
				$(this).addClass('active')
			} else {
				$('.fa-minus-square').hide()
				$('.fa-bars').show()
				$(this).removeClass('active')
			}
		})

		$('i.fa-minus-square').on('click', function () {
			let target_tr = $(this).parent().parent()
			let category_cd = $(target_tr).find('input[name=category_cd]').val()
			if (confirm('카테고리를 삭제하면 하위의 포스트도 모두 삭제됩니다. 계속하시겠습니까?')) {
				$.ajax({
					url: '/config/category?' + $.param({category_cd: category_cd}),
					method: 'DELETE',
					dateType: 'json'
				}).then(function (data) {
					if (data.success) {
						$(target_tr).remove()
					} else {
						alert('오류가 발생하여 삭제되지 못하였습니다.')
					}
				})
			}
		})

		if ($('#newAdmin .alert-danger').get().length > 0) $('#newAdmin').modal('show')
	</script>
	${msg}
	</content>
</body>
</html>
