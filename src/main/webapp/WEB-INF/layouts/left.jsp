<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<div id="sidebar">
	<div id="sidebar-wrapper"> <!-- Sidebar with logo and menu -->
		<!-- Sidebar Profile links -->
		<div id="profile-links">
			你好, <shiro:principal property="name"/>!
			
			<br />
			<a href="${ctx}/logout" title="注销">注销</a>
		</div>        
			
		<ul id="main-nav">  <!-- Accordion Menu -->
		<li> 
			<a href="#" class="nav-top-item ">签到管理</a>
			<ul>
				<li><a href='${ctx}/rungIn/courses'>签到管理</a></li>
			</ul>
		</li>
		<li> 
			<a href="#" class="nav-top-item ">报到管理</a>
			<ul>
				<li><a href='${ctx}/checkIn/courses'>报到管理</a></li>
			</ul>
		</li>
		<li> 
			<a href="#" class="nav-top-item ">报名管理</a>
			<ul>
				<li><a href='${ctx}/signup/courses'>报名管理</a></li>
			</ul>
		</li>
		<li> 
			<a href="#" class="nav-top-item ">计划管理</a>
			<ul>
				<li><a href='${ctx}/project/list?page=1'>项目管理</a></li>
				<li><a href='${ctx}/plan/projects'>月计划管理</a></li>
				<li><a href='${ctx}/course/plans'>课程管理</a></li>
			</ul>
		</li>
		<shiro:hasRole name="admin">
		<li>
			<a href="#" class="nav-top-item">系统设置</a>
			<ul>
				<li><a href='${ctx}/dept'>部门管理</a></li>
				<li><a href='${ctx}/admin/user/list'>人员管理</a></li>
			</ul>
		</li>      
		</shiro:hasRole>
		</ul>
		<!-- End #main-nav -->
	</div>
</div> <!-- End #sidebar -->


