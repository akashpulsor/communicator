<%@page import="com.communicate.model.User"%>
<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>


<p>Dashboard</p>
<div class="container">
<a href="<c:url value="/logout" />">Logout</a>
<form:form method="POST" action =  "uploadimg.html"  modelAttribute="user"
    enctype="multipart/form-data">

      <table>
        <tr>
            <td><form:label path="name">${user.name}</form:label></td>
        </tr>

        
        <tr>
        	<td>
        	<img width="100" src="image/${user.id}/${user.albumId}/${user.proflePicId}"/>
        	
        	</td>
        	
        	
        	<td>
        	
        	<input type = "hidden" name = "userid" value = "<c:out value = "${user.id}" />"/>
        	<input type = "hidden" name = "image_type" value = "profile_pic" />
        	</td> 
            <td>Please select a file to upload :</td>
            <td><input type="file" name="file" /></td>
            <td><input type="submit" value="upload" /></td>
        </tr>
        
     </table>
    

     
    
    
    

</form:form>
			            
	                
	                

	            
	</div>
<%@ include file="common/footer.jspf"%>