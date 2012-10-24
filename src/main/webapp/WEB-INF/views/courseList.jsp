<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<head>
<script>
function delById(url){

	if(!window.confirm("r u sure to delete the course ?")){
		return;
	}

	window.location.href = url;
	window.submit();
}
</script>

</head>
<body>
<h1>course management</h1>
<c:if test="${not empty message}">
	<div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>${message}</div>
</c:if>
<c:if test="${empty message}">
	<div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>You can edit these dept info.</div>
</c:if>

<table id="contentTable" class="table table-striped table-bordered table-condensed">
	<thead><tr>
		<th>
			计划名称
		</th>
		<th>
			课程名称
		</th>
		<th>
			开始时间
		</th>
		<th>
			结束时间
		</th>
		<th>
			学员数量
		</th>
		<th>
			操作
		</th>
	</tr></thead>
	<tbody>
	<c:forEach items="${courses}" var="course">
		<tr>
			<td>${course.plan.name}</td>
			<td>${course.name}</td>
			<td><fmt:formatDate value="${course.startDate}" pattern="yyyy/MM/dd"/></td>
			<td><fmt:formatDate value="${course.endDate}" pattern="yyyy/MM/dd"/></td>
			<td>${course.numbers}</td>
			<td>
   				<a href="${ctx}/course/update/${course.id}" >edit</a> 
				<a href="#" onclick="javascript:delById('${ctx}/course/delete/${course.id}/planId/${plan.id}');" >delete</a>
			</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
<div class="form-actions">
	<a class="button" href="${ctx}/course/create/${plan.id}">create</a>
	<a class="button" href="${ctx}/course/plans">cancel</a>
</div>

</body>
