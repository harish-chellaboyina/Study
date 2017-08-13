<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<title>Add Organ Donation</title>
<style>
    
    @import url(https://fonts.googleapis.com/css?family=Roboto:300);

.imgCl {
    height: 90px;
    width: 90px;
    padding-top: 20px;
    padding-left: 20px;
}
.item {
    margin: 0 auto; 
    height: 590px;
    width: 500px;
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
  margin-top: auto;
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
    padding-top: 10px;
    font-family:  Lucida Bright, Georgia, serif
}
.PatientName {
    font-family: "Calibri", sans-serif;
    width: 150px;
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
    position: relative;
  z-index: 1;
  background: #E3E3E3;
  width: 550px;
  margin: 0 auto 100px;
  padding: 45px;
  text-align: center;
  box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);
    
}
.textarea {
    width: 310px;
	height: 120px;
	border: 1px solid #cccccc;
	padding: 5px;
    font: 20px "Times New Roman", sans-serif;
	background-image: url(bg.gif);
	background-position: bottom right;
	background-repeat: no-repeat;
}
.ctextbox {
  margin: 10px;
  border: 1px solid #111;
  background: white;
  width: 280px;
  padding: 5px 35px 5px 5px;
  font-size: 16px;
  border: 1px solid #ccc;
  height: 34px;
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
}
.form button {
  font-family: "Roboto", sans-serif;
  text-transform: uppercase;
  outline: 0;
  background: #4CAF50;
  width: 320px;
  border: 0;
  position: relative;
  padding: 13px;
    padding-top: 20px;
  right: 0;
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
    
.SubHeading {
    font: 25px "Times New Roman", sans-serif;
    font-weight: bold;
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
.heading {
    font-family: "Times New Roman", sans-serif;
    font-size: 30;
    font-weight: bold;
    padding-left: 10px;
}
.custom {
    display: inline;
    position: absolute;
}
.select {
  margin: 10px;
  border: 1px solid #111;
  background: transparent;
  width: 320px;
  padding: 5px 35px 5px 5px;
  font-size: 16px;
  border: 1px solid #ccc;
  height: 34px;
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
   background: url(Images/dropdown.png) 96% / 8% no-repeat #eee;

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
.dateselect {
  margin: 10px;
  border: 1px solid #111;
  background: transparent;
  width: 90px;
  padding: 5px 35px 5px 5px;
  font-size: 16px;
  border: 1px solid #ccc;
  height: 34px;
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
   background: url(Images/dropdown.png) 96% / 20% no-repeat #eee;

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
    </style>
<script type="text/javascript">
    
    
    
    function httpGetAsync(theUrl, callback, headers)
    {
        var xmlHttp = new XMLHttpRequest();
        xmlHttp.open("GET", theUrl, true);
        for (each in headers)
            xmlHttp.setRequestHeader(each, headers[each]);
        
        xmlHttp.onreadystatechange = function() { 
            if (xmlHttp.readyState == 4 && xmlHttp.status == 200)
                callback(xmlHttp.responseText);
        };
        
        xmlHttp.send(null);
    }
    
    function succCallback(res)
    {
        if (res == "Added successfully")
        {
            alert(res);
            window.location.href = "http://localhost:8080/DoctorAppointmentManagementSystem";
        }
        else{
            alert(res);
        }
            
    }
    function addDonation()
    {
        var headers = {};
        headers["organName"] = document.getElementById("Organ").selectedOptions[0].value;
        headers["organDonater"] = document.getElementById("Donater").value;
        headers["email"] = document.getElementById("email").value;
        headers["mobile"] = document.getElementById("mobile").value;
        headers["address"] = document.getElementById("address").value;
        
        httpGetAsync("http://localhost:8080/DoctorAppointmentManagementSystem/RestServices/details/addDonation", succCallback, headers);
        
    }
    </script>    
    
<center><div class="list-page">
  <div class="form" >
      <div class="item">
          <p class="SubHeading">Organ Donation</p>
          <center>
        <select id="Organ" class="select" onchange="changeDoctorsList()">
       			  <option>Select Organ</option>
            	  <option>Heart</option>
                  <option>Intestine</option>
                  <option>Kidney</option>
                  <option>Liver</option>
                  <option>Lungs</option>
                  <option>Pancreas</option>
                  <option>Bone</option>
                  <option>Cartilage</option>
                  <option>Corneas</option>
                  <option>Fascia</option>
                  <option>Heart Valves</option>
                  <option>Ligaments</option>
                  <option>Pericardium</option>
                  <option>Skin</option>
                  <option>Tendons</option>
                  <option>Veins</option>
                  
          </select>
              <input type="text" id = "Donater" class="ctextbox" placeholder="Donater Name"/>
              <input type="text" id = "mobile" class="ctextbox" placeholder="Mobile"/>
              <input type="text" id = "email" class="ctextbox" placeholder="Email"/>
          </center>
          
          <div style="padding-top:10px"><textarea id = "address" class="textarea" placeholder="Address"></textarea></div>
          <br>
          <button onclick="addDonation()">Add</button>

      </div>
      <br>
    </div></div>
    </center>
</html>