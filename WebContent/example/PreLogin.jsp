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
var checkSpace = ' ';
var alphabets = /[^a-zA-Z]/;
var numbers = /[^0-9]/;



function checkForm(form){

	var aaa = form.emailinput.value;
	var aaaa = aaa.split("@");
	var check = aaa.search(aa);
	var checkSpaceTest = aaaa[0].replace(/\s/g,"&");
	var checkNonNumbersTest = checkSpaceTest.replace(/[0-9]/g,"");
	var checkNonAlphaTest = checkNonNumbersTest.replace(/[a-zA-Z]/g,"");

	
	
	if((aaaa.length > 2) || (check < 2) || (checkNonAlphaTest.length > 0)){
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

		document.getElementById('Image').innerHTML = "<img src = \"delete.png\" align = \"middle\" alt = \"ok\" width=\"12\" height=\"12\"/> <span id=\"changeFont\">Email required</span>";
		form.emailinput.focus();
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

<center><s:form action="PreLogin" onsubmit="return checkForm(this)">
	<span id="changeFont">All you need is your</span> <b id="address"> seas.upenn.edu </b> <span id="changeFont">email address</span>
	<br>
	
    <s:textfield id = "emailinput" name="emailinput"/>
    
    <s:submit  id="sub" value="Login"/>
</s:form><center><div id = 'Image' style="top:360; left:720; position:absolute; z-index:1; visibility: show;"></div></center>
</center>



<jsp:include page="Footer.jsp" />


</body>
</html>