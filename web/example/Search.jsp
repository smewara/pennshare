<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Web 2 Share</title>
    <style type= "text/css">
    .rightalign {
        text-align: right;
        font-size: 15;
                }
    </style>
    
</head>

<body>
<br>
<br>
<!-- <a href="<s:url action="ShowUserProfile">
		 	<s:param name="email">${email}</s:param>
		 </s:url>">${username}</a> -->
<div class="rightalign"><img src="door_out.png" width="17" height = "17"/>   <a href="<s:url action="Logout"/>" target="_parent"><font color="Maroon">Logout</font></a>  
<br></div>
<br>

<center>
<!--<a href="<s:url action="test">
		 </s:url>">test</a>

-->
<br>
<s:form action="ShowSearchResult">
	<b>${message}</b>
	<font color="blue"><s:textfield label="I'm looking for" name="textbookname"/></font>
	<s:submit value="Search"/>
</s:form>
		 
</center>
</body>
</html>