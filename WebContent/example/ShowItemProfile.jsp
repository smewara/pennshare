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

<center><font color="darkblue">
<big><b>${itemname}</b></big>

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

<br><br>
<a href="javascript:self.close();">Close Window</a></li>

</font></center>

</body>
</html>