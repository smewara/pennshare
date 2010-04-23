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
<s:if test="successful">
	<br>
	<img src="accept.png" width="17" height = "17"/> <b>${message}</b>
	<br><br>
</s:if>
<s:else>
	<br>
	<s:if test="userlist != null">
		<s:iterator value="userlist" >
		<s:form action="ChangeProfile">
			<s:textfield label="Name" name="name" />
		 	<s:textfield label="Address" name="address" />
			<s:password label="New Password" name="password" />
			<s:submit/>
		</s:form>
	</s:iterator>
	</s:if>
	<s:else>
		<img src="delete.png" width="17" height = "17"/> <b>${message}</b>
		<br><br>
		<img src="profile.png" width="17" height = "17"/> <a href="<s:url action="EditProfile">
			</s:url>" target = "right">Edit Your Profile</a> 
	</s:else>
</s:else>

</body>
</html>