<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
    <title>Confirm Request</title>
    <style type= "text/css">
    .rightalign {
        text-align: right;
        font-size: 15;
                }
    </style>
</head>

<body>
<br>
<!--<a href="<s:url action="ShowUserProfile">
		 	<s:param name="email">${email}</s:param>
		 </s:url>">${username}</a>
--><div class="rightalign"><img src="door_out.png" width="17" height = "17"/>   <a href="<s:url action="Logout"/>"><font color="Maroon">Logout</font></a>  
<br></div>
<center>
<img src="accept.png" width="17" height = "17"/> <b>${message}</b>
<br><br>
<img src="portfolio.png" width="17" height = "17"/> <a href="<s:url action="ShowItemPortfolio">
			<s:param name="email">${email}</s:param>
		 </s:url>" target = "right">Back to Your Item Portfolio</a> 
<br><br>
</center>
    
</body>
</html>