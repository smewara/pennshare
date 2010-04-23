<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Search</title>
</head>
<FRAMESET rows="15%, 75%, 10%" FRAMEBORDER = "0">
		<!-- <FRAME src= "/example/Header.jsp" name="header"> 
		<FRAME ="<s:url action="Header" namespace="/pages/smart_member" />"> -->
		<frame src="<s:url value="/example/Header.jsp"/>" name="header" noresize scrolling=no>
		<FRAMESET cols="20%, 80%"> 
        	<!--  <FRAME src="<s:url value="/example/ShowUserProfile.jsp"/>" name="left" > -->
        	<FRAME src="<s:url action="ShowUserProfile"><s:param name="email">${email}</s:param></s:url>" name ="left">
        	<FRAME src="<s:url action="Search"/>" name="right">
        </FRAMESET>
        <FRAME src = "<s:url value = "/example/Footer.jsp"/>" name="footer" scrolling=no>
</FRAMESET>


</html>