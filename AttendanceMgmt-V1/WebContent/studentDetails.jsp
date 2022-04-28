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
	<table border="2">
  	<thead>
     <tr>
      <th>Id</th>
      <th>First Name</th>
      <th>Last Name</th>
      <th>Degree Name</th>
      <th>Spec Name</th>
      <th>Batch Year</th>
      <th>Current Semester</th>
    </tr>
   </thead>
  <tbody>
  	<c:forEach items="${slist}" var="student">  
     <tr>
      <td><a href="amgmt?id=${student.id}">${student.id}</a></td>
      <td>${student.firstName}</td>
      <td>${student.lastName}</td>
      <td>${student.degreeName}</td>
      <td>${student.specName}</td>
      <td>${student.batchYear}</td>
      <td>${student.currentSemester}</td>
    </tr>
    </c:forEach>
  </tbody>
</table>
</body>
</html>