<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<head>
<script>
function delById(url){

	if(!window.confirm("r u sure to delete the plan ?")){
		return;
	}

	window.location.href = url;
	window.submit();
}
</script>

</head>
<body>
<h1>plan management</h1>
<c:if test="${not empty message}">
	<div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>${message}</div>
</c:if>
<c:if test="${empty message}">
	<div id="message" class="alert alert-success">
		<button data-dismiss="alert" class="close">×</button>You can edit these plans.</div>
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
			<th>
				状态
			</th>
			<th>
				操作
			</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${plans}" var="plan">
		<tr>
			<td>${plan.project.name}</td>
			<td>${plan.name}		</td>
			<td>${plan.statusLabel} </td>
			<td>
				<c:if test="${plan.status eq '0'}">
   				<a href="${ctx}/plan/update/${plan.id}" >修改</a> 
   				<a href="${ctx}/plan/pub/${plan.id}/projectId/${project.id}" >发布</a>
   				<a href="#"  onclick="javascript:delById('${ctx}/plan/delete/${plan.id}/projectId/${project.id}');" >删除</a>
				</c:if>								
			</td>
		</tr>
		</c:forEach>
		</tbody>
	</table>

	<div class="form-actions">
		<a class="btn" href="${ctx}/plan/create/${project.id}">create</a>
		<a class="btn" href="${ctx}/plan/projects">cancel</a>
	</div>

</body>
