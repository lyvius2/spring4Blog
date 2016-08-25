<%--
  Created by IntelliJ IDEA.
  User: yhwang131
  Date: 2016-08-22
  Time: 오후 3:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
	<title>회원 등록</title>
</head>
<body ng-app="memberApp">
	<form:form cssClass="ui form warning" action="/member/register" method="post" commandName="memberVO" autocomplete="off" ng-controller="memberCtrl">
		<h4 class="ui dividing header">Membership Register</h4>
		<div class="fields">
			<div class="six wide field">
				<label for="username">ID</label>
				<form:input type="text" path="username" maxlength="20" placeholder="ID" required="true"/>
				<form:errors path="username" cssClass="ui pointing red basic label"></form:errors>
			</div>
			<div class="ten wide field">
				<label for="kr_name">성명</label>
				<form:input type="text" path="kr_name" maxlength="30" placeholder="한글명" required="true"/>
				<form:errors path="kr_name" cssClass="ui pointing red basic label"></form:errors>
			</div>
		</div>
		<div class="field">
			<label>영문명</label>
			<div class="two fields">
				<div class="field">
					<form:input type="text" path="first_name" maxlength="30" placeholder="First Name"/>
				</div>
				<div class="field">
					<form:input type="text" path="last_name" maxlength="30" placeholder="Last Name"/>
				</div>
			</div>
		</div>
		<div class="fields">
			<div class="five wide field">
				<label for="email">E-Mail</label>
				<form:input type="email" path="email" placeholder="Mail Address"/>
			</div>
			<div class="five wide field">
				<label for="phone">휴대폰</label>
				<form:input type="tel" path="phone" placeholder="Cell Phone"/>
			</div>
			<div class="six wide field">
				<label for="nationality">국적</label>
				<div class="ui fluid search selection dropdown">
					<form:input type="hidden" path="nationality" id="nationality"/>
					<i class="dropdown icon"></i>
					<div class="default text">Select Country</div>
					<div class="menu">
						<div class="item" ng-repeat="item in countryList" data-value="{{item.cd}}">
							<i class="{{item.cd|lowercase}} flag"></i>{{item.cd_name}}
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="field">
			<label for="password">비밀번호</label>
			<form:password path="password" placeholder="비밀번호 입력" required="true"/>
			<form:errors path="password" element="div" cssClass="ui pointing red basic label"></form:errors>
		</div>
		<div class="field">
			<label for="passwordConfirm">비밀번호 확인</label>
			<input type="password" id="passwordConfirm" name="passwordConfirm" placeholder="비밀번호 확인" required="true"/>
		</div>
		<button type="submit" class="ui primary button" tabindex="0">회원등록</button>
	</form:form>
	<content tag="script">
	<script>
		$('div.dropdown').dropdown();

		var app = angular.module('memberApp', []);
		app.controller('memberCtrl', ['$scope','$http',function($scope,$http){
			$http({
				method:'get',
				url:'/api/codeList',
				params:{up_cd:'NAT'}
			}).then(function(result){
				$scope.countryList = result.data;
			});
		}]);
	</script>
	</content>
</body>
</html>
