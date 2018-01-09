<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>


<p>Dashboard</p>
<div class="container">

<form:form method="POST" action =  "${user.id}/uploadimg.html"  modelAttribute="user"
    enctype="multipart/form-data">

      <table>
        <tr>
            <td><form:label path="name">${user.name}</form:label></td>
        </tr>

        
        <tr>
        	<td>
        	<img width="100" src="<c:url value="/image/${user.albumId}/imageId.jpg"/>"/>
        	
        	</td>
        	
            <td>Please select a file to upload :</td>
            <td><input type="file" name="file" /></td>
            <td><input type="submit" value="upload" /></td>
        </tr>
        
     </table>
    

     
    
    
    

</form:form>
			            
	                
	                

	            
	</div>
<%@ include file="common/footer.jspf"%>