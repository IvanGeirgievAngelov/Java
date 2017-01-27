 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <%@page import = "java.util.Date" %>
  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>

<body background ="http://www.desktopwallpaperhd.net/wallpapers/0/8/background-web-privacy-consulting-portals-6200.jpg" >
<%
 Date today = new Date();
 String name =(String) session.getAttribute("userId");
 //String name = request.getParameter("userId");
 %>
  <font color = "white">
Today's date is : <%= today %>
</font>

<div align = "center" style = "color:white"><h1>Welcome <%out.print(name); %></h1></div>

<form action = "RegisterNewVacation" method = "post">
<table border ="1px" bordercolor = "white" width ="100%">

<tr  align="center"><td colspan="3"><font color = "white" size="10">Enter starting date</font></td></tr>

<tr> 

<td>
 <font color = "white"> select month </font>
 <select name="month">
    <option value="Jan">January</option>
    <option value="Feb">February</option>
    <option value="Mar">March</option>
    <option value="Apr">April</option>
    <option value="May">May</option>
    <option value="Jun">June</option>
    <option value="Jul">July</option>
    <option value="Aug">August</option>
    <option value="Sep">September</option>
    <option value="Oct">October</option>
    <option value="Nov">November</option>
    <option value="Dec">December</option>
  </select>

</td>

<td>
<font color = "white">
select day
<select name="day">
    <c:forEach begin="1" end="31" var="day">
      <option><c:out value="${day}"/></option>
    </c:forEach>
  </select>

</font>

<td>
<font color = "white">
select year
  <select name="year">
    <c:forEach begin="2017" end="2025" var="year">
      <option><c:out value="${year}"/></option>
    </c:forEach>
  </select>
  
</font>
  </td>
</tr>

<tr>
<tr align="center"><td colspan="3"><font color = "white" size="10">Enter end date</font></td><tr>
<td>
 <font color = "white"> select month </font>
 <select name="smonth">
    <option value="Jan">January</option>
    <option value="Feb">February</option>
    <option value="Mar">March</option>
    <option value="Apr">April</option>
    <option value="May">May</option>
    <option value="Jun">June</option>
    <option value="Jul">July</option>
    <option value="Aug">August</option>
    <option value="Sep">September</option>
    <option value="Oct">October</option>
    <option value="Nov">November</option>
    <option value="Dec">December</option>
  </select>

</td>
<td>
<font color = "white">
select day
<select name="sday">
    <c:forEach begin="1" end="31" var="day">
      <option><c:out value="${day}"/></option>
    </c:forEach>
  </select>

</font>

<td>
<font color = "white">
select year
  <select name="syear">
    <c:forEach begin="2017" end="2025" var="year">
      <option><c:out value="${year}"/></option>
    </c:forEach>
  </select>
  
</font>
  </td>
</tr>

<tr align = "center"><td colspan = "3"><input type ="submit" value = "submit"></td></tr>
</table>
</form>
<font color = "white">${errorMessage }</font>
<br/><br/>
<input type="button" value="Back" onclick="javascript:history.go(-1)">
</body>
</html>