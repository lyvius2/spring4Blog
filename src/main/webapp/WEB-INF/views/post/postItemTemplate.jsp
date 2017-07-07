<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %><%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %><script type="text/x-template" id="post-item-template">
		<div class="col-lg-10 col-md-10 col-sm-12 col-lg-offset-1 col-md-offset-1">
			<div class="thumbnail">
				<div class="row date">
					<div class="col-md-6" v-html="post.category_name"></div>
					<div class="col-md-6 text-right">{{post.reg_dt|formatDate}}</div>
				</div>
				<div class="image-box" v-if="post.delegate_img != null && post.delegate_img != ''">
					<img src="" v-bind:style="'background: url(/post/images/' + post.delegate_img + ') no-repeat center;'">
				</div>
				<div class="caption">
					<h3 class="add-click" v-html="post.title" v-on:click="move_to_post(post.post_cd)"></h3>
					<p class="add-click" v-on:click="move_to_post(post.post_cd)">{{get_stripped_content(post.content)}}</p>
					<security:authorize access="isAuthenticated()">
						<p class="text-right">
							<a href="javascript:void(0);" class="btn btn-sm btn-success" v-on:click="modify_post(post.post_cd)"><i class="fa fa-pencil-square-o" aria-hidden="true"> 수정</i></a>
							<a href="javascript:void(0);" class="btn btn-sm btn-danger" v-on:click="remove_post(post.post_cd, post.delegate_img, index, $event)"><i class="fa fa-trash-o" aria-hidden="true"> 삭제</i></a>
						</p>
					</security:authorize>
				</div>
			</div>
		</div>
	</script>