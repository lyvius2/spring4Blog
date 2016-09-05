<%--
  Created by IntelliJ IDEA.
  User: yhwang131
  Date: 2016-08-08
  Time: 오후 5:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>새로운 시작</title>
</head>
<body>
	<div class="row">
		<div class="column">
			<h1 class="ui header">Hello Spring 4 & Semantic UI World!</h1>
			<p>This is a basic fixed menu template using fixed size containers.</p>
			<p>A text container is used for the main container, which is useful for single column layouts</p>
			<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Dolorum in quod similique? Asperiores atque, beatae distinctio dolor eos id, iure libero nesciunt numquam officiis perspiciatis porro rem sit temporibus vitae.</p>
			<p>Railways Hi! Current Time is ${serverTime}.</p>
			<p>
				<i class="jp flag"></i>
				<i class="tw flag"></i>
				<i class="is flag"></i>
				<i class="fi flag"></i>
				<i class="ee flag"></i>
				<i class="hk flag"></i>
				<br/>
				<i class="kr flag"></i>
				<i class="se flag"></i>
				<i class="no flag"></i>
				<i class="fr flag"></i>
				<i class="nl flag"></i>
				<i class="gb flag"></i>
				<br/>
				<i class="gb sct flag"></i>
				<i class="dk flag"></i>
				<i class="fo flag"></i>
				<i class="de flag"></i>
				<i class="sg flag"></i>
			</p>
			<img class="wireframe" src="${pageContext.request.contextPath}/resources/images/faroeIslands.jpg">
		</div>
	</div>
	<content tag="script">
		<script>
			console.log('Hello! We are the world');
		</script>
	</content>
</body>
</html>
