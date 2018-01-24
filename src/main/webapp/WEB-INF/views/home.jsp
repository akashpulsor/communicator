<%@ include file="common/header.jspf"%>


<%@ include file="common/navigation.jspf" %>  


	<div class="container" >
		<form:form method="POST"  action="register.html" modelAttribute="registrationForm" class="form-control">
             <table>
                <tr>
                	
                    <td><form:label path="name" >Name</form:label></td>
                    <td><form:input path="name" /></td>
                </tr>
                <tr>
                    <td><form:label path="email"  >Email</form:label></td>
                    <td><form:input path="email"  /></td>
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
                	<td><input type="hidden" name = "${_csrf.parameterName} "value="${_csrf.token}"/></td>
                    <td><input type="submit" value="Submit"/></td>
                </tr>
            </table>
        </form:form>
	</div>
	
        
        <div>
     
        	<form method="POST" action="login.html">
	             <table>
	                 <tr>
	                 	<td><label >Mobile/Email</label></td>
	                	 <td><input type='text' class="form-control" name='login' value=''></td>
	                	 
	                </tr>
	                
	                <tr>
	                	
	                	<td><label>Password</label></td>
	                	<td>
	                		<input type="password" class="form-control" id="password" name="password" placeholder="Enter Password" required>
	                	</td>
	                	
	                </tr> 
	                
	                 <tr>
	                 	<td><input type="hidden" name = "${_csrf.parameterName} "value="${_csrf.token}"/></td>
	                    <td><input type="submit" value="Login"/></td>
	                </tr>
	            </table>
        	</form>
        </div>
<%@ include file="common/footer.jspf"%>
