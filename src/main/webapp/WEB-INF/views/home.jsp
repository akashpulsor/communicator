<%@ include file="common/header.jspf"%>
<nav class="navbar navbar-toggleable-md navbar-light-blue bg-faded">
  <div class="collapse navbar-collapse" id="navbarSupportedContent">

	<header class="navbar navbar-expand navbar-dark flex-column flex-md-row bd-navbar">
	Communicator</header>
	
  </div>  
</nav>


	<div class="container">
		<form:form method="POST" action="register.html" modelAttribute="registrationForm" class="form-signin">
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
                	<td><form:label path="gender">Sex</form:label></td>
                	<td><form:radiobutton path="gender" value="M"/>Male</td>
                    <td><form:radiobutton path="gender" value="F"/>Female</td>
                    <td><form:radiobutton path="gender" value="O"/>Other</td>
                </tr>
                <tr>
                	<td><form:label path="sexualInterest">Interest</form:label></td>
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
                	<td>
                		<form:input type = "hidden" name = "roles" path = "roles" value = "ROLE_USER" />
                	</td>
                </tr>
                <tr>
                    <td><input type="submit" value="Submit"/></td>
                </tr>
            </table>
        </form:form>
	</div>
	
        
        <div>
        	<form:form method="POST" action="login.html" modelAttribute="loginForm">
	             <table>
	                 <tr>
	                 	<td><form:label path="login">Mobile/Email</form:label></td>
	                	 <td><form:input path="login"/></td>
	                	 
	                </tr>
	                
	                <tr>
	                	
	                	<td><form:label path="password">Password</form:label></td>
	                	<td><form:password path="password"/></td>
	                </tr> 
	                
	                 <tr>
	                    <td><input type="submit" value="Login"/></td>
	                </tr>
	            </table>
        	</form:form>
        </div>
<%@ include file="common/footer.jspf"%>
