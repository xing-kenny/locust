<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
<script>
	$(document).ready(function() {
		//聚焦第一个输入框
		$("#plan_name").focus();
		//为inputForm注册validate函数
		$("#inputForm").validate();
	});

	
</script>
</head>

<body>
<h1>plan management</h1>
<c:if test="${not empty message}">
	<div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>${message}</div>
</c:if>
<c:if test="${empty message}">
	<div id="message" class="alert alert-success">
		<button data-dismiss="alert" class="close">×</button>You can plans info.</div>
</c:if>

<form id="inputForm" action="${ctx}/plan/${action}" method="post" class="form-horizontal">
	<input type="hidden" name="id" value="${plan.id}"/>
	<input type="hidden" name="projectId" value="${plan.project.id}"/>
	<fieldset>
		<div class="control-group">
			<label class="control-label">project name:</label> 
			<div class="controls">
				<input type="text" id="project_name" name="project_name"  value="${plan.project.name}" class="input-large required" readonly maxlength="40"/>
			</div>
			<label class="control-label">plan name:</label>
			<div class="controls">
				<input type="text" id="plan_name" name="name"  value="${plan.name}" class="input-large required" maxlength="40"/>
			</div>

		</div>	
		<div class="form-actions">
			<input id="submit_btn" class="btn btn-primary" type="submit" value="submit"/>&nbsp;	
			<input id="cancel_btn" class="btn" type="button" value="cancel" onclick="history.back()"/>
		</div>
	</fieldset>
</form>
</body>
</html>
