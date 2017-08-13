<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page import="java.util.List"%>
    <%@ page import="DTO.AppointmentDTO"%>
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<title>Patient Appointments</title>
<style>
    
    @import url(https://fonts.googleapis.com/css?family=Roboto:300);

.imgCl {
    height: 90px;
    width: 90px;
    padding-top: 20px;
    padding-left: 20px;
}
.imgProfile {
    height: 20px;
    width: 20px;
}
.dropbtn {
    background-color: #76b852;
    color: white;
    padding: 16px;
    font-size: 16px;
    border: none;
    cursor: pointer;
    right: 0;
}

/* The container <div> - needed to position the dropdown content */
.dropdown {
    position: relative;
    display: inline-block;
    float: right;
}

/* Dropdown Content (Hidden by Default) */
.dropdown-content {
    display: none;
    position: absolute;
    background-color: #f9f9f9;
    min-width: 250px;
    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
}

/* Links inside the dropdown */
.dropdown-content a {
    color: black;
    padding: 12px 16px;
    text-decoration: none;
    display: block;
}

/* Change color of dropdown links on hover */
.dropdown-content a:hover {background-color: #f1f1f1}

/* Show the dropdown menu on hover */
.dropdown:hover .dropdown-content {
    display: block;
}

/* Change the background color of the dropdown button when the dropdown content is shown */
.dropdown:hover .dropbtn {
    background-color: #3e8e41;
}
.item {
    margin: 0 auto; 
    height: 150px;
    width: 850px;
    border-style: ridge;
    border-width: 1px;
    background-color: #F4F4F4;
    box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);
    
}
.imgCal {
    height: 35px;
    width: 35px;
    padding-left: 20px;
}
hr {
    display: block;
    margin-top: 0.5em;
    margin-bottom: 0.5em;
    margin-left: auto;
    margin-right: auto;
    border-style: inset;
    border-width: 1px;
    float: none;
    top:20px
}
.list-page {
  width: 900px;
  padding: 5% 0 0;
  margin: auto;
}
.username {
    position:absolute;
    top: 0;
    right: 100px;
    padding:1px;
}
.date {
    float: right;
}
.time {
    padding-left: 5px;
    font-family:  Lucida Bright, Georgia, serif
}
.date1 {
    padding-right: 40px;
    padding-top: 5px;
    font-family:  Lucida Bright, Georgia, serif
}
.PatientName {
    font-family: "Calibri", sans-serif;
    width: 250px;
    font-weight: bold;
    font-size: 20px;
    display: inline;
    padding-left: 25px;
    position: absolute;
}
.Subject {
    width: 575px;
    display: inline;
    padding-left: 25px;
    position: absolute;
    padding-top: 35px;
    float: left
}
.divider {
    width:20px;
        float: left
}
.form {
    margin: 0 auto; 
    width: 850px;
    position: absolute;
    background: #E3E3E3;
  margin: 0 auto 100px;
  padding: 45px;
  box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);
    
}
.form input {
  font-family: "Times New Roman", sans-serif;
  outline: 0;
  background: #f2f2f2;
  width: 100%;
  border: 0;
  margin: 0 0 15px;
  padding: 15px;
  box-sizing: border-box;
  font-size: 14px;
}
.form button {
  font-family: "Roboto", sans-serif;
  text-transform: uppercase;
  outline: 0;
  background: #4CAF50;
  width: 30%;
  border: 0;
  padding: 15px;
  color: #FFFFFF;
  font-size: 14px;
  -webkit-transition: all 0.3 ease;
  transition: all 0.3 ease;
  cursor: pointer;
}
.form button:hover,.form button:active,.form button:focus {
  background: #43A047;
}
.form .message {
  margin: 15px 0 0;
  color: #b3b3b3;
  font-size: 12px;
}
.form .message a {
  color: #4CAF50;
  text-decoration: none;
}
.form .register-form {
  background-color:  #E3E3E3;
}
.container {
  position: relative;
  z-index: 1;
  max-width: 300px;
  margin: 0 auto;
}
.container:before, .container:after {
  content: "";
  display: block;
  clear: both;
}
.container .info {
  margin: 50px auto;
  text-align: center;
}
.container .info h1 {
  margin: 0 0 15px;
  padding: 0;
  font-size: 36px;
  font-weight: 300;
  color: #1a1a1a;
}
.container .info span {
  color: #4d4d4d;
  font-size: 12px;
}
.container .info span a {
  color: #000000;
  text-decoration: none;
}
.container .info span .fa {
  color: #EF3B3A;
}
body {
  background: #76b852; /* fallback for old browsers */
  background: -webkit-linear-gradient(right, #76b852, #8DC26F);
  background: -moz-linear-gradient(right, #76b852, #8DC26F);
  background: -o-linear-gradient(right, #76b852, #8DC26F);
  background: linear-gradient(to left, #76b852, #8DC26F);
  font-family: "Roboto", sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;      
}

.trilokImg {
    height: 40px;
    width: 40px;
}
    </style>
    


<script type="text/javascript">
	function navigateToAppointments(){
		var email = "${email}";
		window.location.href='http://localhost:8080/DoctorAppointmentManagementSystem/DoctorAppointments?email=' + email;
	}
	
	function httpGetAsync(theUrl, callback, headers)
    {
        var xmlHttp = new XMLHttpRequest();
        xmlHttp.open("GET", theUrl, true);
        for (each in headers)
            xmlHttp.setRequestHeader(each, headers[each])
        
        xmlHttp.onreadystatechange = function() { 
            if (xmlHttp.readyState == 4 && xmlHttp.status == 200)
                callback(xmlHttp.responseText);
        }
        
        xmlHttp.send(null);
    }
	
	function success(res) {
		if (res == "Deleted successfully")
			location.reload(true);
		else
			alert("Failed -- " + res);
	}
	
	function deleteAppointment(appointmentId)
	{
		var headers = {};
		headers["appointmentId"] = appointmentId;
		
		httpGetAsync("http://localhost:8080/DoctorAppointmentManagementSystem/RestServices/details/deleteAppointment", success, headers);
		
	}
</script>
   
   <div class="dropdown">
  <button class="dropbtn"><img class="imgProfile" src="Images/defaultUser.png"> ${username}</button>
  <div class="dropdown-content">
    <a href="http://localhost:8080/DoctorAppointmentManagementSystem">Logout</a>
  </div>
</div>
<div class="list-page">
  <div class="form">
        <center><button onclick="navigateToAppointments()">Back to Appointments</button></center><br>
        <c:forEach items="${appointments}" var="appointment">
          <div class='item'>
                <img src='Images/profilepic.jpg' class = 'imgCl'>
                <p class='PatientName'>Dr. ${appointment.doctorName}</p>
                
				<p class='Subject'>
                ${appointment.doctorRemarks}
    </p>        
                <div class='date'>
                    <p class='date1'>${appointment.appointmentDate}</p>
                </div>
          </div>
          <br>

    </c:forEach>
  </div>
</div>
</html>