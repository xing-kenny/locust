<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<head>
<script>
function delById(url){

	if(!window.confirm("r u sure to delete the dept ?")){
		return;
	}

	window.location.href = url;
	window.submit();
}
</script>
</head>
<body>
<h1>department management</h1>
	<c:if test="${not empty message}">
		<div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>${message}</div>
	</c:if>
	<c:if test="${empty message}">
		<div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>You can edit these dept info.</div>
	</c:if>

	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>name</th><th>operation</th></tr></thead>
		<tbody>
		<c:forEach items="${depts}" var="dept">
			<tr>
				<td><a href="${ctx}/dept/update/${dept.id}">${dept.name}</a></td>
				<td><a href="#" onclick="javascript:delById('${ctx}/dept/delete/${dept.id}');">delete</a></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="form-actions">
	<a class="btn" href="${ctx}/dept/create">create</a>
	</div>
</body>
