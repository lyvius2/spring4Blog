<%--
  Created by IntelliJ IDEA.
  User: yhwang131
  Date: 2017-07-25
  Time: 오후 5:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><div class="col-lg-12">
	<h4 class="heading">Lucene Index</h4>
	<table class="table">
		<thead>
		<th>Model Class</th>
		<th>Data Count</th>
		<th>Index Count</th>
		<th>Build</th>
		</thead>
		<tbody>
		<tr<c:if test="${data.dataLength != data.indexLength}"> class="danger"</c:if>>
			<td><c:out value="${data.object}"/></td>
			<td><c:out value="${data.dataLength}"/></td>
			<td><c:out value="${data.indexLength}"/></td>
			<td>
				<a href="/post/indexingBuild" class="btn btn-sm btn-primary">
					<i class="fa fa-tags" aria-hidden="true"> BUILD</i>
				</a>
			</td>
		</tr>
		</tbody>
	</table>
</div>
