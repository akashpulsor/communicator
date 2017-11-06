<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- Basic -->

<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>Communicator</title>
<meta name="keywords" content="HTML5 Theme" />
<meta name="description" content="Megakit - HTML5 Theme">

</head>
 <body>
		<!--========== HEADER ==========-->
	<header
		class="navbar-fixed-top s-header js__header-sticky js__header-overlay">

	</header>
	
	<form:form method="POST" action="register.html" modelAttribute="registrationForm">
             <table>
                <tr>
                    <td><form:label path="name">Name</form:label></td>
                    <td><form:input path="name"/></td>
                </tr>
                <tr>
                    <td><form:label path="email">Email</form:label></td>
                    <td><form:input path="email"/></td>
                </tr>
                
                <tr>
                    <td><form:label path="password">Password</form:label></td>
                    <td><form:password path="password"/></td>
                </tr>
                <tr>
                    <td><form:label path="mobileNumber">Mobile</form:label></td>
                    <td><form:input path="mobileNumber"/></td>
                </tr>
                <tr>
                	<td><form:label path="password">Sex</form:label></td>
                	<td><form:radiobutton path="gender" value="M"/>Male</td>
                    <td><form:radiobutton path="gender" value="F"/>Female</td>
                    <td><form:radiobutton path="gender" value="O"/>Other</td>
                </tr>
                <tr>
                	<td><form:label path="password">Interest</form:label></td>
                	<td><form:radiobutton path="sexualInterest" value="M"/>Male</td>
                    <td><form:radiobutton path="sexualInterest" value="F"/>Female</td>
                    <td><form:radiobutton path="sexualInterest" value="O"/>Other</td>
                </tr>
                
                <tr>
                	<td><form:select path="day"></form:select></td>
                	<td><form:select path="month"></form:select></td>
                	<td><form:select path="year"></form:select></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Submit"/></td>
                </tr>
            </table>
        </form:form>
	<!--========== END HEADER ==========-->
</body>
</html>