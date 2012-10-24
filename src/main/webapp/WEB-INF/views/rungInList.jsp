<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<head>
<script>

</script>

</head>
<body>
<h1>rungin management</h1>
<c:if test="${not empty message}">
	<div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>${message}</div>
</c:if>
<c:if test="${empty message}">
	<div id="message" class="alert alert-success">
		<button data-dismiss="alert" class="close">×</button>record for somebody's rungIn.</div>
</c:if>

<table id="contentTable" class="table table-striped table-bordered table-condensed">
	<thead>
	<tr>
		<th>
			姓名
		</th>
		<c:forEach items="${labels}" var="label">
			<th>
				<fmt:formatDate value="${label.rungInDay}" pattern="yyyy-MM-dd"/> | 
				<c:if test="${label.forenoon ne 1}">
					下午
				</c:if>				
				<c:if test="${label.forenoon eq 1}">
					上午
				</c:if>				
			</th>
		</c:forEach>
	</tr>
	</thead>
	<tbody>
	<c:forEach items="${blocks}" var="block">
		<tr>
			<td>
			${block.user.name}
			</td>
			<c:forEach items="${block.rungIns}" var="rungIn">
				<td>
					<c:if test="${rungIn.id eq 0}">
						<a  href="${ctx}/rungIn/rungIn/${rungIn.courseId}/rungInDay/<fmt:formatDate value="${rungIn.runginDay}" pattern="yyyy-MM-dd"/>/forenoon/${rungIn.forenoon}/runginUserId/${rungIn.runginUserId}" >签到</a>
					</c:if>				
					<c:if test="${rungIn.id ne 0}">
						已签到
					</c:if>				
				</td>
			</c:forEach>
		</tr>					
	</c:forEach>
	</tbody>
</table>

<div class="form-actions">
<a class="btn" href="${ctx}/rungIn/courses">cancel</a>
</div>
</body>
