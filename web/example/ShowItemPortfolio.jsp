<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Item Portfolio</title>
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

<!-- <a href="<s:url action="Search">
		 </s:url>">Go back to Search page</a>  -->
<table cellspacing="15" width="100%"><tr>
<td width="50%"><center>
	<b>You posted these items:</b><br><br>
	<s:iterator id="entry" value="owned_items">
		<a href="<s:url action="downloadFile">
			<s:param name="itemId" value="%{itemid}"><s:property value="itemid"/></s:param>
			<s:param name="title"><s:property value="name"/></s:param>
		 </s:url>" target = "_blank"><s:property value="title"/></a>
		<br><br>
	</s:iterator>
	<img src="delete.png" width="17" height = "17"/> <a href="<s:url action="DeleteItemStart">
			 </s:url>" target = "right">Delete An Item</a> 
	<hr>
</center></td>
<td width="50%"><center>
</center></td>
</tr></table>
</center>
</body>
</html>