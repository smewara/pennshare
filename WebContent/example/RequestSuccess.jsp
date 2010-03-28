<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
    <title>Request Success</title>
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
--><div class="rightalign"><img src="door_out.png" width="17" height = "17"/>   <a href="<s:url action="Logout"/>" target="_parent"><font color="Maroon">Logout</font></a>  
<br></div>
<center>
<b>${message}</b>
<br><br>
<img src="search.png" width="17" height = "17"/> <a href="<s:url action="ShowSearchResult">
		 	<s:param name="textbookname">${search_keywords}</s:param>
		 	<s:param name="categoryid">${categoryid}</s:param>
		 </s:url>">Back to Search Result</a> 
<br><br>
<img src="search.png" width="17" height = "17"/> <a href="<s:url action="Search">
 		 </s:url>">Renew Search</a> 
<br><br>
</center>
    
</body>
</html>