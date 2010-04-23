<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
    <title>Web 2 Share</title>
    <script src="http://maps.google.com/maps?file=api&v=1
            &key=ABQIAAAAh3DOgE386URhCYdIXhUiSxQGoEh6LqRYT1bkfw3-ikvsyZ6QehReC8j9W6kNSvhbBw0cj6y5vgbNTA" type="text/javascript">
    </script>
    <style type= "text/css">
    .rightalign {
        text-align: right;
        font-size: 15;
                }
    </style>
</head>

<body>
<br>
<!--  <a href="<s:url action="ShowUserProfile">
		 	<s:param name="email">${email}</s:param>
		 </s:url>">${username}</a> -->
<div class="rightalign"><img src="door_out.png" width="17" height = "17"/>   <a href="<s:url action="Logout"/>" target="_parent"><font color="Maroon">Logout</font></a>  
<br></div>
<center>
<s:if test="numofowners != 0">
	<b><big>Results Found</big></b>
</s:if>
<s:else>
	<b><big>No Results Found</big></b>
</s:else>

<br><img src="search.png" width="17" height = "17"/> <a href="<s:url action="Search">
		 </s:url>">Renew Search</a> 
<hr>

<!--
<s:iterator value="owners">

	<br>
    <b><big><s:property value="itemname"/></big></b>
    <br>
    <br>
    <b>Owner</b>: <s:property value="name"/>
    <br>
    <b>Address</b>: <s:property value="address"/>
    <br>
    <br>
   	<a href="<s:url action="RequestItem">
			<s:param name="requesteremail">${requesteremail}</s:param>
            <s:param name="itemid"><s:property value= "itemid"/></s:param>
         </s:url>"><big>Request This Item!</big></a>
    <br>
    <br>
    <hr>
</s:iterator>
<br>-->

<s:if test="numofowners != 0">
	<br>
	<div id="map" style="width: 800px; height: 550px"></div>
</s:if>
</center>

<script type="text/javascript">
//<![CDATA[

var map = null;
var geocoder = null;

var itemname;
var categoryid;
var ownername;
var owneraddress;
var markerhtml;

initialize();

<s:iterator value="owners">

	itemname = "<s:property value="itemname"/>";
	categoryid = "<s:property value="categoryid"/>";
	ownername = "<s:property value="name"/>";
	owneraddress = "<s:property value="address"/>";

	markerhtml = "<table><tr>";
	if(categoryid == 1) {
		markerhtml +="<td><img src=\"<s:property value="itemimage" />\" width=\"200\" height=\"300\" /></td>";
	}
	markerhtml +="<td><center><font color=\"darkblue\"" + 
				 "<b><big>" + itemname + "</big></b><br><hr>" + 
	 			 "<b>Owner</b>: " + ownername + "<br>" +
	 			 "<b>Address</b>: " + owneraddress + "<br><br>" +
	 			 "<a href=\"<s:url action="RequestItem">" +
	 			 	"<s:param name="requesteremail">${requesteremail}</s:param>" +
	             	"<s:param name="itemid"><s:property value="itemid"/></s:param>" +
	 			 "</s:url>\"><big>Request This Item!</big></a>" +
	 			 "</font></center></td>" +
				 "</tr></table>";

	showAddress(owneraddress, markerhtml);
	
</s:iterator>



//showAddress("3650 Chestnut St, Philadelphia, PA", "<big>lala</big><a href=\"http://www.w3schools.com/\">Visit W3Schools!</a>");
//showAddress("3800 Spruce St, Philadelphia, PA", "heng");

function initialize() {
  if (GBrowserIsCompatible()) {
    map = new GMap2(document.getElementById("map"));
    //map.setCenter(new GLatLng(37.4419, -122.1419), 13);
    map.addControl(new GLargeMapControl());
    geocoder = new GClientGeocoder();
  }
}

function showAddress(address, html) {
  if (geocoder) {
    geocoder.getLatLng(
      address,
      function(point) {
        if (!point) {
          alert(address + " not found");
        } else {
          map.setCenter(point, 13);
          var marker = new GMarker(point);      
          GEvent.addListener(marker, "click",
	           function() {
	            marker.openInfoWindowHtml(html);  
	           }   
          );          
          map.addOverlay(marker);    
        }
      }
    );
  }
}

 //]]>
 </script>
    
</body>
</html>