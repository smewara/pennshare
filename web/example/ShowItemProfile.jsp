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

<s:iterator var="book" value="bookname">

	<br>
    <b><big><s:property value="title"/></big></b><pre></pre><a href = "<s:url action="downloadFile">
								<s:param name="itemId" value="%{itemid}"/></s:url>">Download</a>
    <hr>
</s:iterator>
<br>

<br><br>
<a href="javascript:self.close();">Close Window</a></li>

</font></center>

</body>
</html>