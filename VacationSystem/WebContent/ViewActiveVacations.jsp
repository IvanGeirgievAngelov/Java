<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "com.axway.academy.iangelov.jdbc.ActiveVacation" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>view Active vacations</title>
</head>
<body background = "http://www.desktopwallpaperhd.net/wallpapers/0/8/background-web-privacy-consulting-portals-6200.jpg">
<div style = "color:white">
<%
String name =(String) session.getAttribute("userId");
ActiveVacation vav = ActiveVacation.getInstance();
String output = vav.getActiveVacations(name);
if(output != ""){
out.println(output);
}else 
out.println("there is no active vacations");
%>
<br/><br/>
<input type="button" value="Back" onclick="javascript:history.go(-1)">
</div>
</body>
</html>