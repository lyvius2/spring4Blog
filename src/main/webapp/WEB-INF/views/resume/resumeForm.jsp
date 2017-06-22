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
<form:form action="/resume" method="post" commandName="resumeVO" autocomplete="off" enctype="multipart/form-data">
	<div class="resume-wrapper">

		<!-- header -->
		<header class="resume">
			<h1 class="heading-container">Résumé</h1>
			<h2 class="name-container"><form:input type="text" cssClass="form-control" path="name" placeholder="korean name" tabindex="5"/></h2>
			<h3 class="job-container"><form:input type="text" cssClass="form-control" path="eng_name" placeholder="english name" tabindex="6"/></h3>
			<div class="profile-picture">
				<img src="${pageContext.request.contextPath}/resources/images/profile.png" alt="Profile">
			</div>
			<div class="contact-container">
				<ul class="contact-list">
					<li>
						<div class="input-group input-group-sm">
							<form:input type="email" cssClass="form-control" path="email" placeholder="email address" tabindex="1"/>
						</div>
						<span><i class="fa fa-envelope-o" aria-hidden="true"></i></span>
					</li>
					<li>
						<div class="input-group input-group-sm">
							<form:input type="text" cssClass="form-control" path="web_addr" placeholder="homepage address" tabindex="2"/>
						</div>
						<span><i class="fa fa-globe" aria-hidden="true"></i></span>
					</li>
					<li>
						<div class="input-group input-group-sm">
							<form:input type="text" cssClass="form-control" path="git_addr" placeholder="github address" tabindex="3"/>
						</div>
						<span><i class="fa fa-github" aria-hidden="true"></i></span>
					</li>
					<li>
						<div class="input-group input-group-sm">
							<form:input type="text" cssClass="form-control" path="real_addr" placeholder="location address" tabindex="4"/>
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
				<section class="resume-item experience" id="test">
					<div class="inner">
						<h2>experience</h2>
						<div class="form-horizontal">
							<%--
							<c:forEach items="${resumeVO.experience}" varStatus="vs">
							<div id="experience_index_${vs.index}">
								<div class="form-group">
									<div class="col-sm-3"><h5>[${vs.index + 1}]</h5></div>
									<div class="col-sm-9 text-right">
										<i class="fa fa-times text-danger add-click" aria-hidden="true" title="삭제"></i>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">Title</label>
									<div class="col-sm-9">
										<form:input type="text" cssClass="form-control" path="experience[${vs.index}].title"/>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">Company, Rank</label>
									<div class="col-sm-9">
										<form:input type="text" cssClass="form-control" path="experience[${vs.index}].sub_title"/>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">Period</label>
									<div class="col-sm-4">
										<form:input type="date" cssClass="form-control" path="experience[${vs.index}].start_dt"/>
									</div>
									<div class="col-sm-1 text-center">~</div>
									<div class="col-sm-4">
										<form:input type="date" cssClass="form-control" path="experience[${vs.index}].end_dt"/>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">Detail</label>
									<div class="col-sm-9">
										<form:textarea cssClass="form-control" path="experience[${vs.index}].description" rows="3"/>
									</div>
								</div>
							</div>
							</c:forEach>
							--%>
							<experience-form v-for="(item, index) in items" v-bind:key="item ":seq="index"></experience-form>
								<form:input type="text" cssClass="form-control" path="experience[3].title"/>
								<form:input type="text" cssClass="form-control" path="experience[4].title"/>
								<input type="text" class="form-control" name="experience[5].title" id="experience5.title"/>
								<input type="text" class="form-control" name="experience[6].title" id="experience6.title"/>
						</div>
						<div class="text-right">
							<i class="fa fa-plus text-success add-click" aria-hidden="true" title="추가"></i>
						</div>
					</div>
				</section><!-- experience -->

				<%--
				<!-- project -->
				<section class="resume-item project">
					<div class="inner">
						<h2>project</h2>
						<div class="form-horizontal">
							<c:forEach var="project" items="${resumeVO.project}" varStatus="vs">
								<div class="form-group">
									<div class="col-sm-3"><h5>[${vs.index + 1}]</h5></div>
									<div class="col-sm-9 text-right">
										<i class="fa fa-times text-danger add-click" aria-hidden="true" title="삭제"></i>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">Title</label>
									<div class="col-sm-9">
										<form:input type="text" cssClass="form-control" path="project[${vs.index}].title"/>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">Work Role</label>
									<div class="col-sm-9">
										<form:input type="text" cssClass="form-control" path="project[${vs.index}].sub_title"/>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">Period</label>
									<div class="col-sm-4">
										<form:input type="date" cssClass="form-control" path="project[${vs.index}].start_dt"/>
									</div>
									<div class="col-sm-1 text-center">~</div>
									<div class="col-sm-4">
										<form:input type="date" cssClass="form-control" path="project[${vs.index}].end_dt"/>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">Detail</label>
									<div class="col-sm-9">
										<form:textarea cssClass="form-control" path="project[${vs.index}].description" rows="3"/>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label" style="padding-top: 0px;">Tech</label>
									<div class="col-sm-9">
										<c:forEach var="tech" items="${fn:split(project.tech,'#')}" varStatus="vss">
										<span class="label label-primary">#${tech}</span>
										</c:forEach>
										<form:input type="hidden" path="project[${vs.index}].tech"/>
										<span class="label label-success">
											<i class="fa fa-plus-circle" aria-hidden="true" title="추가"></i>
										</span>
									</div>
								</div>
							</c:forEach>
							<div class="text-right">
								<i class="fa fa-plus text-success add-click" aria-hidden="true" title="추가"></i>
							</div>
						</div>
					</div>
				</section><!-- project -->

				<!-- skill -->
				<section class="resume-item skill">
					<div class="inner">
						<h2>Skill</h2>
						<c:forEach var="skill" items="${resumeVO.skill}" varStatus="vs">
						<div class="row">
							<div class="col-sm-3">
								<div class="input-group input-group-sm">
									<form:input type="text" cssClass="form-control" path="skill[${vs.index}].title" placeholder="skill name"/>
								</div>
							</div>
							<div class="col-sm-7">
								<form:input type="range" cssClass="form-control" path="skill[${vs.index}].level" min="0" max="100" onchange="skill${vs.index}_level.value = value"/>
							</div>
							<div class="col-sm-2 text-right">
								<output id="skill${vs.index}_level" style="display:inline-block">${skill.level}</output>
								&nbsp;
								<i class="fa fa-times text-danger add-click" aria-hidden="true" title="삭제"></i>
							</div>
						</div>
						</c:forEach>
						<div class="text-right">
							<i class="fa fa-plus text-success add-click" aria-hidden="true" title="추가"></i>
						</div>
					</div>
				</section><!-- skill -->
				--%>
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
</form:form>
<content tag="script">
	<script type="text/x-template" id="experience-template">
		<div v-bind:id="'experience_index_' + seq">
			<div class="form-group">
				<div class="col-sm-3"><h5>[{{seq + 1}}]</h5></div>
				<div class="col-sm-9 text-right">
					<i class="fa fa-times text-danger add-click" aria-hidden="true" title="삭제"></i>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">Title</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" v-bind:name="'experience[' + seq + '].title'"
					       v-bind:id="'experience' + seq + '.title'"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">Company, Rank</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" v-bind:name="'experience[' + seq + '].sub_title'"
					       v-bind:id="'experience' + seq + '.sub_title'"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">Period</label>
				<div class="col-sm-4">
					<input type="date" class="form-control" v-bind:name="'experience[' + seq + '].start_dt'"
					       v-bind:id="'experience' + seq + '.start_dt'"/>
				</div>
				<div class="col-sm-1 text-center">~</div>
				<div class="col-sm-4">
					<input type="date" class="form-control" v-bind:name="'experience[' + seq + '].end_dt'"
					       v-bind:id="'experience' + seq + '.end_dt'"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">Detail</label>
				<div class="col-sm-9">
					<textarea class="form-control" v-bind:name="'experience[' + seq + '].description'"
					          v-bind:id="'experience' + seq + '.description'" rows="3"/>
				</div>
			</div>
		</div>
	</script>
	<script>
		Vue.component('experience-form', {
			template: '#experience-template',
			props: {
				seq: {type: Number, required: true}
			}
		})

		var experience_list = new Vue({
			el: '#test',
			data: {
				items: [1,2,3]
			}
		})
	</script>
</content>
</body>
</html>
