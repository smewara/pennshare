<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Web 2 Share</title>
</head>

<body>
<jsp:include page="Header.jsp" />
<b>${textbookname}</b>  <br/>
<br/>
<%try{ %>

<s:iterator id="entryOfItemsPage" value="item">
    ISBN: <s:property value="isbn" />
    <br/>
    Duration: <s:property value="duration"/>
    <br/>
    Price: <s:property value="price"/> <i> CIS$</i>
    <br/>
    Notes: <s:property value="notes"/>
    <br/>
    <br />
    <img src="<s:property value = "picture"/>" alt = "No Image Found" height="300" width = "250"/>
    <br/>
</s:iterator>
<%} catch(Exception e){System.out.println(e.fillInStackTrace());} %>
<br>

<jsp:include page="Footer.jsp" />
</body>
</html>