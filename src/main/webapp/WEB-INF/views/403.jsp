<%--
  Created by IntelliJ IDEA.
  User: yhwang131
  Date: 2016-09-02
  Time: 오후 6:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>403 Error</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/semantic/dist/semantic.min.css" />
	<style>
		.ui.header:first-child {
			margin-top: 7em;
		}
	</style>
</head>
<body ng-app="errPage">
	<div class="ui text container" ng-controller="errPageCtrl">
		<h2 class="ui dividing header"><i class="warning sign icon"></i><c:out value="${message}"/></h2>
		<p><span ng-bind="timerRanges"></span>초 후에 기본 화면으로 이동합니다...</p>
	</div>
	<script src="${pageContext.request.contextPath}/resources/scripts/vendor.js"></script>
	<script src="${pageContext.request.contextPath}/resources/semantic/dist/semantic.min.js"></script>
	<script>
		var app = angular.module('errPage', []);
		app.controller('errPageCtrl', ['$scope', function($scope){
			$scope.timerRanges = 3;
			$scope.$watch('timerRanges', (newVal, oldVal) => {
				if(newVal <= 0) { location.replace('/'); }
			});
			setInterval(() => {
				$scope.timerRanges--;
				$scope.$digest();
			}, 1000);
		}]);
	</script>
</body>
</html>
