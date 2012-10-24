<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<head>
<script>
</script>

</head>
<body>
<h1>checkIn management</h1>
<c:if test="${not empty message}">
	<div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>${message}</div>
</c:if>
<c:if test="${empty message}">
	<div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>Select a course please.</div>
</c:if>

<table id="contentTable" class="table table-striped table-bordered table-condensed">
	<thead><tr>
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
	</tr></thead>
	<tbody>
	<c:forEach items="${courses}" var="course">
		<tr>
			<td><a href="${ctx}/checkIn/list/${course.id}" >${course.name}</a></td>
			<td><fmt:formatDate value="${course.startDate}" pattern="yyyy/MM/dd"/></td>
			<td><fmt:formatDate value="${course.endDate}" pattern="yyyy/MM/dd"/></td>
			<td>${course.numbers}</td>
		</tr>
	</c:forEach>
	</tbody>
</table>

</body>
