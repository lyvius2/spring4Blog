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
		.label-primary,.label-success {
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
			<h2 class="name-container"><form:input type="text" cssClass="form-control" path="name" placeholder="korean name"/></h2>
			<h3 class="job-container"><form:input type="text" cssClass="form-control" path="eng_name" placeholder="english name"/></h3>
			<div class="profile-picture">
				<img src="${pageContext.request.contextPath}/resources/images/profile.png" alt="Profile">
			</div>
			<div class="contact-container">
				<ul class="contact-list">
					<li>
						<div class="input-group input-group-sm">
							<form:input type="email" cssClass="form-control" path="email" placeholder="email address"/>
						</div>
						<span><i class="fa fa-envelope-o" aria-hidden="true"></i></span>
					</li>
					<li>
						<div class="input-group input-group-sm">
							<form:input type="text" cssClass="form-control" path="web_addr" placeholder="homepage address"/>
						</div>
						<span><i class="fa fa-globe" aria-hidden="true"></i></span>
					</li>
					<li>
						<div class="input-group input-group-sm">
							<form:input type="text" cssClass="form-control" path="git_addr" placeholder="github address"/>
						</div>
						<span><i class="fa fa-github" aria-hidden="true"></i></span>
					</li>
					<li>
						<div class="input-group input-group-sm">
							<form:input type="text" cssClass="form-control" path="real_addr" placeholder="location address"/>
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
							<c:forEach items="${resumeVO.experience}" varStatus="vs">
							<div class="form-group">
								<div class="col-sm-3"><h5>[${vs.index + 1}]</h5></div>
								<div class="col-sm-9 text-right">
									<button type="button" class="btn btn-sm btn-danger">
										<i class="fa fa-times" aria-hidden="true"> 삭제</i>
									</button>
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
							</c:forEach>
						</div>


						<%--
						<ul>
							<!-- list example
							<li>
							  <h3>Your Role</h3>
							  <h4 class="company">Your Company Name & Location</h4>
							  <span class="date">Working Period</span>
							  <p class="detail">
								Details what you did at this company
							  </p>
							</li>
							-->

							<li>
								<h3></h3>
								<h4 class="company"><form:input type="text" cssClass="form-control" path="experience[0].sub_title"/></h4>
								<span class="date">Sept 2015 - present</span>
								<p class="detail">
									<form:textarea path="experience[0].description" cssClass="form-control" rows="3"/>
								</p>
							</li>
							<li>
								<h3>Senior Front-End Developer<button type="submit">TEST SUBMIT</button></h3>
								<h4 class="company">Awesome Startup Corp. in Gangnam</h4>
								<span class="date">Feb 2015 - Jan 2016</span>
								<p class="detail">
									Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.
								</p>
							</li>
							<li>
								<h3>Junior Front-End Developer</h3>
								<h4 class="company">Great Web Agency in Hongdae</h4>
								<span class="date">Mar 2010 - Jan 2015</span>
								<p class="detail">
									Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.
								</p>
							</li>
						</ul>
						--%>
					</div>
				</section><!-- experience -->

				<!-- project -->
				<section class="resume-item project">
					<div class="inner">
						<h2>project</h2>
						<div class="form-horizontal">
							<c:forEach var="project" items="${resumeVO.project}" varStatus="vs">
								<div class="form-group">
									<div class="col-sm-3"><h5>[${vs.index + 1}]</h5></div>
									<div class="col-sm-9 text-right">
										<button type="button" class="btn btn-sm btn-danger">
											<i class="fa fa-times" aria-hidden="true"> 삭제</i>
										</button>
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
											<i class="fa fa-plus-circle" aria-hidden="true"></i>
										</span>
									</div>
								</div>
							</c:forEach>
						</div>

						<%--
						<ul>
							<!-- list example
							<li>
							  <h3><a href="#" target="_blank">Your Project Name</a></h3>
							  <p class="detail">
								Details what the project is
							  </p>
							  <p class="tags">#hash #tags</p>
							</li>
							-->
							<li>
								<h3><a href="https://github.com/dhparkdh/resume-for-web-developer" target="_blank">HTML5 Résumé template for web developer</a></h3>
								<p class="detail">
									Online Résumé(CV) template for web developer using HTML5 and CSS3.
								</p>
								<p class="tags">#HTML5 #CSS3 #Noto</p>
							</li>
							<li>
								<h3><a href="#" target="_blank">Project Name</a></h3>
								<p class="detail">
									Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.
								</p>
								<p class="tags">#hash #tags</p>
							</li>
							<li>
								<h3><a href="#" target="_blank">Project Name</a></h3>
								<p class="detail">
									Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.
								</p>
								<p class="tags">#hash #tags</p>
							</li>
						</ul>
						--%>
					</div>
				</section><!-- project -->

				<!-- skill -->
				<section class="resume-item skill">
					<div class="inner">
						<h2>Skill</h2>
						<ul>
							<!-- list example
							<li>
							  <h3>Your Skill Name</h3>
							  <div class="skill-bar">
								<span class="bar" style="width: Percentage% ;"></span>
							  </div>
							</li>
							-->
							<li>
								<h3>Master</h3>
								<div class="skill-bar">
									<span class="bar" style="width: 100% ;"></span>
								</div>
							</li>
							<li>
								<h3>Professional</h3>
								<div class="skill-bar">
									<span class="bar" style="width: 80% ;"></span>
								</div>
							</li>
							<li>
								<h3>Senior</h3>
								<div class="skill-bar">
									<span class="bar" style="width: 60% ;"></span>
								</div>
							</li>
							<li>
								<h3>Junior</h3>
								<div class="skill-bar">
									<span class="bar" style="width: 40% ;"></span>
								</div>
							</li>
							<li>
								<h3>Rookie</h3>
								<div class="skill-bar">
									<span class="bar" style="width: 20% ;"></span>
								</div>
							</li>
						</ul>
					</div>
				</section><!-- skill -->

			</div>
		</section><!-- main -->

		<!-- footer -->
		<footer>
			© 2016 <a href="https://github.com/dhparkdh" target="-_blank">dhpark</a>. All rights reserved.
		</footer><!-- footer -->

	</div>
</form:form>
</body>
</html>
