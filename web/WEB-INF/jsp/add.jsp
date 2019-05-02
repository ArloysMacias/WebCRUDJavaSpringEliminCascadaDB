
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
                        
                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="textinput">Name</label>  
                            <div class="col-md-4">
                            <f:input id="textinput" path="nombre" name="textinput" type="text" placeholder="Example" class="form-control input-md" required="" />
                            <span class="help-block">Enter full name</span>
                        </div>
                    </div>
                    
                    <!-- Text input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="correo">E-mail</label>  
                        <div class="col-md-4">
                            <f:input id="correo" path="correo" name="correo" type="email" placeholder="example@server.com" class="form-control input-md" required="" />
                            <span class="help-block">Enter e-mail</span>  
                        </div>
                    </div>
                    
                    <!-- Text input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="edad">Age</label>  
                        <div class="col-md-4">
                            <f:input id="edad" name="edad" path="edad" type="number" min="18" max="40" value="18" step="1" class="form-control input-md" required="" />
                            <span class="help-block">Write or select age</span>  
                        </div>
                    </div>
                    
                    <!-- Text input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="Fecha_Puntuacion">Date of birth</label>  
                        <div class="col-md-4">
                            <f:input id="Fecha_Nacimiento" name="Fecha_Nacimiento" path ="fecha" type="date" min="1800-01-01" max="2019-01-01"  class="form-control input-md" required=""/>
                            <span class="help-block">Select date of birth</span>  
                        </div>
                    </div>
                    
                    <!-- Select Basic -->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="pais">Select country</label>
                        <div class="col-md-4">
                            <f:select id="pais" path="pais" name="pais" class="form-control">
                                <option value="ninguno">Select......</option>
                                <f:options items="${paisLista}"/>
                            </f:select>
                        </div>
                        
                        
                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal" data-whatever="@mdo">Add Country</button>
                        
                        
                        <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="row">
                                        
                                        <div class="col-md-6 ml-auto">
                                            <div class="container-fluid">
                                                <div class="modal-header">
                                                    <h4 class="modal-title" id="exampleModalLabel">Add another country</h4>
                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>
                                                <div class="modal-body"> 
                                                    <form>
                                                        <div class="form-group">
                                                            <label> Name</label>
                                                            <input type="text" class="form-control">
                                                            <small class="form-text text-muted">Make sure that the country does not exist</small>
                                                        </div>
                                                    </form>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                                    <button type="button" class="btn btn-primary">Save</button>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6 ml-auto">
                                            <iframe src="https://www.google.com/maps/embed?pb=!1m14!1m12!1m3!1d52084671.18180194!2d40.265177402728376!3d35.571869140229495!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!5e0!3m2!1ses!2sse!4v1556755086375!5m2!1ses!2sse" width="600" height="450" frameborder="0" style="border:0" allowfullscreen></iframe>
                                        </div>
                                        
                                        
                                    </div>
                                    
                                </div>
                            </div>
                        </div>
                        
                        
                        
                    </div>
                    <!-- Button -->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="enviar"></label>
                        <div class="col-md-4">
                            <button id="enviar" type="input" name="enviar" class="btn btn-primary">Add</button>              
                        </div>
                    </div>
                </fieldset>
            </f:form>           
        </div>
    </body>
</html>
