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
		<a href="<s:url action="ShowItemProfile">
			<s:param name="itemid"><s:property value="itemid"/></s:param>
			<s:param name="itemname"><s:property value="name"/></s:param>
		 </s:url>" target = "_blank"><s:property value="name"/></a>
		<br><br>
	</s:iterator>
	<img src="delete.png" width="17" height = "17"/> <a href="<s:url action="DeleteItemStart">
			 </s:url>" target = "right">Delete An Item</a> 
	<hr>
	<b>Others requested these of your items:</b>
	<br><br>
	<s:iterator value="items_requested">
		<a href="<s:url action="ShowItemProfile">
			<s:param name="itemid"><s:property value="itemid"/></s:param>
			<s:param name="itemname"><s:property value="name"/></s:param>
		 </s:url>" target = "_blank"><s:property value="name"/></a>
		<b>to</b> <s:property value="requester_name"/>
		<s:set name="status" value="<s:property value=\"status\" />" />
		<s:if test="status != 'Confirmed' && status != 'Obtained By Others'">
			<br>
			<img src="accept.png" width="17" height = "17"/> <a href="<s:url action="ConfirmRequest">
					<s:param name="itemid"><s:property value="itemid"/></s:param>
					<s:param name="requesteremail"><s:property value="requester_email"/></s:param>
				 </s:url>" target = "right">Confirm!</a>
		</s:if>   
		<br><br>
	</s:iterator>
</center></td>
<td width="50%"><center>
	<b>You requested these items from others:</b>
	<br><br>
	<s:iterator value="requesting_items">
		<a href="<s:url action="ShowItemProfile">
			<s:param name="itemid"><s:property value="itemid"/></s:param>
			<s:param name="itemname"><s:property value="name"/></s:param>
		 </s:url>" target = "_blank"><s:property value="name"/></a>
		<b>from</b> <s:property value="ownername"/> <b><font color="red"><s:property value="status"/></font></b>
		<br>
		<img src="contact.png" width="17" height = "17"/> <a href="mailto:<s:property value="owneremail"/>">Contact <s:property value="ownername"/></a>
		<br><br>
	</s:iterator>
</center></td>
</tr></table>
<!--
Requested by:
<s:iterator id="entry" value="requesters">
	<s:property value="name"/>
	<br>
	Send Email to <a href=""><s:property value="email"/></a> to fix a pick-up time
</s:iterator>
<br>
<br>
--></center>
</body>
</html>