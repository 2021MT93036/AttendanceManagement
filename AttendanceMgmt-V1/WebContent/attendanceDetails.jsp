<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Attendance Management System</title>
</head>
<body color="purple">
	<h1 align="center"> Group AK</h1>
	<h2 align="center"> Attendance Management System</h2>
	<center><img src="resources\logo1.png" width="200" height="100"/>
	<p> 
	 <b>Id :</b> ${student.id}
	 <p>
	<b> Name :</b> ${student.firstName} ${student.lastName}
	 <br>
	 <table border="2">
	 <tr>
	 	<th> Course Name </th>
	 	<th> Percent Attendance </th>
	 </tr>
	 <c:forEach items="${alist}" var="attend">  
     <tr align="center">
      <td>${attend.courseName}</td>
      <td>${attend.perAttendance}</td>
      
      </tr>
      </c:forEach>
    </table>
</body>
</html>