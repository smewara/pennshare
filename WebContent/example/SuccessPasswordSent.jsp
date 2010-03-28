<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
	<title>Web 2 Share</title>
	<link rel="stylesheet" href="style-login.css" type="text/css" />
	<script type="text/javascript" src="js/prototype.js"></script>
    <script type="text/javascript" src="js/scriptaculous.js?load=effects,builder"></script>
    <script type="text/javascript" src="js/lightbox.js"></script>
	
<script type="text/javascript">

var emailCheck = "@seas.upenn.edu";
var aa = /@seas.upenn.edu/;


function checkForm(form){

	var aaa = form.emailinput.value;
	var aaaa = aaa.split("@");
	var check = aaa.search(aa);
	
	if((aaaa.length > 2) || (check == -1)){
		document.getElementById('Image').innerHTML = "<img src = \"delete.png\" align = \"middle\" alt = \"ok\" width=\"12\" height=\"12\"/> <span id=\"changeFont\">Invalid Email </span>";
		form.emailinput.focus();
		form.emailinput.value = "";
		return false;
		}
	
	else if((form.emailinput.value == "") ||
			(form.emailinput.value == " ")){
		
		document.getElementById('Image').innerHTML = "<img src = \"delete.png\" align = \"middle\" alt = \"ok\" width=\"12\" height=\"12\"/> <span id=\"changeFont\">Email required</span>";
		form.emailinput.focus();
		form.emailinput.value = "";
		return false;
	}

	
	else if((aaaa.length == 2) && (check != -1)){
		
		return true;
	}
	

	else {
		
		form.emailinput.value = "";
		return false;
		}
		



}

  
</script>
</head>

<body>
<jsp:include page="headerLogin.jsp" />

<center><img src = "logo.jpg"></img></center>
<br>

<center>
<br>
You Temporary Password has been sent to your email box successfully! <img src="smile.png"/>
<br>
<br>
<center><s:form action="NewUserLogin" >
	<s:if test="pwdmsg != null">
		<b>${pwdmsg}</b>
	</s:if>
	<s:else>
		<br>
	</s:else>
    <s:password label="Enter Your Temporary Password Here" name="passwordinput"  />
    <s:submit id="sub" value="Login"/>
</s:form></center>



<jsp:include page="Footer.jsp" />


</body>
</html>