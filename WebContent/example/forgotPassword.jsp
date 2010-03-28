<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Web 2 Share</title>

</head>
<body>
<jsp:include page="headerLogin.jsp" />
<center><img src = "logo.jpg"></img></center>
<s:form action="forgotPassword">
    <s:textfield label="SEAS Email:" id = "emailinput" name="emailinput"/>
    
    <s:submit />
</s:form>
<jsp:include page="Footer.jsp" />
<div id="backToLoginPage"></div>
</body>
</html>