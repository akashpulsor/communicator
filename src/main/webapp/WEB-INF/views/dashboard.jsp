<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>


<p>Dashboard</p>
<div class="container">

<form:form method="POST" action =  "uploadimg.html"  modelAttribute="user"
    enctype="multipart/form-data">

      <table>
        <tr>
            <td><form:label path="profilename">${user.name}</form:label></td>
        </tr>

        
        <tr>
        	<td>
        	<img width="100" src="<c:url value="${dashboard.imagepath}"/>"/>
        	
        	</td>
        	
            <td><form:label path="file">Please select a file to upload :</form:label></td>
            <td><input type="file" name="file" /></td>
        </tr>
        <tr>
            <td><input type="submit" value="Submit"/></td>
        </tr>
     </table>
    

     
    <input type="submit" value="upload" />
    
    

</form:form>
			            
	                
	                

	            
	</div>
<%@ include file="common/footer.jspf"%>