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
<jsp:include page="Header.jsp" />
<br>
<br>
<a href="<s:url action="ShowUserProfile">
		 	<s:param name="email">${email}</s:param>
		 </s:url>">${username}</a>
<div class="rightalign"><img src="jsp/door_out.png" width="17" height = "17"/>   <a href="<s:url action="Logout"/>"><font color="Maroon">Logout</font></a>  
<br></div>
<br>

<center>
<s:form action="ShowSearchResult2">
    <s:textfield label="Search By Textbook Name" name="textbookname"/>
    <s:submit value="Search"/>
</s:form>
</center>
<jsp:include page="Footer.jsp" />
</body>
</html>