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
							<input type="text" class="form-control" name="git_addr" v-model="data.git_addr" placeholder="GitHub URL" tabindex="3"/>
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
		<section id="main" style="padding-bottom: 0px;">
			<div class="resume-item-wrapper">

				<!-- experience -->
				<section class="resume-item experience">
					<div class="inner">
						<h2>경력</h2>
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
						<h2>프로젝트 이력</h2>
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
				<section class="resume-item">
					<div class="inner">
						<h2>보유기술</h2>
						<act-form v-for="(item, index) in data.skill"
						              v-bind:key="index"
						              :seq="index" :data="item" :flag="'skill'"></act-form>
						<div class="text-right">
							<i class="fa fa-plus text-success add-click" aria-hidden="true" title="추가" v-on:click="item_plus('skill')"></i>
						</div>
					</div>
				</section><!-- skill -->

				<!-- education -->
				<section class="resume-item experience">
					<div class="inner">
						<h2>이수교육</h2>
						<div class="form-horizontal">
							<act-form v-for="(item, index) in data.education"
							          v-bind:key="index"
							          :seq="index" :data="item" :flag="'education'"></act-form>
						</div>
						<div class="text-right">
							<i class="fa fa-plus text-success add-click" aria-hidden="true" title="추가" v-on:click="item_plus('education')"></i>
						</div>
					</div>
				</section><!-- education -->


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
			Customized by <a href="https://github.com/lyvius2" target="_blank">walter.hwang</a>
		</footer><!-- footer -->

	</div>
</form>
<div class="modal fade" id="newTechTag" tabindex="-1">
	<div class="modal-dialog">
		<form id="techTag" onsubmit="return false">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">기술태그 추가</h4>
			</div>
			<div class="modal-body">
				<input type="text" name="tech_tag" class="form-control input-lg" placeholder="기술명을 입력하세요." required/>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-sm btn-default" data-dismiss="modal">
					<i class="fa fa-times-circle" aria-hidden="true"></i> 닫기
				</button>
				<button type="submit" class="btn btn-sm btn-primary">
					<i class="fa fa-check-circle" aria-hidden="true"></i> 등록
				</button>
			</div>
		</div>
		</form>
	</div>
</div>
<content tag="script">
	<jsp:include page="resumeFormTemplate.jsp"/>
	<script>
		function createActVO(flag) {
			this.title = ''
			switch(flag) {
				case 'skill' :
					this.level = 0
					break;
				case 'project' :
					this.tech = ''
					this.related_site = ''
				default :
					this.sub_title = ''
					this.description = ''
					this.start_dt = ''
					this.end_dt = ''
			}
		}

		function createDatepicker(elements) {
			$(elements).datepicker({
				todayBtn: "linked",
				language: "kr",
				multidate: false,
				autoclose: true,
				format: 'yyyy-mm-dd'
			})
		}

		var form_titles = {
			titles: {
				experience: {title: '회사명', sub_title: '소속 및 직급', description: '담당업무'},
				project: {title: '프로젝트', sub_title: '상세', description: '담당역할'},
				education: {title: '과정명', sub_title: '교육기관'}
			}
		}

		$(document).onCreate('div.input-daterange', function (elements) {
			createDatepicker(elements)
		})

		Vue.component('act-form', {
			template: '#act-form-template',
			props: {
				seq: {type: Number, required: true},
				data: {type: Object, required: true},
				flag: {type: String, required: true}
			},
			data: function() {
				return form_titles
			},
			methods: {
				item_remove: function(item, index) {
					resume.data[item].splice(index, 1)
				},
				tech_tag_remove: function(item, seq) {
					let tech_string = resume.data.project[seq].tech
					let start_idx = tech_string.indexOf('/' + item)
					let item_len = item.length + 1
					resume.data.project[seq].tech = tech_string.substring(0, start_idx) + tech_string.substring(start_idx + item_len)
				},
				open_tech_tag_modal: function(seq) {
					resume.project_seq = seq
					$('#newTechTag').modal('show')
				}
			}
		})

		var resume;
		$.get('/resume/api').then(function (result) {
			setTimeout(function () {
				resume = new Vue({
					el: '.resume-wrapper',
					data: {
						data: result,
						project_seq: 0
					},
					methods: {
						item_plus: function(item) {
							this.data[item].push(new createActVO(item))
							setTimeout(function () {
								createDatepicker('div.input-daterange')
							}, 100)
						}
					}
				})
			}, 100)
		})

		$('#newTechTag').on('shown.bs.modal', function() {
			$('input[name=tech_tag]').focus()
		})

		$('#newTechTag').on('hide.bs.modal', function() {
			$('input[name=tech_tag]').val(null)
		})

		$('form#techTag').on('submit', function(e) {
			e.preventDefault()
			let tech_tag = $('input[name=tech_tag]').val()
			resume.data.project[resume.project_seq].tech = resume.data.project[resume.project_seq].tech + '/' + tech_tag
			$('#newTechTag').modal('hide')
		})
	</script>
</content>
</body>
</html>
