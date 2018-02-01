
<%@ include file="common/header.jspf"%>
<div class="collapse navbar-collapse" id="navbarCollapse">
<br>
 <ul class="navbar-nav mr-auto pull-right" >
        <!--  <li class="nav-item active">
              <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
         </li> -->
            <li class="nav-item">
              <a class="nav-link" href="<c:url value="/logout" />">Logout</a>
            </li>
            <!-- <li class="nav-item">
              <a class="nav-link disabled" href="#">Disabled</a>
            </li>  -->
          </ul>
 
  </div>
  
<%@ include file="common/headerBoundary.jspf"%>

<div class="container">
    <div class="cm-profile">
        <%-- <img align="left" class="cm-image-profile img-thumbnail img-fluid rounded float-left" src="image/${user.id}/${user.albumId}/${user.proflePicId}" alt="${user.name}"/> --%>
        <div class="cmPc-block">
        	<img align="left" class="img-responsive img-thumbnail rounded-circle" style="width:20%" src="image/${user.id}/${user.albumId}/${user.proflePicId}" alt="${user.name}"/>
        </div>
        
        <div class="cm-profile-text">
            <h1>${user.name}</h1>
        </div>
    </div>
</div> 

<%-- <div class="col-sm-5 pull-right" >

	<a href="<c:url value="/logout" />" class="pull-right" >Logout</a>
</div>



<%@ include file="common/headerBoundary.jspf"%>

<p>Dashboard</p>
<div class="container">

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
			            
	                
	                

	            
	</div> --%>
<%@ include file="common/footer.jspf"%>