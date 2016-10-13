<%--
  Created by IntelliJ IDEA.
  User: yhwang131
  Date: 2016-10-12
  Time: 오후 2:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
	<title>포스트 - <c:out value="${post.title}"/></title>
</head>
<body>
	<div class="ui">
		<div class="column">
			<div class="ui raised segment">
				<a class="ui red ribbon label">
					<c:if test="${post.trip_country!=null}"><i class="${fn:toLowerCase(post.trip_country)} flag"></i></c:if>
					<c:out value="${post.category_name}"/>
				</a>
				<span class="georgia">${regDt}</span>
				<h4 class="header">
					<c:out value="${post.title}"/>
				</h4>
				<div class="ui divider"></div>
				<div class="ui justified container">
					<jsp:scriptlet>
						pageContext.setAttribute("wrap", "\n");
					</jsp:scriptlet>
					<c:out value="${fn:replace(post.content, wrap, '<br/>')}" escapeXml="false"/>
				</div>
				<div class="ui divider"></div>
				<%-- 댓글란은 구현 시까지 주석 처리. (MongoDB 사용 예정) --%>
				<!--
				<div class="ui comments">
					<%--
					<h4 class="ui dividing header">Comments</h4>
					--%>
					<div class="comment">
						<%--
						<a class="avatar">
							<img src="/images/avatar/small/matt.jpg">
						</a>
						--%>
						<div class="content">
							<a class="author">Elliot Fu</a>
							<div class="metadata">
								<span class="date">Yesterday at 12:30AM</span>
							</div>
							<div class="text">
								<p>This has been very useful for my research. Thanks as well!</p>
							</div>
							<div class="actions">
								<a class="reply">Reply</a>
							</div>
						</div>
						<div class="comments">
							<div class="comment">
								<%--
								<a class="avatar">
									<img src="/images/avatar/small/matt.jpg">
								</a>
								--%>
								<div class="content">
									<a class="author">Jenny Hess</a>
									<div class="metadata">
										<span class="date">Just now</span>
									</div>
									<div class="text">
										Elliot you are always so right :)
									</div>
									<div class="actions">
										<a class="reply">Reply</a>
									</div>
								</div>
							</div>
							<div class="comment">
								<div class="content ui form">
									<div class="field">
										<label>Reply to Jenny Hess</label>
										<textarea rows="2"></textarea>
									</div>
									<div class="ui scrolling dropdown">
										<input type="hidden" name="gender">
										<div class="default text">Select Profile</div>
										<i class="dropdown icon"></i>
										<div class="menu">
											<div class="item" data-value="fb">Facebook</div>
											<div class="item" data-value="gp">Google Plus</div>
											<div class="item" data-value="nv">Naver</div>
											<div class="item" data-value="ip">Your IP</div>
										</div>
									</div>
									<div class="mini ui blue labeled submit icon button">
										<i class="icon edit"></i> Add Reply
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="content ui form">
						<div class="field">
							<label>Comment</label>
							<textarea rows="4"></textarea>
						</div>
						<div class="ui scrolling dropdown">
							<input type="hidden" name="gender">
							<div class="default text">Select Profile</div>
							<i class="dropdown icon"></i>
							<div class="menu">
								<div class="item" data-value="fb">Facebook</div>
								<div class="item" data-value="gp">Google Plus</div>
								<div class="item" data-value="nv">Naver</div>
								<div class="item" data-value="ip">Your IP</div>
							</div>
						</div>
						<div class="mini ui blue labeled submit icon button">
							<i class="icon edit"></i> Add Comment
						</div>
					</div>
				</div>
				-->
			</div>
		</div>
	</div>
	<content tag="script">
	<script>
		$('.ui.dropdown').dropdown();
	</script>
	</content>
</body>
</html>
