<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<head>
<script>
</script>

</head>
<body>
<h1>course management</h1>
<c:if test="${not empty message}">
	<div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>${message}</div>
</c:if>
<c:if test="${empty message}">
	<div id="message" class="alert alert-success">
		<button data-dismiss="alert" class="close">×</button>select a plan please.</div>
</c:if>

<table id="contentTable" class="table table-striped table-bordered table-condensed">
	<thead>
	<tr>
		<th>
			项目名称
		</th>
		<th>
			计划名称
		</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach items="${plans}" var="plan">
	<tr>
		<td>${plan.project.name}</td>
		<td><a href="${ctx}/course/list/${plan.id}" >${plan.name}</a></td>
	</tr>
	</c:forEach>
	</tbody>
</table>
</body>
