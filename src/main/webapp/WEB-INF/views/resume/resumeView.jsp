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
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
		<jsp:scriptlet>
			pageContext.setAttribute("wrap", "\n");
		</jsp:scriptlet>

		<!-- header -->
		<section class="resume">
			<h1 class="heading-container">Résumé</h1>
			<h2 class="name-container"><c:out value="${resume.name}"/></h2>
			<h3 class="job-container"><c:out value="${resume.eng_name}"/></h3>
			<div class="profile-picture">
				<img src="${pageContext.request.contextPath}/resources/images/profile/${resume.image_url != null ? resume.image_url:'rockman.gif'}" alt="Profile">
			</div>
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
		</section><!-- header -->

		<!-- main -->
		<section id="main" style="padding-bottom: 0px;">
			<div class="resume-item-wrapper">

				<!-- experince -->
				<c:if test="${fn:length(resume.experience) > 0}">
				<section class="resume-item experience">
					<div class="inner">
						<h2>경력</h2>
						<ul>
							<fmt:setLocale value="en_us" scope="session"/>
							<c:forEach var="experience" items="${resume.experience}" varStatus="vs">
							<li>
								<h3><c:out value="${experience.title}"/></h3>
								<h4 class="company"><c:out value="${experience.sub_title}"/></h4>
								<span class="date">
									<fmt:formatDate value="${experience.start_dt}" pattern="MMM yyyy"/>
									-
									<c:if test="${experience.str_end_dt != 'Present'}">
										<fmt:formatDate value="${experience.end_dt}" pattern="MMM yyyy"/>
									</c:if>
									<c:if test="${experience.end_dt == ''}">
										Present
									</c:if>
								</span>
								<p class="detail"><c:out value="${fn:replace(experience.description, wrap, '<br/>')}" escapeXml="false"/></p>
							</li>
							</c:forEach>
						</ul>
					</div>
				</section>
				</c:if><!-- experince -->

				<!-- project -->
				<c:if test="${fn:length(resume.project) > 0}">
				<section class="resume-item project">
					<div class="inner">
						<h2>프로젝트 이력</h2>
						<ul>
							<c:forEach var="project" items="${resume.project}" varStatus="vs">
							<li>
								<h3>
									<c:choose>
										<c:when test="${project.related_site != ''}">
											<a href="${project.related_site}" target="_blank"><c:out value="${project.title}"/></a>
										</c:when>
										<c:otherwise>
											<c:out value="${project.title}"/>
										</c:otherwise>
									</c:choose>
								</h3>
								<p class="sub"><c:out value="${project.sub_title}"/></p>
								<p class="date"><c:out value="${project.str_start_dt}"/> - <c:out value="${project.str_end_dt}"/></p>
								<p class="detail"><c:out value="${fn:replace(project.description, wrap, '<br/>')}" escapeXml="false"/></p>
								<p class="tags">
									<c:forEach var="tech" items="${fn:split(project.tech, '/')}">
										#<c:out value="${tech}"/>&nbsp;
									</c:forEach>
								</p>
							</li>
							</c:forEach>
						</ul>
					</div>
				</section>
				</c:if><!-- project -->

				<!-- skill -->
				<c:if test="${fn:length(resume.skill) > 0}">
				<section class="resume-item skill">
					<div class="inner">
						<h2>보유기술</h2>
						<ul>
							<c:forEach var="skill" items="${resume.skill}" varStatus="vs">
							<li>
								<h3><c:out value="${skill.title}"/></h3>
								<div class="skill-bar">
									<span class="bar" style="width: ${skill.level}% ;"></span>
								</div>
							</li>
							</c:forEach>
						</ul>
					</div>
				</section>
				</c:if><!-- skill -->

				<!-- education -->
				<c:if test="${fn:length(resume.education) > 0}">
				<section class="resume-item experience">
					<div class="inner">
						<h2>이수교육</h2>
						<ul>
							<c:forEach var="edu" items="${resume.education}" varStatus="vs">
								<li>
									<h3><c:out value="${edu.title}"/></h3>
									<h4 class="company"><c:out value="${edu.sub_title}"/></h4>
									<span class="date"><c:out value="${edu.str_start_dt}"/> - <c:out value="${edu.str_end_dt}"/></span>
								</li>
							</c:forEach>
						</ul>
					</div>
				</section>
				</c:if><!-- education -->

			</div>
		</section><!-- main -->

		<!-- footer -->
		<footer>
			© 2016 <a href="https://github.com/dhparkdh" target="-_blank">dhpark</a>. All rights reserved.
			Customized by <a href="https://github.com/lyvius2" target="_blank">walter.hwang
		</footer><!-- footer -->

	</div>
</body>
</html>
