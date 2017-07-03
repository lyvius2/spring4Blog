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
			<div class="listByPaging" v-cloak>
				<div class="list-group">
					<a href="javascript:void(0);" class="list-group-item" v-for="post in pageList" v-bind:key="post" v-on:click="move_post(post.post_cd)">
						<span class="badge" style="color: #777; background-color: #fff; border-radius: 0px;">{{post.df_reg_dt}}</span>
						<i class="fa fa-caret-right"></i> {{post.title}}
					</a>
					<nav>
						<ul class="pagination">
							<li>
								<a href="javascript:void(0);" aria-label="Previous" v-on:click="move_list(paging.firstPageNo)">
									<span aria-hidden="true">&laquo;</span>
								</a>
							</li>
							<li v-for="num in paging.pagingNumbers" v-bind:class="num == paging.currPageNo ? 'active':''">
								<a href="javascript:void(0);" v-on:click="move_list(num)">{{num}}</a>
							</li>
							<li>
								<a href="javascript:void(0);" aria-label="Next" v-on:click="move_list(paging.finalPageNo)">
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
					</div>
					<!-- /.post-content-->
					<div class="comments" v-cloak>
						<h4>{{list.length}} comment{{list.length > 1 ? 's':''}}</h4>
						<!-- /.comment-->
						<div v-for="(item, index) in list" v-bind:key="item._id" v-bind:class="index == (list.length - 1) ? 'row comment last':'row comment'">
							<div class="col-sm-3 col-md-2 text-center-xs">
								<p><img v-bind:src="item.profile_image_url" v-bind:alt="item.userName" class="img-responsive img-circle" style="width: 50px;"></p>
							</div>
							<div class="col-sm-9 col-md-10">
								<h5><a v-bind:href="item.link" target="_blank">{{item.userName}}</a></h5>
								<p class="posted"><i class="fa fa-clock-o"></i> {{item.regDt}}</p>
								<p class="text-gray">{{item.comment}}</p>
							</div>
							<div class="col-sm-offset-3 col-md-offset-2 col-sm-9 col-md-10 text-right">
								<p class="reply"><a href="javascript:void(0)" v-on:click="open_reply_modal(item._id, item.userName)"><i class="fa fa-reply"></i> Reply</a></p>
							</div>
							<!-- /.대댓글 -->
							<div v-for="(reply, index) in item.replys" v-bind:key="reply" class="col-sm-offset-3 col-md-offset-2 col-sm-9 col-md-10 replys">
								<span aria-hidden="true" class="text-danger"
								      style="position: absolute; top: 5px; right:10px; cursor: pointer;" v-on:click="remove_reply(item.id, index)">&times;</span>
								<h5><i class="fa fa-reply fa-rotate-180"></i> <a v-bind:href="reply.link" target="_blank">{{reply.userName}}</a></h5>
								<p class="posted"><i class="fa fa-clock-o"></i> {{reply.regDt}}</p>
								<p class="text-gray">{{reply.comment}}</p>
							</div>
							<!-- /.대댓글 -->
						</div>
						<!-- /.comment-->
						<div class="modal fade" id="newReply" tabindex="-1">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal" aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
										<h4 class="modal-title">{{replyTo}} 님 댓글에 대한 대댓글</h4>
									</div>
									<div class="modal-body">
										<textarea id="reply_comment" name="reply_comment" rows="4" class="form-control" required></textarea>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-sm btn-default" data-dismiss="modal">
											<i class="fa fa-times-circle" aria-hidden="true"></i> 닫기
										</button>
										<button type="button" class="btn btn-sm btn-primary" v-on:click="insert_reply()">
											<i class="fa fa-check-circle" aria-hidden="true"></i> 저장
										</button>
									</div>
								</div>
							</div>
						</div>
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
										<textarea id="comment" name="comment" rows="4" class="form-control"></textarea>
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
		var pagination, comments
		const category_cd = document.viewForm.category_cd.value
		const commentFrm = document.commentForm;

		function executePost (url, param) {
			return (function () {
				return $.ajax({
					type: 'POST',
					url: url,
					data: param,
					dataType: 'json',
					contentType: 'application/x-www-form-urlencoded; charset=UTF-8'
				})
			})()
		}

		function getPostList (currPageNo, categoryCd, callback) {
			let params = {currPageNo: currPageNo, category_cd: categoryCd}
			$.get('/post/list', params).then(function (data) {
				document.viewForm.currPageNo.value = currPageNo
				return callback(data)
			})
		}

		function getComments (callback) {
			$.get('/post/comment', {postCd: document.commentForm.postCd.value}).then(function (data) {
				console.log(data)
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
						list: data,
						replyTo: '',
						replyTargetId: ''
					},
					methods: {
						open_reply_modal: function(_id, userName) {
							this.replyTargetId = _id
							this.replyTo = userName
							$('#newReply').modal('show')
						},
						insert_reply: function() {
							executePost('/post/reply',
								$.param({_id: this.replyTargetId, comment: $('#reply_comment').val()}))
								.then(function (data) {
									if (data.status) {
										getComments(function (data) {
											comments.list = data
										})
									} else {
										alert('대댓글 내용을 입력하세요.');
									}
									$('#newReply').modal('hide')
									return false
								})
						},
						remove_reply: function(_id, index) {
							$.ajax({
								type: 'DELETE',
								url: '/post/reply' + '?' + $.param({_id: _id, index: index}),
								dataType: 'json'
							}).then(function () {
								getComments(function (data) {
									comments.list = data
								})
							})
						}
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
				executePost('/post/comment', $(commentFrm).serialize()).then(
					function (data) {
						if (data.status) {
							commentFrm.comment.value = ''
							getComments(function (data) {
								comments.list = data
							})
						} else {
							alert('댓글 내용을 입력하세요.')
						}
						return false
					}
				)
			})

			$(document).on('shown.bs.modal', '#newReply', function() {
				$('#reply_comment').focus()
			})

			$(document).on('hide.bs.modal', '#newReply', function() {
				comments.replyTargetId = ''
				comments.replyTo = ''
				$('#reply_comment').val('')
			})
		})()
	</script>
	</content>
</body>
</html>
