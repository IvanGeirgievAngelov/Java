<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register users</title>
</head>
<body background = "http://www.desktopwallpaperhd.net/wallpapers/0/8/background-web-privacy-consulting-portals-6200.jpg">
<h1 align = "center"><strong><font color="white">Welcome guest please enter your info for create registration</font></strong></h1>
<div align="center" style ="margin-top: 50px;">
 <form action = "Registration" method = "post">
 <font color = "white">Username:<br/><input type = "text" name = "userId" size = "20px"><br/><br/>
 Password:<br/><input type = "password" name ="pass" size = "20px"><br/><br/>
 Email:   <br/><input type = "text" name = "email" size = "20px"><br/><br/>
<font color = "red">${errorMessage}</font> <br/>
 </font>
 <input type = "submit"  value = "submit">
 </form>
 <input type="button" value="Back" onclick="javascript:history.go(-1)">
 </div>
</body>
</html>