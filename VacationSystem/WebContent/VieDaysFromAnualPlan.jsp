<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@ page import = "com.axway.academy.iangelov.jdbc.GetDaysLeft" %>
      
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Days from anual plan</title>
</head>
<body link = "yellow" vlink = "green" alink = "red" background = "http://www.desktopwallpaperhd.net/wallpapers/0/8/background-web-privacy-consulting-portals-6200.jpg">
<div style = "color:white">
<%
String name = request.getParameter("userId"); 
GetDaysLeft gdl = GetDaysLeft.getInstance(); 
String output = gdl.getDays(name);
out.println(output);
%>
<br/><br/>
<input type="button" value="Back" onclick="javascript:history.go(-1)">
</div>
<br/>
</body>
</html>