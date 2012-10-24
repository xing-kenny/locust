<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
	<script>
		$(document).ready(function() {
			$("#dept_name").focus();
			$("#inputForm").validate();
		});
	</script>
</head>

<body>
<h1>department management</h1>
	<c:if test="${not empty message}">
		<div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>${message}</div>
	</c:if>
	<c:if test="${empty message}">
		<div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>You can edit dept info.</div>
	</c:if>

<form id="inputForm" action="${ctx}/dept/${action}" method="post" class="form-horizontal">
	<input type="hidden" name="id" value="${dept.id}"/>
	<fieldset>
		<div class="control-group">
			<label for="dept_name" class="control-label">Dept shortly name:</label>
			<div class="controls">
				<input type="text" id="dept_name" name="name"  value="${dept.name}" class="input-large required" maxlength="10"/>
			</div>
		</div>	
		<div class="form-actions">
			<input id="submit_btn" class="btn btn-primary" type="submit" value="submit"/>&nbsp;	
			<a class="btn" href="${ctx}/dept">cancel</a>
		</div>
	</fieldset>
</form>
</body>
</html>
