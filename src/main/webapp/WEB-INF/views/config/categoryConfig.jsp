<%--
  Created by IntelliJ IDEA.
  User: yhwang131
  Date: 2016-09-13
  Time: 오후 2:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>메뉴 설정</title>
</head>
<body ng-app="categoryApp">
	<h3 class="ui header">Category Settings</h3>
	<div class="ui internally celled grid" ng-controller="categoryCtrl">
		<form name="categoryForm" novalidate>
			<div class="ui modal small">
				<div class="header">Update Category Settings</div>
				<div class="content">
					<div class="ui form">
						<h4 class="ui dividing header" ng-bind="popupData.category_cd==null?'Add':'Config'"></h4>
						<div class="required field" ng-show="popupData.parent_category_cd>0">
							<label for="parent_category_cd">Parent Category</label>
							<select name="parent_category_cd" id="parent_category_cd" class="ui dropdown" ng-model="popupData.parent_category_cd" ng-required="true">
								<option ng-repeat="item in firstDepthCategory"
										ng-selected="popupData.parent_category_cd == item.category_cd"
										value="{{item.category_cd}}">{{item.category_name}}</option>
							</select>
						</div>
						<div class="required field" ng-class="{error:categoryForm.category_name.$invalid && categoryForm.category_name.$dirty}">
							<label>Category Name</label>
							<div class="ui input">
								<input type="text" name="category_name" id="category_name" ng-minlength="2" ng-maxlength="10" ng-model="popupData.category_name" ng-required="true"/>
							</div>
						</div>
						<div class="ui error message" ng-style="{'display':(categoryForm.category_name.$invalid && categoryForm.category_name.$dirty?'block':'')}">
							<p ng-show="categoryForm.category_name.$error.required && categoryForm.category_name.$dirty">카테고리 명은 반드시 입력해야 합니다.</p>
							<p ng-show="categoryForm.category_name.$error.minlength">2 bytes 이상 입력하세요.</p>
							<p ng-show="categoryForm.category_name.$error.maxlength">48 bytes(한글 16자) 이하로 입력하세요.</p>
						</div>
						<div class="required field">
							<label for="access_role">Permission</label>
							<select name="access_role" id="access_role" class="ui dropdown" ng-model="popupData.access_role" ng-required="true">
								<option ng-repeat="role in roleList"
										ng-selected="popupData.access_role == role.cd"
										value="{{role.cd}}">{{role.cd_name}}</option>
							</select>
						</div>
						<div class="ui warning message" ng-style="{'display':categoryForm.access_role.$invalid?'block':''}">
							<p>보기 권한은 반드시 선택하세요.</p>
						</div>
					</div>
				</div>
				<div class="actions">
					<div class="ui negative button">Cancel</div>
					<div class="ui positive right labeled icon submit button" ng-click="categoryFormSubmit(popupData,categoryForm.$valid)">
						Save
						<i class="checkmark icon"></i>
					</div>
				</div>
			</div>
		</form>
		<div class="row">
			<div class="four wide column">
				<div class="ui list">
					<div class="item" ng-repeat="firstDep in tree">
						<i class="red folder icon"></i>
						<div class="content">
							<div class="header" ng-bind="firstDep.category_name"></div>
							<div class="list">
								<div class="item" ng-repeat="secondDep in firstDep.children">
									<i class="orange file icon"></i>
									<div class="content">
										<div class="header" ng-bind="secondDep.category_name"></div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="six wide column">
				<table class="ui compact selectable celled definition table">
					<thead class="full-width">
						<tr>
							<th><div class="ui red ribbon label">1st</div></th>
							<th>Category</th>
							<th>Role</th>
							<th>Active</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="item in firstDepthCategory">
							<td class="center aligned">
								<div class="field">
									<div class="ui radio checkbox">
										<input type="radio" name="1stChk" tabindex="0" class="hidden" value="{{$index}}"/>
										<label></label>
									</div>
								</div>
							</td>
							<td ng-bind="item.category_name" ng-click="getSecondCategoryList(item.category_cd)"></td>
							<td ng-bind="item.access_role_name" ng-click="getSecondCategoryList(item.category_cd)"></td>
							<td class="collapsing">
								<div class="ui fitted toggle checkbox">
									<input type="checkbox" name="1stActive" ng-model="item.use_yn" ng-checked="item.use_yn" ng-click="setActiveOption(item)"/>
									<label></label>
								</div>
							</td>
						</tr>
					</tbody>
					<tfoot class="full-width">
						<tr>
							<th colspan="4">
								<div class="ui right floated compact positive icon button" ng-click="openConfigModal('1stChk',0)" title="설정">
									<i class="setting icon"></i>
								</div>
								<div class="ui right floated compact negative icon button" ng-click="delCategory('1stChk')" title="삭제">
									<i class="remove icon"></i>
								</div>
								<div class="ui right floated compact primary icon button" ng-click="openConfigModal('add',0)" title="추가">
									<i class="plus icon"></i>
								</div>
							</th>
						</tr>
					</tfoot>
				</table>
			</div>
			<div class="six wide column">
				<table class="ui compact celled definition table" ng-show="secondDepthCategory!=null">
					<thead class="full-width">
						<tr>
							<th><div class="ui orange ribbon label">2nd</div></th>
							<th>Category</th>
							<th>Role</th>
							<th>Active</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="item in secondDepthCategory">
							<td class="center aligned">
								<div class="field">
									<div class="ui radio checkbox">
										<input type="radio" name="2ndChk" tabindex="0" value="{{$index}}"/>
										<label></label>
									</div>
								</div>
							</td>
							<td ng-bind="item.category_name"></td>
							<td ng-bind="item.access_role_name"></td>
							<td class="collapsing">
								<div class="ui fitted toggle checkbox">
									<input type="checkbox" name="2ndActive" ng-model="item.use_yn" ng-checked="item.use_yn" ng-click="setActiveOption(item)"/>
									<label></label>
								</div>
							</td>
						</tr>
						<tr ng-if="secondDepthCategory.length==0">
							<td class="center aligned" colspan="4"><i class="warning sign icon"></i>하위 카테고리가 없습니다.</td>
						</tr>
					</tbody>
					<tfoot class="full-width">
						<tr>
							<th colspan="4">
								<div class="ui right floated compact positive icon button" ng-click="openConfigModal('2ndChk',parentCategoryCd)" title="설정">
									<i class="setting icon"></i>
								</div>
								<div class="ui right floated compact negative icon button" ng-click="delCategory('2ndChk')" title="삭제">
									<i class="remove icon"></i>
								</div>
								<div class="ui right floated compact primary icon button" ng-click="openConfigModal('add',parentCategoryCd)" title="추가">
									<i class="plus icon"></i>
								</div>
							</th>
						</tr>
					</tfoot>
				</table>
			</div>
		</div>
	</div>
	<content tag="script">
		<script>
			var app = angular.module('categoryApp', []);
			app.controller('categoryCtrl', ['$scope','$http','$q','$timeout',function($scope, $http, $q, $timeout){
				var createParamObj = function(depth, parentCategoryCd){
					this.depth = depth;
					this.parent_category_cd = parentCategoryCd;
				};

				var getTree = function(data){
					var promises = new Array();
					data.forEach(v => { promises.push(getList(2, v.category_cd)); });
					$q.all(promises).then(results => {
						angular.forEach(results, (item, index) => {
							angular.extend(data[index], {children:item.data});
						});
						angular.element('.ui.radio.checkbox').checkbox();
						$scope.tree = data;
					});
				};

				var getList = function(depth, parentCategoryCd){
					return $http({
						method:'get',
						url:'/api/categoryList',
						params:new createParamObj(depth, parentCategoryCd)
					});
				};

				var getFirstCategoryList = function() {
					getList(1, null).then(result => {
						$scope.firstDepthCategory = result.data;
						getTree(result.data);
					});
				};

				var openConfigLayer = function() {
					angular.element('.small.modal')
							.modal({
								onShow:function(){
									document.getElementById('parent_category_cd').value = $scope.popupData.parent_category_cd;
									document.getElementById('category_name').value = $scope.popupData.category_name;
								},
								onApprove:function(){
									return false;
								},
								onHidden:function(){
									document.getElementById('category_name').value = null;
									$scope.categoryForm.$setPristine();
								}
							})
							.modal('show');
				};

				$scope.getSecondCategoryList = function(parentCategoryCd){
					$scope.parentCategoryCd = parentCategoryCd;
					getList(2, parentCategoryCd).then(result => {
						$scope.secondDepthCategory = result.data;
					});
				};

				$scope.openConfigModal = function(chkBoxEleName,parentCategoryCd){
					var index = angular.element('input[name='+chkBoxEleName+']:checked').val();
					if(chkBoxEleName!='add'&& typeof index == 'undefined'){
						alert('수정할 카테고리를 선택하세요.');
						angular.element('input[name='+chkBoxEleName+']').first().focus();
						return;
					}
					//$scope.popupData = _.clone($scope.firstDepthCategory[index]);
					switch(chkBoxEleName){
						case '1stChk' :
							$scope.popupData = angular.copy($scope.firstDepthCategory[index]);
							break;
						case '2ndChk' :
							$scope.popupData = angular.copy($scope.secondDepthCategory[index]);
							break;
						case 'add' :
							$scope.popupData = new Object();
							angular.extend($scope.popupData, {parent_category_cd:parentCategoryCd});
							break;
					}
					if($scope.roleList==null){
						$http({
							method:'get',
							url:'/api/codeList',
							params:{up_cd:'ROLE'}
						}).then(function(result){
							$scope.roleList = result.data;
							openConfigLayer();
						});
					} else {
						openConfigLayer();
					}
				};

				$scope.categoryFormSubmit = function(obj,formValidate){
					if(formValidate){
						$http({
							method:'post',
							url:'/category',
							params:obj
						}).then(function(result) {
							if (result.data == 'success') {
								angular.element('.small.modal').modal('hide');
								getFirstCategoryList();
								if(obj.parent_category_cd > 0) $scope.getSecondCategoryList(obj.parent_category_cd);
							} else {
								alert("시스템 오류로 인해 처리되지 못하였습니다.");
							}
						}, function(error){
							alert(error.statusText);
						});
					}

				};

				$scope.setActiveOption = function(obj){
					$http({
						method:'post',
						url:'/category/setActiveOption',
						params:obj
					}).then(function(result) {
					}, function(error){
						alert(error.statusText);
					});
				};

				$scope.delCategory = function(chkBoxEleName){
					if(confirm('정말로 삭제하시겠습니까? 복구할 수 없습니다.')){
						var params;
						var index = angular.element('input[name='+chkBoxEleName+']:checked').val();
						var isFirst = angular.equals(chkBoxEleName, '1stChk');
						if(isFirst) {
							params = $scope.firstDepthCategory[index];
						} else {
							params = $scope.secondDepthCategory[index];
						}
						$http.delete('/category', {params:params}).then(function(result){
							getFirstCategoryList();
							if(!isFirst) $scope.getSecondCategoryList(result.data);
						}, function(error){
							alert(error.statusText);
						});
					}
				};

				getFirstCategoryList();
			}]);
		</script>
	</content>
</body>
</html>
