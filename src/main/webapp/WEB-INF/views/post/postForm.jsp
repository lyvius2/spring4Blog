<%--
  Created by IntelliJ IDEA.
  User: yhwang131
  Date: 2017-05-08
  Time: 오전 11:18
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
	<meta charset="UTF-8">
	<title>포스트 쓰기</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.2/css/bootstrap-select.min.css">
	<style>
		section {
			padding-top: 40px;
			padding-bottom: 40px;
		}
		.toggle.btn {
			min-width: 100%;
		}
		#travel-mode {
			display: none;
		}
	</style>
</head>
<body>
<form:form cssClass="form-horizontal" action="/post/register" method="post" commandName="postVO" autocomplete="off" enctype="multipart/form-data">
	<section class="background-gray-lightest">
		<div class="container">
			<div class="breadcrumbs">
				<ul class="breadcrumb">
					<li><a href="index.html">Blog</a></li>
					<li>Writer</li>
				</ul>
			</div>
			<div class="form-group">
				<div class="col-md-2">
					<form:select path="category_cd" cssClass="form-control" required="true">
						<c:forEach var="category" items="${categories}" varStatus="vs">
							<c:if test="${category.use_yn == true}">
								<form:option value="${category.category_cd}"><c:out value="${category.category_name}"/></form:option>
							</c:if>
						</c:forEach>
					</form:select>
				</div>
				<div class="col-md-8">
					<form:input type="text" cssClass="form-control" path="title" id="title" maxlength="100" placeholder="포스트 제목을 입력하세요" required="true"/>
					<form:errors path="title" cssClass="alert alert-danger" role="alert"></form:errors>
				</div>
				<div class="col-md-2">
					<input id="travel-mode-select" type="checkbox" data-toggle="toggle" data-on="Option" data-off="Option" />
				</div>
			</div>
			<div class="form-group" id="travel-mode">
				<div class="col-md-2">
					<div class="input-group date">
						<span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
						<form:input type="text" cssClass="form-control" path="reg_dt"/>
					</div>
				</div>
				<div class="col-md-2">
					<form:select path="post_theme_cd" cssClass="form-control selectpicker">
						<option data-content="여행국가 <span class='caret'></span>" value=""></option>
						<c:forEach var="item" items="${countryList}">
							<option data-content="<i class='${fn:toLowerCase(item.cd)} flag'></i> <c:out value="${item.cd_name}"/>" value="${item.cd}"></option>
						</c:forEach>
					</form:select>
				</div>
				<div class="col-md-8">
					<form:input type="file" cssClass="filestyle" data-buttonText="대표 이미지" data-buttonName="btn-primary" path="delegate_img_file" id="delegate_img_file"/>
				</div>
			</div>
		</div>
	</section>
	<section class="blog-post">
		<div class="container">
			<div class="post-content">
				<form:textarea path="content" />
			</div>
			<div class="form-group">
				<div class="col-md-2">
					<div class="checkbox">
						<label>
							<form:checkbox path="use_yn" tabindex="0"/>
							Show
						</label>
					</div>
				</div>
				<div class="col-md-2">
					<div class="checkbox">
						<label>
							<form:checkbox path="comment_yn" tabindex="0"/>
							Allow Comments
						</label>
					</div>
				</div>
				<div class="col-md-8 text-right">
					<button type="submit" class="btn btn-primary" title="등록">
						Register
					</button>
					<a href="#" class="btn btn-danger" title="취소">
						Cancel
					</a>
				</div>
			</div>
		</div>
	</section>
</form:form>
<content tag="script">
	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.2/js/bootstrap-select.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/scripts/bootstrap-filestyle.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/ckeditor/ckeditor.js"></script>
	<script>
		CKEDITOR.replace('content', {
			extraPlugins:'uploadimage,image2',
			uploadUrl:'/post/dragAndDropUpload',
			filebrowserImageUploadUrl:'/post/imageUpload',
			stylesSet: [
				{ name: 'Narrow image', type: 'widget', widget: 'image', attributes: { 'class': 'image-narrow' } },
				{ name: 'Wide image', type: 'widget', widget: 'image', attributes: { 'class': 'image-wide' } }
			],
			contentsCss: [ CKEDITOR.basePath + 'contents.css', CKEDITOR.basePath + 'widgetstyles.css' ],
			image2_alignClasses: [ 'image-align-left', 'image-align-center', 'image-align-right' ],
			image2_disableResizer: true,
			height: 500
		})

		$('form').on('submit', function () {
			document.getElementById('content').value = CKEDITOR.instances.content.getData()
		})

		$('#travel-mode-select').change(function () {
			if ($(this).prop('checked')) $('#travel-mode').slideDown()
			else {
				$('#travel-mode').slideUp()
				$('button > span.filter-option.pull-left').html('여행국가')
				document.getElementById('post_theme_cd').value = ''
				document.getElementById('delegate_img_file').value = ''
			}
		})

		$('input[name=reg_dt]').datepicker({
			todayBtn: 'linked',
			clearBtn: true,
			language: 'kr',
			multidate: false,
			autoclose: true,
			format: 'yyyy-mm-dd'
		})
	</script>
</content>
</body>
</html>
