<%--
  Created by IntelliJ IDEA.
  User: yhwang131
  Date: 2017-04-20
  Time: 오후 5:50
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
	<title>포스트 - <c:out value="${post.title}"/></title>
</head>
<body>
	<section class="background-gray-lightest">
		<div class="container">
			<div class="breadcrumbs">
				<ul class="breadcrumb">
					<li><a href="index.html">Blog</a></li>
					<li class="add-click"><c:out value="${post.category_name}"/> <i class="fa fa-angle-double-down"></i></li>
				</ul>
			</div>
			<div class="listByPaging">
				<div class="list-group">
					<a href="#" class="list-group-item" v-for="post in pageList" v-bind:key="post" v-on:click="move_post(post.post_cd)">
						<span class="badge" style="color: #777; background-color: #fff; border-radius: 0px;">{{post.df_reg_dt}}</span>
						<i class="fa fa-caret-right"></i> {{post.title}}
					</a>
					<nav>
						<ul class="pagination">
							<li>
								<a href="#" aria-label="Previous" v-on:click="move_list(paging.firstPageNo)">
									<span aria-hidden="true">&laquo;</span>
								</a>
							</li>
							<li v-for="num in paging.pagingNumbers" v-bind:class="num == paging.currPageNo ? 'active':''"><a href="#" v-on:click="move_list(num)">{{num}}</a></li>
							<li>
								<a href="#" aria-label="Next" v-on:click="move_list(paging.finalPageNo)">
									<span aria-hidden="true">&raquo;</span>
								</a>
							</li>
						</ul>
					</nav>
				</div>
			</div>
			<h1 class="heading"><c:out value="${post.title}"/></h1>
			<%--
			<p class="lead">This is the lead paragraph of the article. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Vestibulum tortor quam, feugiat vitae, ultricies eget.</p>
			--%>
		</div>
	</section>
	<c:if test="${post.delegate_img != null && post.delegate_img != ''}">
	<figure class="full-image"><img src="/post/images/${post.delegate_img}" alt="">
		<figcaption><c:out value="${image_spec}"/></figcaption>
	</figure>
	</c:if>
	<section class="blog-post">
		<div class="container">
			<div class="row">
				<div class="col-lg-8">
					<div class="post-content">
						<c:out value="${post.content}" escapeXml="false"/>
						<%--
						<p><strong>Pellentesque habitant morbi tristique</strong> senectus et netus et malesuada fames ac turpis egestas. Vestibulum tortor quam, feugiat vitae, ultricies eget, tempor sit amet, ante. Donec eu libero sit amet quam egestas semper. <em>Aenean ultricies mi vitae est.</em> Mauris placerat eleifend leo. Quisque sit amet est et sapien ullamcorper pharetra. Vestibulum erat wisi, condimentum sed, <code>commodo vitae</code>, ornare sit amet, wisi. Aenean fermentum, elit eget tincidunt condimentum, eros ipsum rutrum orci, sagittis tempus lacus enim ac dui. <a href="#">Donec non enim</a> in turpis pulvinar facilisis. Ut felis.</p>
						<h2>Header Level 2</h2>
						<ol>
							<li>Lorem ipsum dolor sit amet, consectetuer adipiscing elit.</li>
							<li>Aliquam tincidunt mauris eu risus.</li>
						</ol>
						<blockquote>
							<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus magna. Cras in mi at felis aliquet congue. Ut a est eget ligula molestie gravida. Curabitur massa. Donec eleifend, libero at sagittis mollis, tellus est malesuada tellus, at luctus turpis elit sit amet quam. Vivamus pretium ornare est.</p>
						</blockquote>
						<h3>Header Level 3</h3>
						<p>Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Vestibulum tortor quam, feugiat vitae, ultricies eget, tempor sit amet, ante. Donec eu libero sit amet quam egestas semper. Aenean ultricies mi vitae est. Mauris placerat eleifend leo. Quisque sit amet est et sapien ullamcorper pharetra. Vestibulum erat wisi, condimentum sed, commodo vitae, ornare sit amet, wisi. Aenean fermentum, elit eget tincidunt condimentum, eros ipsum rutrum orci, sagittis tempus lacus enim ac dui. Donec non enim in turpis pulvinar facilisis. Ut felis. Praesent dapibus, neque id cursus faucibus, tortor neque egestas augue, eu vulputate magna eros eu erat. Aliquam erat volutpat. Nam dui mi, tincidunt quis, accumsan porttitor, facilisis luctus, metus</p>
						<ul>
							<li>Lorem ipsum dolor sit amet, consectetuer adipiscing elit.</li>
							<li>Aliquam tincidunt mauris eu risus.</li>
						</ul>
						<p><img src="img/blog1.jpg" alt="Example blog post alt" class="img-responsive"></p>
						<p>Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Vestibulum tortor quam, feugiat vitae, ultricies eget, tempor sit amet, ante. Donec eu libero sit amet quam egestas semper. Aenean ultricies mi vitae est. Mauris placerat eleifend leo. Quisque sit amet est et sapien ullamcorper pharetra. Vestibulum erat wisi, condimentum sed, commodo vitae, ornare sit amet, wisi. Aenean fermentum, elit eget tincidunt condimentum, eros ipsum rutrum orci, sagittis tempus lacus enim ac dui. Donec non enim in turpis pulvinar facilisis. Ut felis. Praesent dapibus, neque id cursus faucibus, tortor neque egestas augue, eu vulputate magna eros eu erat. Aliquam erat volutpat. Nam dui mi, tincidunt quis, accumsan porttitor, facilisis luctus, metus</p>
						--%>
					</div>
					<!-- /.post-content-->
					<div class="comments">
						<h4>2 comments</h4>
						<div v-for="(item, index) in list" v-bind:key="item._id" v-bind:class="index == (list.length - 1) ? 'row comment last':'row comment'">
							<div class="col-sm-3 col-md-2 text-center-xs">
								<p><a v-bind:href="item.link" target="_blank">
									<img v-bind:src="item.profile_image_url" v-bind:alt="item.userName" class="img-responsive img-circle">
								</a></p>
							</div>
							<div class="col-sm-9 col-md-10">
								<h5>{{item.userName}}</h5>
								<p class="posted"><i class="fa fa-clock-o"></i> {{item.regDt}}</p>
								<p class="text-gray">{{item.comment}}</p>
								<p class="reply"><a href="#"><i class="fa fa-reply"></i> Reply</a></p>
							</div>
							<!--
							<div class="col-sm-offset-3 col-md-offset-2 col-sm-9 col-md-10 row">
								<div class="col-sm-3 col-md-2 text-center-xs">
									<p><img src="img/blog-avatar2.jpg" alt="" class="img-responsive img-circle"></p>
								</div>
								<div class="col-sm-9 col-md-10">
									<h5>Julie Alma</h5>
									<p class="posted"><i class="fa fa-clock-o"></i> September 23, 2011 at 12:00 am</p>
									<p class="text-gray">Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Vestibulum tortor quam, feugiat vitae, ultricies eget, tempor sit amet, ante. Donec eu libero sit amet quam egestas semper. Aenean ultricies mi vitae est. Mauris placerat eleifend leo.</p>
								</div>
							</div>
							-->
						</div>
						<!-- /.comment-->
						<%--
						<div class="row comment last">
							<div class="col-sm-3 col-md-2 text-center-xs">
								<p><img src="img/blog-avatar.jpg" alt="" class="img-responsive img-circle"></p>
							</div>
							<div class="col-sm-9 col-md-10">
								<h5>Barbara Corazon</h5>
								<p class="posted"><i class="fa fa-clock-o"></i> September 23, 2012 at 12:00 am</p>
								<p class="text-gray">Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Vestibulum tortor quam, feugiat vitae, ultricies eget, tempor sit amet, ante. Donec eu libero sit amet quam egestas semper. Aenean ultricies mi vitae est. Mauris placerat eleifend leo.</p>
								<p class="reply"><a href="#"><i class="fa fa-reply"></i> Reply</a></p>
							</div>
						</div>
						--%>
						<!-- /.comment-->
					</div>
					<!-- /#comments-->
					<div class="comment-form">
						<h4>Leave comment</h4>
						<form name="commentForm" method="post" onsubmit="return false;">
							<input type="hidden" name="postCd" value="${post.post_cd}"/>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group">
										<label for="comment">Comment <span class="required">*</span></label>
										<textarea id="comment" rows="4" class="form-control"></textarea>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12 text-right">
									<button type="button" class="btn btn-primary" id="post-comment"><i class="fa fa-comment-o"></i> Post comment</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</section>
	<form name="viewForm" method="post" onsubmit="return false;">
		<input type="hidden" name="currPageNo" value="<c:out value="${currPageNo}"/>"/>
		<input type="hidden" name="category_cd" value="<c:out value="${post.category_cd}"/>"/>
	</form>
	<content tag="script">
	<script>
		var pagination, comments;
		const category_cd = document.viewForm.category_cd.value
		const commentFrm = document.commentForm;

		function getPostList (currPageNo, categoryCd, callback) {
			let params = {currPageNo: currPageNo, category_cd: categoryCd}
			$.get('/post/list', params).then(function (data) {
				document.viewForm.currPageNo.value = currPageNo
				return callback(data)
			})
		}

		function getComments (callback) {
			$.get('/post/comment', {postCd: document.commentForm.postCd.value}).then(function (data) {
				return callback(data)
			})
		}

		function createPagingNumArray (paging) {
			let array = new Array()
			array.push(paging.startPageNo)
			let len = paging.endPageNo - paging.startPageNo + 1
			for(var i = 1; i < len; i++) {
				array.push(paging.startPageNo + i)
			}
			paging['pagingNumbers'] = array
			return paging;
		}

		(function () {
			let currPageNo = document.viewForm.currPageNo.value
			getPostList(currPageNo, category_cd, function (data) {
				pagination = new Vue({
					el: '.listByPaging',
					data: {
						pageList: data.postList,
						paging: createPagingNumArray(data.paging)
					},
					methods: {
						move_list: function (pageNo) {
							getPostList(pageNo, category_cd, function (data) {
								this.pageList = data.postList
								this.paging = createPagingNumArray(data.paging)
							})
						},
						move_post: function (postCd) {
							document.viewForm.action = '/post/' + postCd;
							document.viewForm.submit();
						}
					}
				})
			})

			getComments(function (data) {
				comments = new Vue({
					el: '.comments',
					data: {
						list: data
					}
				})
			})

			$('.add-click').on('click', function () {
				let parent = this
				let isNode = $(this).find('i').hasClass('fa-angle-double-down')
				$('.listByPaging').toggle('blind', function () {
					if (isNode) $(parent).find('i').removeClass('fa-angle-double-down').addClass('fa-angle-double-up')
					else $(parent).find('i').removeClass('fa-angle-double-up').addClass('fa-angle-double-down')
				})
			})

			$('#post-comment').on('click', function (e) {
				e.preventDefault()
				$.ajax({
					type: 'POST',
					url: '/post/comment',
					data: $.param({postCd: Number(commentFrm.postCd.value), comment: commentFrm.comment.value}),
					dataType: 'json',
					contentType: 'application/x-www-form-urlencoded; charset=UTF-8'
				}).then(function (data) {
					if (data.status) {
						commentFrm.comment.value = ''
						getComments(function (data) {
							comments.list = data
						})
					} else {
						alert('댓글 내용을 입력하세요.')
					}
					return false
				});
			})
		})()

	</script>
	</content>
</body>
</html>
