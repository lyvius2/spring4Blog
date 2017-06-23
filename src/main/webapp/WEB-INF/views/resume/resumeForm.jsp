<%--
  Created by IntelliJ IDEA.
  User: yhwang131
  Date: 2017-06-21
  Time: 오전 9:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
	<title>Profile Editor</title>
	<!-- Noto Sans KR-Hestia -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/resume/NotoSansKR-Hestia.css">
	<!-- custom css -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/resume/resume.css">
	<style>
		.label-primary,.label-success,.add-click {
			cursor: pointer;
		}
	</style>
</head>
<body>
<form id="resumeVO" action="/resume" method="post" enctype="multipart/form-data" autocomplete="off">
	<div class="resume-wrapper">

		<!-- header -->
		<header class="resume">
			<h1 class="heading-container">Résumé</h1>
			<h2 class="name-container"><input type="text" class="form-control" name="name" v-model="data.name" placeholder="한글명" tabindex="5"/></h2>
			<h3 class="job-container"><input type="text" class="form-control" name="eng_name" v-model="data.eng_name" placeholder="영문명" tabindex="6" /></h3>
			<div class="profile-picture">
				<img v-if="data.image_url != null" v-bind:src="'${pageContext.request.contextPath}/resources/images/profile/' + data.image_url" alt="Profile">
				<input type="file" name="profile"/>
				<input type="hidden" name="image_url" v-model="data.image_url"/>
			</div>
			<div class="contact-container">
				<ul class="contact-list">
					<li>
						<div class="input-group input-group-sm">
							<input type="email" class="form-control" name="email" v-model="data.email" placeholder="E-Mail" tabindex="1"/>
						</div>
						<span><i class="fa fa-envelope-o" aria-hidden="true"></i></span>
					</li>
					<li>
						<div class="input-group input-group-sm">
							<input type="text" class="form-control" name="web_addr" v-model="data.web_addr" placeholder="홈페이지 URL" tabindex="2"/>
						</div>
						<span><i class="fa fa-globe" aria-hidden="true"></i></span>
					</li>
					<li>
						<div class="input-group input-group-sm">
							<input type="text" class="form-control" name="git_addr" v-model="data.gir_addr" placeholder="GitHub URL" tabindex="3"/>
						</div>
						<span><i class="fa fa-github" aria-hidden="true"></i></span>
					</li>
					<li>
						<div class="input-group input-group-sm">
							<input type="text" class="form-control" name="real_addr" v-model="data.real_addr" placeholder="주소" tabindex="4"/>
						</div>
						<span><i class="fa fa-map-marker" aria-hidden="true"></i></span>
					</li>
				</ul>
			</div>
		</header><!-- header -->

		<!-- main -->
		<section id="main">
			<div class="resume-item-wrapper">

				<!-- experience -->
				<section class="resume-item experience">
					<div class="inner">
						<h2>experience</h2>
						<div class="form-horizontal">
							<act-form v-for="(item, index) in data.experience"
							                 v-bind:key="index"
							                 :seq="index" :data="item" :flag="'experience'"></act-form>
						</div>
						<div class="text-right">
							<i class="fa fa-plus text-success add-click" aria-hidden="true" title="추가" v-on:click="item_plus('experience')"></i>
						</div>
					</div>
				</section><!-- experience -->


				<!-- project -->
				<section class="resume-item project">
					<div class="inner">
						<h2>project</h2>
						<div class="form-horizontal">
							<act-form v-for="(item, index) in data.project"
							          v-bind:key="index"
							          :seq="index" :data="item" :flag="'project'"></act-form>
							<div class="text-right">
								<i class="fa fa-plus text-success add-click" aria-hidden="true" title="추가" v-on:click="item_plus('project')"></i>
							</div>
						</div>
					</div>
				</section><!-- project -->

				<!-- skill -->
				<section class="resume-item skill">
					<div class="inner">
						<h2>Skill</h2>
						<act-form v-for="(item, index) in data.skill"
						              v-bind:key="index"
						              :seq="index" :data="item" :flag="'skill'"></act-form>
						<div class="text-right">
							<i class="fa fa-plus text-success add-click" aria-hidden="true" title="추가" v-on:click="item_plus('skill')"></i>
						</div>
					</div>
				</section><!-- skill -->

				<section class="resume-item">
					<div style="padding-top: 20px;">
						<button type="submit" class="btn btn-primary btn-sm">
							<i class="fa fa-check" aria-hidden="true"> 저장</i>
						</button>
					</div>
				</section>

			</div>
		</section><!-- main -->

		<!-- footer -->
		<footer>
			© 2016 <a href="https://github.com/dhparkdh" target="-_blank">dhpark</a>. All rights reserved.
		</footer><!-- footer -->

	</div>
