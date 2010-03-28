<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
	<title>Web 2 Share</title>
	<link rel="stylesheet" href="style-login.css" type="text/css" />

<script type="text/javascript">



function checkForm(form){

    var aaa = form.passwordinput.value;

    
    if((form.passwordinput.value == "") ||
            (form.passwordinput.value == " ")){

    	
    	document.getElementById('passwordError').innerHTML = "<img src = \"delete.png\" align = \"middle\" alt = \"ok\" width=\"12\" height=\"12\"/> ";
        form.passwordinput.focus();
        return false;
    }

  
    

    else {
        
        
        return true;
        }

}

  
</script>

</head>

<body>
<jsp:include page="headerLogin.jsp" />

<center><img src = "logo.jpg"></img></center>
<br>

<center><s:form action="LoginCIS" onsubmit="return checkForm(this)">

	<span id="changeFont">All you need is your</span> <b id="address"> seas.upenn.edu </b> <span id="changeFont">email address</span>
	<br/>
    <s:textfield label="SEAS Email Address" id = "emailinput" name="emailinput" disabled="true"/>
    
    <s:password label="Password" name="passwordinput"  /> <b>${pwdmsg}</b>
    <s:submit id="sub" value="Login"/>
</s:form>
</center>
<br>
<center>
<a href ="<s:url action="forgotPassword"/>"><p id="forgotPsw">Forgot Password?</p></a>
</center>
        
<img src = "accept.png" align = "middle" alt = "ok" width="12" height="12" style="top:370; left:780; position:absolute; z-index:1; visibility: show;"/> 
<span id = "changeFont" style="top:368; left:800; position:absolute; z-index:1; visibility: show;">${message}</span>

<jsp:include page="Footer.jsp" />

<div id="passwordError" style="top:390; left:780; position:absolute; z-index:1; visibility: show;"></div>


</body>
</html>