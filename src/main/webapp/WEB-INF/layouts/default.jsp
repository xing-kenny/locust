<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>  
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<title>Locust<sitemesh:title/></title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />

<link type="image/x-icon" href="${ctx}/static/images/favicon.ico" rel="shortcut icon">
<link href="${ctx}/static/bootstrap/2.1.1/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/jquery-validation/1.10.0/validate.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/styles/default.css" type="text/css" rel="stylesheet" />
<script src="${ctx}/static/jquery/1.7.2/jquery.min.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery-validation/1.10.0/jquery.validate.min.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery-validation/1.10.0/messages_bs_zh.js" type="text/javascript"></script>
<script src="${ctx}/static/simpla.jquery.configuration.js"></script>

<sitemesh:head/>
</head>
<body>
<div class="container">
	<shiro:user>
		<%@ include file="/WEB-INF/layouts/header.jsp"%>
		<div class="row">
			<div id="leftbar" class="span3">
				<%@ include file="/WEB-INF/layouts/left.jsp"%>
			</div>
			<div id="main" class="span8">
				<sitemesh:body />
			</div>
		</div>
	</shiro:user>
	<shiro:guest>
		<div class="row">
			<div id="left" class="span3">
			</div>
			<div id="main" class="span8">
				<sitemesh:body />
			</div>
		</div>
	</shiro:guest>
	<%@ include file="/WEB-INF/layouts/footer.jsp"%>
</div>
<script src="${ctx}/static/bootstrap/2.1.1/js/bootstrap.min.js" type="text/javascript"></script>
</body>

</html>