<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<head>
<script>
function delById(url){

	if(!window.confirm("r u sure to delete the project ? ")){
		return;
	}
	window.location.href = url;
	window.submit();
}
</script>

</head>
<body>
<h1>project management</h1>
<c:if test="${not empty message}">
	<div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>${message}</div>
</c:if>
<c:if test="${empty message}">
	<div id="message" class="alert alert-success">
		<button data-dismiss="alert" class="close">×</button>You can edit these project.</div>
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
			计划人天
		</th>
		<th>
			执行人天
		</th>
		<th>
			状态
		</th>
		<th>
			操作
		</th>
	</tr></thead>
	<tbody>
	<c:forEach items="${projects.content}" var="project">
		<tr>
			<td><a href="${ctx}/project/update/${project.id}">${project.name}</a></td>
			<td>${project.description}</td>
			<td><fmt:formatDate value="${project.startDate}" pattern="yyyy/MM/dd"/></td>
			<td><fmt:formatDate value="${project.endDate}" pattern="yyyy/MM/dd"/></td>
			<td>${project.mandays}</td>
			<td>${project.actualMandays}</td>
			<td>${project.statusLabel} </td>
			<td>
			<c:if test="${project.status eq 0 }">
			<a href="#" onclick="javascript:delById('${ctx}/project/delete/${project.id}');">delete</a>
			</c:if>
			</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
<tags:pagination page="${projects}" paginationSize="5"/>
<div class="form-actions">
<a class="btn" href="${ctx}/project/create">create</a>
</div>

</body>
