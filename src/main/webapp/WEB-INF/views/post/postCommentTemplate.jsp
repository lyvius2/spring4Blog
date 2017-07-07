<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %><%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %><script type="text/x-template" id="post-comment-template">
		<div v-bind:class="index == (len - 1) ? 'row comment last':'row comment'">
			<div class="col-sm-3 col-md-2 text-center-xs">
				<p><img v-bind:src="item.profile_image_url" v-bind:alt="item.userName" class="img-responsive img-circle" style="width: 50px;"></p>
			</div>
			<div class="col-sm-9 col-md-10">
				<span aria-hidden="true" class="text-danger" style="position: absolute; top: 5px; right:10px; cursor: pointer;"
				      v-on:click="remove_comment(item._id)">&times;</span>
				<h5><a v-bind:href="item.link" target="_blank">{{item.userName}}</a></h5>
				<p class="posted"><i class="fa fa-clock-o"></i> {{item.regDt}}</p>
				<p class="text-gray">{{item.comment}}</p>
			</div>
			<div class="col-sm-offset-3 col-md-offset-2 col-sm-9 col-md-10 text-right">
				<p class="reply"><a href="javascript:void(0)" v-on:click="open_reply_modal(item._id, item.userName)"><i class="fa fa-reply"></i> Reply</a></p>
			</div>
			<!-- /.대댓글 -->
			<div v-for="(reply, index) in item.replys" v-bind:key="reply" class="col-sm-offset-3 col-md-offset-2 col-sm-9 col-md-10 replys">
				<span aria-hidden="true" class="text-danger" style="position: absolute; top: 5px; right:10px; cursor: pointer;"
				      v-on:click="remove_reply(item._id, index)">&times;</span>
				<h5><i class="fa fa-reply fa-rotate-180"></i> <a v-bind:href="reply.link" target="_blank">{{reply.userName}}</a></h5>
				<p class="posted"><i class="fa fa-clock-o"></i> {{reply.regDt}}</p>
				<p class="text-gray">{{reply.comment}}</p>
			</div>
			<!-- /.대댓글 -->
		</div>
	</script>