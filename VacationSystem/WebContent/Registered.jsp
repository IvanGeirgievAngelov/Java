<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import = "java.util.Date" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registered users;</title>

<%Date today = new Date(); 
 String name = (String) session.getAttribute("userId");
 %>
 
</head>
 <body alink = "yellow" vlink="yellow" background = "http://www.desktopwallpaperhd.net/wallpapers/0/8/background-web-privacy-consulting-portals-6200.jpg">
 <div style = "color: white">
<p>Today's date is:<%= today %><br/></p>
 <p align = "center"><font size = "16">Welcome ${userId}</font></p>
<div>
 <a  href = "ViewRegisteredVacations.jsp?userId=${userId }">1)View registered Vacation<br/><br/></a>
 <a href = "ViewActiveVacations.jsp?userId=${userId}">2)View active Vacations<br/><br/></a>
 <a href = "RegisterNewVacation.jsp?userId=${userId}">3)Register new Vacation<br/><br/></a>
 
 <a href = "VieDaysFromAnualPlan.jsp?userId=${userId}">4)View how many days from annual plan left<br/><br/></a>
 <%-- <a href = "ViewDaysFromVacations.jsp?userId=${userId}">5)View how many days left from vacation<br/><br/></a> --%>
<h1 align = "center"> ${errorMessage} </h1>
<br/><br/>
<input type="button" value="Back" onclick="javascript:history.go(-1)">
</div>
</div>
</body>
</html>