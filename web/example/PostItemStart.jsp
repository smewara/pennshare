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

		<img src="accept.png" width="17" height = "17"/> <b>Your posting of item has been successful!</b>
		<br><br>
		<img src="add.png" width="17" height = "17"/> <a href="<s:url action="PostItemStart">
			 </s:url>">Post Another Item</a>
		<br>
		<s:if test="textbooklist != null">
			<hr>
			<s:iterator value="textbooklist">
				<table><tr>
					<td align="left"><font color="darkblue">
						<b>Title</b>: <s:property value="title" />
						<br>
						<b>Author</b>: <s:property value="author" />
						<br>
						<b>Publisher</b>: <s:property value="publisher" />
						<br>
						<b>ISBN</b>: <s:property value="isbn" />
					</font></td>
					<td><center>
						<img src="<s:property value="coverimage" />" />
					</center></td>
				</tr></table>
			</s:iterator>
		</s:if>
	
</s:if>
<s:else>
	<s:if test="message != null">
		<b>${message}</b>
	</s:if>
	<s:else>
		<br>
	</s:else>
	<s:form method="POST" action="PostItem" enctype="multipart/form-data">
		<b>Upload a file</b>
		<s:file name="theFile" label="Select File"/>
	 	<s:textfield label="Title" name="title"/>
		<s:submit/>
	</s:form>
</s:else>
</center>

</body>
</html>
