<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
    <title>Penn Notes</title>
    <style type= "text/css">
    .rightalign {
        text-align: right;
        font-size: 15;
                }
    </style>
</head>

<body>
<br>
<!--  <a href="<s:url action="ShowUserProfile">
		 	<s:param name="email">${email}</s:param>
		 </s:url>">${username}</a> -->
<div class="rightalign"><img src="door_out.png" width="17" height = "17"/>   <a href="<s:url action="Logout"/>" target="_parent"><font color="Maroon">Logout</font></a>  
<br></div>
<center>

<s:if test="num!= 0">
	<b><big>Results Found</big></b>
</s:if>
<s:else>
	<b><big>No Results Found</big></b>
</s:else>

<br><img src="search.png" width="17" height = "17"/> <a href="<s:url action="Search">
		 </s:url>">Renew Search</a> 
<hr>


<s:iterator var="book" value="bookname">

	<br>
    <b><big><s:property value="book"/></big></b>
    <br>
    <br>
    <hr>
</s:iterator>
<br>

</center>
    
</body>
</html>