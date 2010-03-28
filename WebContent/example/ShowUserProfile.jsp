<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Web 2 Share</title>
</head>

<body>

<br>
<!-- <a href="<s:url action="Search">
		 </s:url>">Go back to Search page</a>  -->
<center>
<br>
<s:iterator value="user">
    <img src="user.png" width="17" height = "17"/> <b><big><s:property value="name" /></big></b>
</s:iterator>
<br>
<hr>

<img src="portfolio.png" width="17" height = "17"/> <a href="<s:url action="ShowItemPortfolio">
			<s:param name="email">${email}</s:param>
		 </s:url>" target = "right">Your Item Portfolio</a> 
<br><br>
<img src="add.png" width="17" height = "17"/> <a href="<s:url action="PostItemStart">
</s:url>" target = "right">Post An Item</a>
<br><br>
<img src="profile.png" width="17" height = "17"/> <a href="<s:url action="EditProfile">
</s:url>" target = "right">Edit Your Profile</a>  
<br>
<hr>
<br>
<img src="search.png" width="17" height = "17"/> <a href="<s:url action="Search">
		 </s:url>" target = "right">Search Items</a>

</center>
</body>
</html>