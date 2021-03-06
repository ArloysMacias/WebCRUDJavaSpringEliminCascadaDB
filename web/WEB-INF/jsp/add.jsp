
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>CRUD</title>
        <%@include file="estilos.jsp" %>   
    </head>

    <body>
        <div class="container">
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="<c:url value="home.htm"/>">Home</a></li>
                    <li class="breadcrumb-item active" aria-current="<c:url value="add.htm"/>">Add</li>
                </ol>
            </nav>
            <legend>Insert user</legend>
            <br>
            <!-- con el metodo post -->
            <f:form class="form-horizontal" commandName="persona"> 

                <fieldset>                   
                    <f:errors path="nombre" element="div" cssClass="alert alert-danger"></f:errors>
                    <f:errors path="correo" element="div" cssClass="alert alert-danger"></f:errors>    
                    <f:errors path="pais" element="div" cssClass="alert alert-danger"></f:errors>
                    <f:errors path="fecha" element="div" cssClass="alert alert-danger"></f:errors>

                    <!-- full name-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="textinput">Name</label>  
                        <div class="col-md-4">
                            <f:input id="textinput" path="nombre" name="textinput" type="text" placeholder="Example" class="form-control input-md" required="" />
                            <span class="help-block">Enter full name</span>
                        </div>
                    </div>

                    <!-- e-mail-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="correo">E-mail</label>  
                        <div class="col-md-4">
                            <f:input id="correo" path="correo" name="correo" type="email" placeholder="example@server.com" class="form-control input-md" required="" />
                            <span class="help-block">Enter e-mail</span>  
                        </div>
                    </div>

                    <!-- Age-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="edad">Age</label>  
                        <div class="col-md-4">
                            <f:input id="edad" name="edad" path="edad" type="number" min="18" max="80" value="18" step="1" class="form-control input-md" required="" />
                            <span class="help-block">Write or select age</span>  
                        </div>
                    </div>

                    <!-- Date of birth-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="Fecha_Puntuacion">Date of birth</label>  
                        <div class="col-md-4">
                            <f:input id="Fecha_Nacimiento" name="Fecha_Nacimiento" path ="fecha" type="date" min="1800-01-01" max="2019-01-01"  class="form-control input-md" required=""/>
                            <span class="help-block">Select date of birth</span>  
                        </div>
                    </div>

                    <!-- country -->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="pais">Select country</label>
                        <div class="col-md-4">
                            <f:select id="pais" path="pais" name="pais" class="form-control">
                                <option value="ninguno">Select......</option>
                                <f:options items="${listaPaises}"/>
                                <!--Aqui podria haber puesto tambien  paisLista porque    @ModelAttribute("paisLista")-->
                            </f:select>
                        </div>
                        <!--Boton que al pinchar activa el modelo DATA-TARGET-->
                        <!--<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModalScrollable" data-whatever="@mdo">Add Country</button>-->
                        <a href="<c:url value="addPais.htm"/>"><img  src="<c:url value="public/images/icons8_wrench_filled_25px_1.png"/>"/></a>
                    </div>
                    <br>
                    <!-- Button -->
                    <div class="form-group">
                        <label class="col-md-7 control-label" for="enviar">Send</label>
                        <div class="col-md-4">
                            <button id="enviar"   type="submit" name="enviar" class="botonArloys"><img  src="<c:url value="public/images/nuevos/icons8_submit_document_44px.png"/>"/></button> 
                        </div>
                    </div>
                </fieldset>
            </f:form> 
        </div> 
    </body>
</html>
