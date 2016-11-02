<%--
  Created by IntelliJ IDEA.
  User: yhwang131
  Date: 2016-10-24
  Time: 오후 5:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>파일 업로드</title>
</head>
<body>
	<form class="ui form warning" action="/post/fileUpload" method="post" enctype="multipart/form-data">
		<h4 class="ui dividing header">File Upload to Google Drive</h4>
		<div class="field">
			<label for="byteData">업로드 파일</label>
			<input type="file" id="byteData" name="byteData"/>
		</div>
		<div class="field">
			<p>업로드 한 파일명 : ${fileName}</p>
			<p>업로드 된 파일 경로 : http://${pageContext.request.serverName}:${pageContext.request.localPort}/googleapi/${fileId}</p>
		</div>
		<div class="field">
			<button type="submit" class="ui right floated compact positive labeled icon button" title="업로드">
				<i class="cloud upload icon"></i>File Upload
			</button>
		</div>
	</form>
</body>
</html>