</form>
<content tag="script">
	<script type="text/x-template" id="act-form-template">
		<div v-bind:id="flag + '_index_' + seq">
			<div v-if="flag != 'skill'">
				<div class="form-group">
					<div class="col-sm-3"><h5>[{{seq + 1}}]</h5></div>
					<div class="col-sm-9 text-right">
						<i class="fa fa-times text-danger add-click" aria-hidden="true" title="삭제" v-on:click="item_remove(flag, seq)"></i>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">Title</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" v-bind:name="flag + '[' + seq + '].title'" v-model="data.title"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">{{flag == 'experience' ? 'Company, Rank':'Work Role'}}</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" v-bind:name="flag + '[' + seq + '].sub_title'" v-model="data.sub_title"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">Period</label>
					<div class="col-sm-4">
						<input type="date" class="form-control" v-bind:name="flag + '[' + seq + '].start_dt'" v-model="data.str_start_dt"/>
					</div>
					<div class="col-sm-1 text-center">~</div>
					<div class="col-sm-4">
						<input type="date" class="form-control" v-bind:name="flag + '[' + seq + '].end_dt'" v-model="data.str_end_dt"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">Detail</label>
					<div class="col-sm-9">
						<textarea class="form-control" v-bind:name="flag + '[' + seq + '].description'" v-model="data.description" rows="3"/>
					</div>
				</div>
				<div class="form-group" v-if="flag == 'project'">
					<label class="col-sm-3 control-label" style="padding-top: 0px;">Tech</label>
					<div class="col-sm-9">
						<span v-for="(item, index) in (data.tech || '').split('#')" v-bind:key="item" class="label label-primary">{{item}}</span>
						<span class="label label-success"><i class="fa fa-plus-circle" aria-hidden="true" title="추가"></i></span>
						<input type="hidden" v-bind:name="'project[' + seq + '].tech'" v-model="data.tech"/>
					</div>
				</div>
			</div>
			<div class="row" v-if="flag == 'skill'">
				<div class="col-sm-3">
					<div class="input-group input-group-sm">
						<input type="text" class="form-control" v-bind:name="'skill[' + seq + '].title'" v-model="data.title" placeholder="skill name"/>
					</div>
				</div>
				<div class="col-sm-7">
					<input type="range" class="form-control" v-bind:name="'skill[' + seq + '].level'" v-model="data.level"
					       v-bind:onchange="'skill' + seq + '_level.value = value'" min="0" max="100"/>
				</div>
				<div class="col-sm-2 text-right">
					<output v-bind:id="'skill' + seq + '_level'" style="display:inline-block">{{data.level}}</output>
					&nbsp;
					<i class="fa fa-times text-danger add-click" aria-hidden="true" title="삭제" v-on:click="item_remove(flag, seq)"></i>
				</div>
			</div>
		</div>
	</script>
	<script>
		var resume;

		function createActVO(flag) {
			this.title = ''
			switch(flag) {
				case 'skill' :
					this.level = 0
					break;
				case 'project' :
					this.tech = ''
				default :
					this.sub_title = ''
					this.description = ''
					this.start_dt = ''
					this.end_dt = ''
			}
		}

		Vue.component('act-form', {
			template: '#act-form-template',
			props: {
				seq: {type: Number, required: true},
				data: {type: Object, required: true},
				flag: {type: String, required: true}
			},
			methods: {
				item_remove: function(item, index) {
					resume.data[item].splice(index, 1)
				}
			}
		})

		$.get('/resume/api').then(function (result) {
			console.log(result)
			resume = new Vue({
				el: '.resume-wrapper',
				data: {data: result},
				methods: {
					item_plus: function(item) {
						this.data[item].push(new createActVO(item))
					}
				}
			})
		})
	</script>
</content>
</body>
</html>
