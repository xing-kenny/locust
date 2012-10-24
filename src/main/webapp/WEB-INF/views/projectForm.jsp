<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
<script>
	$(document).ready(function() {
		//聚焦第一个输入框
		$("#project_name").focus();
		//为inputForm注册validate函数
		$("#inputForm").validate();
	});

	function pub(){

		inputForm.project_status.value = "1";
		inputForm.pub_btn.click();
	}

	function save(){

		inputForm.project_status.value = "0";
		inputForm.save_btn.click();
	}
	
	function done(){

		inputForm.project_status.value = "2";
		inputForm.done_btn.click();
	}
	
</script>
</head>

<body>
<h1>project management</h1>
<c:if test="${not empty message}">
	<div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>${message}</div>
</c:if>
<c:if test="${empty message}">
	<div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>You can edit project info.</div>
</c:if>

<form id="inputForm" action="${ctx}/project/${action}" method="post" class="form-horizontal">
	<input type="hidden" name="id" value="${project.id}"/>
	<fieldset>
		<div class="control-group">
			<label class="control-label">project name:</label>
			<div class="controls">
				<input type="text" id="project_name" name="name"  value="${project.name}" class="input-large required" maxlength="40"/>
			</div>
			<label class="control-label">description:</label>
			<div class="controls">
				<input type="text" id="project_description" name="description"  value="${project.description}" class="input-large required" maxlength="256"/>
			</div>
			<label class="control-label">startDate:</label>
			<div class="controls">
				<input type="date" id="startDate" name="startDate" value='<fmt:formatDate value="${project.startDate}" pattern="yyyy-MM-dd"/>' class="input-large required" />
			</div>
			<label class="control-label">endDate:</label>
			<div class="controls">
				<input type="date" id="endDate" name="endDate" value='<fmt:formatDate value="${project.endDate}" pattern="yyyy-MM-dd"/>' class="input-large required" />
			</div>
			<label class="control-label">mandays:</label>
			<div class="controls">
				<input type="text" id="project_mandays" name="mandays"  
					<c:if test="${empty project.id}">
					value="" 
					</c:if>
					<c:if test="${not empty project.id}">
					value="${project.mandays}"
					</c:if>
				class="input-large required" maxlength="4"/>
				

				<input type="hidden" id="project_status" name="status"  value="${project.status}" class="input-large required" maxlength="4"/>
			</div>
		</div>	
		<div class="form-actions">
			<c:if test="${project.status eq 0 }">
			<input id="save_btn" class="btn btn-primary" type="submit" value="save" onclick="save();"/>&nbsp;	
			<input id="pub_btn" class="btn btn-primary" type="submit" value="pub" onclick="pub();" />&nbsp;	
			</c:if>
			<c:if test="${project.status eq 1}">
			<input id="pub_btn" class="btn btn-primary" type="submit" value="done" onclick="done();" />&nbsp;	
			</c:if>
			<a class="btn" href="${ctx}/project/list">cancel</a>
		</div>
	</fieldset>
</form>
</body>
</html>
