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
	<center><img src="resources\logo1.png" width="100" height="50"/>
	
	<p>Faculty Name : ${faculty.name} </p>
	<p>Faculty Email: ${faculty.email}</p>
	<p>Semester :${sem} Year :${year}</p> 
	<table border="2">
  	<thead>
     <tr>
      <th>Course Id</th>
      <th>Course Name</th>
    </tr>
   </thead>
  <tbody>
  	<c:forEach items="${clist}" var="course">  
     <tr>
      <td><a href="aentrymgmt?id=${course.id}&sid=${sem}">${course.id}</a></td>
      <td>${course.courseName}</td>
    </tr>
    </c:forEach>
  </tbody>
</table>

<div>
  <form method="post" action="aentrymgmt">
	<table border="2">
  	<thead>
     <tr>
      <th>Student Id</th>
      <th>Student Name</th>
      <th>Attendance</th>
    </tr>
   </thead>
  <tbody>

  	<c:forEach items="${stlist}" var="atf">  
     <tr>
      <td><input type="text" value=${atf.studentId} name="sid" readonly></td>
      <td>${atf.studentName}
      <input type="hidden" value=${atf.courseId} name="cid" readonly>
      </td>
      <td>
         <input type="text" size="2" name="satt" id="ch1" value=${atf.recordedAttendance}>
      </td>
    </tr>
    </c:forEach>
    </tbody>
</table>
 <input type="submit" value="update"/>
     </form>
</div>
</center>
</body>
</html>