<%@ include file="common/header.jspf"%>


 <div class="col-sm-6 form-group pull-right" > 		 
 				<form method="POST" action="login.html">	 	
    				<div class="col-sm-5 form-group" >
    					<input type="email"  class="form-control" name='login' value='' placeholder="Email/number" required>
    				</div>
    				<div class="col-sm-5 form-group">
    					<input type="password"  class="form-control" id="password" name="password" placeholder="Enter Password" required>
    					<input type="hidden" name = "${_csrf.parameterName} "value="${_csrf.token}"/>
    				</div>
    				<span class="input-group-btn"><button class="btn  btn-primary">Login</button></span>
        		</form>
    		
 </div>
    	
<%@ include file="common/headerBoundary.jspf"%>
 	
<div class = "container ">
	
		<div class ="col-sm-6 pull-right">
 		
			<form:form method="POST"  action="register.html" modelAttribute="registrationForm" class="pull-right well" >

					<div class="form-group">
						<div class="form-inline row">
    						<div class="form-group col-sm-6">
      							<form:input path="firstName" type="text"  class="form-control"  placeholder="First Name" required="true" />
    						</div>
    						<div class="form-group col-sm-6">
    							<form:input path="lastName" type="text"  class="form-control" placeholder="Last Name" required="true" />
    						</div>
  						</div>
				  </div>
					
				<div class="form-group">
					<form:input path="email" type="email"  class="form-control" placeholder="Email" required="true" />
				</div>

				<div class="form-group">
					<div class="form-inline row">
   						<div class="form-group col-sm-6">
     							<form:input path="password" type="password"  id="inputPassword" placeholder="Password" class="form-control" required="true" />
     					</div>
   						<div class="form-group col-sm-6">
   							<form:input path="confirmPassword" type="password" id="inputPasswordConfirm" placeholder="Confirm Password" class="form-control" data-match-error="Whoops, these don't match" data-match="#inputPassword"  required="true"/>
     						<div class="help-block with-errors"></div>
   						</div>
 					</div>
			  </div>
			  
			  <div class="form-group">
					<form:input path="mobileNumber" class="form-control"  type="tel" placeholder="+91-(9818)-972-040" required="true" />
			  </div>
			  
			  <div class="form-group">			  		
   						<div class="form-group col-sm-6 ">
   							<div class="form-inline row">
   								<form:label path="gender" class = "control-label"> Gender</form:label>
   							</div>
   							
   							<div class="form-inline row">
   								<div class = "radio">
   									<form:label path="gender" class = "fa"> 
   										<form:radiobutton path="gender" class = "fa" value="F" required="true"/>
   										Female
   									</form:label>
   								</div>
   							</div>
   							
   							<div class="form-inline row">
   								<div class = "radio">
   									<form:label path="gender" class = "fa"> 
   										<form:radiobutton path="gender" class = "fa" value="M" required="true"/>
   										Male
   									</form:label>
   								</div>
   							</div>
   							
   							<div class="form-inline row">
   								<div class = "radio">
   									<form:label path="gender" class = "fa"> 
   										<form:radiobutton path="gender" class = "fa" value="O" required="true"/>
   										Other
   									</form:label>
   								</div>
   							</div>
   						</div>
   						
   						 <div class="form-group col-sm-6">
   							<div class="form-inline row">
   								<form:label path="sexualInterest" class = "control-label"> Interest</form:label>
   							</div>
   							
   							<div class="form-inline row">
   								<div class = "radio">
   									<form:label path="sexualInterest" class = "fa"> 
   										<form:radiobutton path="gender" class = "fa" value="F" required="true"/>
   										Female
   									</form:label>
   								</div>
   							</div>
   							
   							<div class="form-inline row">
   								<div class = "radio">
   									<form:label path="sexualInterest" class = "fa"> 
   										<form:radiobutton path="gender" class = "fa" value="M" required="true"/>
   										Male
   									</form:label>
   								</div>
   							</div>
   							
   							<div class="form-inline row">
   								<div class = "radio">
   									<form:label path="sexualInterest" class = "fa"> 
   										<form:radiobutton path="gender" class = "fa" value="O" required="true"/>
   										Other
   									</form:label>
   								</div>
   							</div>
   						</div>
			  </div>
			
			   <div class="form-group">
			   		<form:label path="sexualInterest" class = "control-label"> Date Of Birth</form:label>
			   		<div class="input-append date form_datetime">
			   			<input size="16" type="text" value="" placeholder="MM/DD/YYY" readonly>
    					<span class="add-on"><i class="icon-th"></i></span>
			   		</div>

			   		
			   </div>			  

			</form:form>

<%--
                
                <tr>
                	<td><input type="hidden" name = "${_csrf.parameterName} "value="${_csrf.token}"/></td>
                    <td><input type="submit" value="Submit"/></td>
                </tr>
             --%>
            
        
	
	</div>
		
</div>

<%@ include file="common/footer.jspf"%>
