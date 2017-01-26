 <!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login form</title>
</head>
<body background = "http://www.desktopwallpaperhd.net/wallpapers/0/8/background-web-privacy-consulting-portals-6200.jpg">
<div  style = "color: white" align = "center" style = "margin-top: 50px">
<h1 style = "color:white">Welcome to vacation system</h1>
<form action = "Login" method = "post">
Username:<br><input type = "text" name = "userName" size = "20px"></input><br/>
Password:<br><input type = "password" name = "userPass" size = "20px"></input><br/>
${errorMessage}<br/>
<input type = "submit" value = "login">
<input type="button" value="Back" onclick="javascript:history.go(-1)">
</form>
</div>
</body>
</html>