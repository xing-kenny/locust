<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
<script>
	$(document).ready(function() {
		//聚焦第一个输入框
		$("#course_name").focus();
		//为inputForm注册validate函数
		$("#inputForm").validate();
	});

	
</script>
</head>

<body>
<h1>course management</h1>
<form id="inputForm" action="${ctx}/course/${action}" method="post" class="form-horizontal">
	<input type="hidden" name="id" value="${course.id}"/>
	<input type="hidden" name="planId" value="${course.plan.id}"/>
	<fieldset>
		<div class="control-group">
			<label class="control-label">plan name:</label>
			<div class="controls">
				<input type="text" id="plan_name" name="plan_name"  value="${course.plan.name}" class="input-large required" readonly />
			</div>
			<label class="control-label">course name:</label>
			<div class="controls">
				<input type="text" id="course_name" name="name"  value="${course.name}" class="input-large required" maxlength="256"/>
			</div>
			<label class="control-label">startDate:</label>
			<div class="controls">
				<input type="date" id="startDate" name="startDate" value='<fmt:formatDate value="${course.startDate}" pattern="yyyy-MM-dd"/>' class="input-large required" />
			</div>
			<label class="control-label">endDate:</label>
			<div class="controls">
				<input type="date" id="endDate" name="endDate" value='<fmt:formatDate value="${course.endDate}" pattern="yyyy-MM-dd"/>' class="input-large required" />
			</div>
			<label class="control-label">numbers:</label>
			<div class="controls">
				<input type="text" id="course_numbers" name="numbers"  value="${course.numbers}" class="input-large required" maxlength="4"/>
			</div>
		</div>	
		<div class="form-actions">
			<input id="save_btn" class="btn btn-primary" type="submit" value="save" />	
			<a class="btn" href="${ctx}/course/list/${course.plan.id}">cancel</a>
		</div>
	</fieldset>
</form>
</body>
</html>
