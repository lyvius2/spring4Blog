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
<!DOCTYPE html>
<html>
<head>
	<title>Profile</title>
	<!-- Noto Sans KR-Hestia -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/resume/NotoSansKR-Hestia.css">
	<!-- custom css -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/resume/resume.css">
</head>
<body>
	<div class="resume-wrapper">

		<!-- header -->
		<header class="resume">
			<h1 class="heading-container">Résumé</h1>
			<h2 class="name-container"><c:out value="${resume.name}"/></h2>
			<h3 class="job-container"><c:out value="${resume.eng_name}"/></h3>
			<c:if test="${resume.image_url != null}">
			<div class="profile-picture">
				<img src="${pageContext.request.contextPath}/resources/images/profile/${resume.image_url}" alt="Profile">
			</div>
			</c:if>
			<div class="contact-container">
				<ul class="contact-list">
					<c:if test="${resume.email != ''}">
					<li>
						<a href="mailto:${resume.email}"><c:out value="${resume.email}"/></a>
						<span><i class="fa fa-envelope-o" aria-hidden="true"></i></span>
					</li>
					</c:if>
					<c:if test="${resume.web_addr != ''}">
					<li>
						<a href="#" target="_blank"><c:out value="${resume.web_addr}"/></a>
						<span><i class="fa fa-globe" aria-hidden="true"></i></span>
					</li>
					</c:if>
					<c:if test="${resume.git_addr != ''}">
					<li>
						<a href="${resume.git_addr}" target="_blank"><c:out value="${resume.git_addr}"/></a>
						<span><i class="fa fa-github" aria-hidden="true"></i></span>
					</li>
					</c:if>
					<c:if test="${resume.real_addr != ''}">
					<li>
						<c:out value="${resume.real_addr}"/>
						<span><i class="fa fa-map-marker" aria-hidden="true"></i></span>
					</li>
					</c:if>
				</ul>
			</div>
		</header><!-- header -->

		<!-- main -->
		<section id="main">
			<div class="resume-item-wrapper">

				<!-- experince -->
				<section class="resume-item experience">
					<div class="inner">
						<h2>experience</h2>
						<ul>
							<c:forEach var="experience" items="${resume.experience}" varStatus="vs">
							<li>
								<h3><c:out value="${experience.title}"/></h3>
								<h4 class="company"><c:out value="${experience.sub_title}"/></h4>
								<span class="date"><c:out value="${experience.str_start_dt}"/> - <c:out value="${experience.str_end_dt}"/></span>
								<p class="detail"><c:out value="${experience.description}"/></p>
							</li>
							</c:forEach>
							<%--
							<li>
								<h3>Senior Front-End Developer</h3>
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
							--%>
						</ul>
					</div>
				</section><!-- experince -->

				<!-- project -->
				<section class="resume-item project">
					<div class="inner">
						<h2>project</h2>
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
</body>
</html>
