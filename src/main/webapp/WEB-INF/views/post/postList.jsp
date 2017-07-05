<%--
  Created by IntelliJ IDEA.
  User: yhwang131
  Date: 2017-07-05
  Time: 오전 10:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>포스트 리스트</title>
</head>
<body>
	<section class="background-gray-lightest">
		<div class="container">
			<div class="row">
				<div class="col-lg-8">
					<div class="breadcrumbs">
						<ul class="breadcrumb">
							<li><a href="/post">Blog</a></li>
							<li>List</li>
							<li>All</li>
						</ul>
					</div>
				</div>
				<div class="col-lg-4">
					<div class="input-group pull-right">
						<input type="text" class="form-control" placeholder="Search for...">
						<span class="input-group-btn">
						<button type="button" class="btn btn-default">Go!</button>
					</span>
					</div>
				</div>
			</div>
		</div>
	</section>
	<section class="blog-post">
		<div class="container">
			<div class="col-lg-8">
				<div class="row">
					<div class="col-lg-10 col-md-10 col-sm-12 col-lg-offset-1 col-md-offset-1" v-for="(post, index) in postList" v-bind:key="post.post_cd">
						<div class="thumbnail">
							<div class="row date">
								<div class="col-md-6">{{post.category_name}}</div>
								<div class="col-md-6 text-right">{{post.reg_dt|formatDate}}</div>
							</div>
							<div class="image-box" v-if="post.delegate_img != null && post.delegate_img != ''">
								<img src="" v-bind:style="'background: url(/post/images/' + post.delegate_img + ') no-repeat center;'">
							</div>
							<div class="caption">
								<h3>{{post.title}}</h3>
								<p>{{get_stripped_content(post.content)}}</p>
								<p class="text-right">
									<a href="javascript:void(0);" class="btn btn-sm btn-success"><i class="fa fa-pencil-square-o" aria-hidden="true"> 수정</i></a>
									<a href="javascript:void(0);" class="btn btn-sm btn-danger"><i class="fa fa-trash-o" aria-hidden="true"> 삭제</i></a>
								</p>
							</div>
						</div>
					</div>
					<%--
					<div class="col-lg-10 col-md-10 col-sm-12 col-lg-offset-1 col-md-offset-1">
						<div class="thumbnail">
							<div class="row date">
								<div class="col-md-6">여행 이야기</div>
								<div class="col-md-6 text-right">2017-07-06 11:24</div>
							</div>
							<div class="image-box">
								<img src="" style="background: url('${contextPath}/resources/images/norway.jpg') no-repeat center; background-size: cover;">
							</div>
							<div class="caption">
								<h3>Norway in a Nutshell</h3>
								<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ab accusamus amet blanditiis culpa distinctio esse iusto maiores, molestiae numquam optio perferendis reiciendis repellendus sapiente sed sequi sunt ut voluptates? Fugiat.</p>
								<p>
									<a href="#" class="btn btn-sm btn-success"><i class="fa fa-pencil-square-o" aria-hidden="true"> 수정</i></a>
									<a href="#" class="btn btn-sm btn-danger"><i class="fa fa-trash-o" aria-hidden="true"> 삭제</i></a>
								</p>
							</div>
						</div>
					</div>
					--%>
				</div>
			</div>
			<div class="col-lg-4">
				<h4 class="heading">Category</h4>
				<ul class="navigator">
					<c:forEach var="category" items="${categories}" varStatus="vs">
						<c:if test="${category.use_yn == true}">
							<li><a href="javascript:void(0);" data-target="${category.category_cd}"><c:out value="${category.category_name}"/></a></li>
						</c:if>
					</c:forEach>
				</ul>
			</div>
		</div>
	</section>
	<content tag="script">
	<script>
		var posts, nextOffset = 0, rowsPerPage = 20

		function getPostList (param) {
			return (function () {
				return $.get('/api/postList', param)
			})()
		}

		(function () {
			Vue.filter('formatDate', function (value) {
				if (value) return moment(value).format('MMMM DD YYYY, h:mm:ss a')
			})

			getPostList({offset: nextOffset, rowsPerPage: rowsPerPage}).then(function (data) {
				console.log(data)
				if (data) {
					posts = new Vue({
						el: '.blog-post',
						data: {
							postList: data
						},
						methods: {
							get_stripped_content: function (content) {
								return content.replace(/<(?:.|\n)*?>/gm, '').trim()
							}
						}
					})
					if (data.length >= rowsPerPage) nextOffset++;
				}
			})

			/**
			 * 무한 스크롤 구현 시 사용
			 */
			function scrollHandler() {
				if ($(document).height() - 20 <= $(this).height() + $(this).scrollTop()) {
					console.log('go to next rows')
					console.log('stop event handler')
					$(this).off('scroll', scrollHandler)
				}
			}

			$(window).on('scroll', scrollHandler)
		})()
	</script>
	</content>
</body>
</html>
