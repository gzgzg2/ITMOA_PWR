<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.lec.beans.*" %>
<% MbDTO [] dto = (MbDTO[])session.getAttribute("login"); %>

<% if( dto != null) { session.removeAttribute("login"); }%>

 	<script>
 		location.href="logoutIndex.do";
 	</script>