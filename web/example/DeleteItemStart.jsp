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
		 </s:url>">${username}</a>  -->
<div class="rightalign"><img src="door_out.png" width="17" height = "17"/>   <a href="<s:url action="Logout"/>" target="_parent"><font color="Maroon">Logout</font></a>  
<br></div>
<br>

<center>
<s:if test="successful">

		<img src="accept.png" width="17" height = "17"/> <b>Your item has been deleted!</b>
		<br><br>
		<img src="delete.png" width="17" height = "17"/> <a href="<s:url action="DeleteItemStart">
			 </s:url>">Delete Another Item</a>
		<br>
	
</s:if>
<s:else>
	<s:if test="message != null">
		<b>${message}</b>
	</s:if>
	<s:else>
		<br>
	</s:else>
	<s:form action="DeleteItem">
	 	<s:select label="I would like to delete" name="itemid" list="owned_items" listKey="itemid" listValue="name" />
		<s:submit value="Delete"/>
	</s:form>
</s:else>
</center>

</body>
</html>