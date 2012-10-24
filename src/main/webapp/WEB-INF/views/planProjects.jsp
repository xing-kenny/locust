<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<head>

</head>
<body>
<h1>plan management</h1>
<c:if test="${not empty message}">
	<div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>${message}</div>
</c:if>
<c:if test="${empty message}">
	<div id="message" class="alert alert-success">
		<button data-dismiss="alert" class="close">×</button>check a project please.
	</div>
</c:if>

<table id="contentTable" class="table table-striped table-bordered table-condensed">
	<thead>
	<tr>
		<th>
			项目名称
		</th>
		<th>
			项目描述
		</th>
		<th>
			开始时间
		</th>
		<th>
			结束时间
		</th>
		<th>
			人天
		</th>
	</tr></thead>
	<tbody>
	<c:forEach items="${projects}" var="project">
		<tr>
			<td><a href="${ctx}/plan/list/${project.id}">${project.name}</a></td>
			<td>${project.description}</td>
			<td><fmt:formatDate value="${project.startDate}" pattern="yyyy/MM/dd"/></td>
			<td><fmt:formatDate value="${project.endDate}" pattern="yyyy/MM/dd"/></td>
			<td>${project.mandays}</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
</body>
