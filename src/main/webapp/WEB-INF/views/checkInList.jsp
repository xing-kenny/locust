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
	<div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>&nbsp;</div>
</c:if>

<table id="contentTable" class="table table-striped table-bordered table-condensed">
	<thead><tr>
		<th>
			学员名称
		</th>
		<th>
			报名时间
		</th>
		<th>
			报到时间
		</th>
		<th>
			操作
		</th>
	</tr></thead>
	<tbody>
	<c:forEach items="${signUps}" var="signUp">
		<tr>
			<td>${signUp.signer.name}</td>
			<td><fmt:formatDate value="${signUp.signupDay}" pattern="yyyy-MM-dd"/></td>
			<td><fmt:formatDate value="${signUp.checkin.checkinDay}" pattern="yyyy-MM-dd"/></td>
			<td>
			<c:if test="${signUp.checkin eq null}">
			<a href="${ctx}/checkIn/checkIn/${signUp.course.id}/signUpId/${signUp.id}" >报到</a>
			</c:if>
			<c:if test="${signUp.checkin ne null}">
			已报到
			</c:if>
			</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
<div class="form-actions">
	<a class="btn" href="${ctx}/checkIn/courses">cancel</a>
</div>
</body>

