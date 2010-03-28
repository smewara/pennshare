<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
	<title>Web 2 Share</title>
	
	<script type="text/javascript">




function checkForm(form){


	var formvalues = form.firstname.value;
	
	var checkSpaceTest = formvalues.replace(/\s/g,"&");
	var checkNonNumbersTest = checkSpaceTest.replace(/[0-9]/g,"");
	var checkNonAlphaTest = checkNonNumbersTest.replace(/[a-zA-Z]/g,"");
	

	var formLastName = form.lastname.value;

	var checkSpaceTest2 = formLastName.replace(/\s/g,"&");
	var checkNonNumbersTest2 = checkSpaceTest2.replace(/[0-9]/g,"");
	var checkNonAlphaTest2 = checkNonNumbersTest2.replace(/[a-zA-Z]/g,"");


	var addressNo = form.address_no.value;
	var checkSpaceTest3 = addressNo.replace(/\s/g,"&");
	var checkNonNumbersTest3 = checkSpaceTest3.replace(/[0-9]/g,"");
	var checkNonAlphaTest3 = checkNonNumbersTest3.replace(/[a-zA-Z]/g,"##");
	
	
	var passwordTest = form.password.value;
	var checkSpaceTest4 = addressNo.replace(/\s/g,"&");
	var checkNonNumbersTest4 = checkSpaceTest4.replace(/[0-9]/g,"");
	var checkNonAlphaTest4 = checkNonNumbersTest4.replace(/[a-zA-Z]/g,"##");	

	

	if((checkNonAlphaTest.length > 0) || (checkNonAlphaTest2.length > 0)){
		alert("Please enter a valid first name/last name");
		}

	
	if(checkNonAlphaTest3.length > 0){
		alert("Please enter a valid Address No. (e.g. 3650) it should be your street number only");
		}


	if(checkNonAlphaTest4.length > 0){
		alert("Your password cant have white spaces or special characters, please fill the form again");
		}
	
	if(formvalues.length > 45){
		alert("Your first name is a little long, why dont you use your nick name");
		form.firstname.value = "";
		form.lastname.value = "";
		form.zipcode.value = "";
		form.password.value = "";
		return false;
		}

	else if(formLastName.length > 45){
		alert("Your last name is a little long, why dont you use your nick name");
		form.firstname.value = "";
		form.lastname.value = "";
		form.zipcode.value = "";
		form.password.value = "";
		return false;
		}
	
	else if((checkNonAlphaTest.length > 0) || (checkNonAlphaTest2.length > 0) ||
			(checkNonAlphaTest3.length > 0) || (checkNonAlphaTest4.length > 0)){


		form.firstname.value = "";
		form.lastname.value = "";
		form.zipcode.value = "";
		form.password.value = "";
		alert("OOps you must have entered invalid data, Please fill the form again!");
		return false;
		}
		
	else {
		
		return true;
	}
	

}

  
</script>
</head>

<body>
<br>
<jsp:include page="headerLogin.jsp" />
<center>
<s:if test="message != null">
	<b>${message}</b>
</s:if>
<s:else>
	<br>
</s:else>
<s:form action="join" onsubmit="return checkForm(this)">

	<s:textfield label="*First Name" name="firstname"/>
 	<s:textfield label="*Last Name" name="lastname" />
 	<s:textfield label="*Address No. (e.g. 3650)" name="address_no" />
	<s:textfield label="*Address Street (e.g. Chestnut St)" name="address_street" />
	<s:textfield label="City" name="city" value="Philadelphia" disabled="true"/>
	<s:textfield label="State" name="state" value="PA" disabled="true"/>
	<s:textfield label="Zip Code" name="zipcode" />
	<s:password label="*New Password" name="password" />
	<s:submit/>
</s:form>
</center><jsp:include page="Footer.jsp" />
</body>
</html>