
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
                    <li class="breadcrumb-item active" aria-current="<c:url value="addPais.htm"/>">Add Country</li>
                </ol>
            </nav>
            <legend>Insert Country</legend>
            <br>
            <div class="container">
                <f:form class="form-horizontal" commandName="paisSS"> 
                    <fieldset>                   
                        <div class="row">
                            <f:errors path="nombrePais" element="div" cssClass="alert alert-danger"></f:errors>
                            <div class="col-md-4">
                                <!--pequeño formulario de recogida del nombre del pais-->
                                <div class="form-group">
                                    <div class="row">
                                        <label class="col-md-2 control-label" for="textinput">
                                            Name
                                        </label>
                                        <div class="col-md-8">
                                            <f:input id="textinput" path="nombrePais" name="textinput" type="text" placeholder="Pais" class="form-control input-md" required="" />
                                            <small class="form-text text-muted">Make sure the country does not exist in the list</small>
                                        </div>
                                        <div class="col-md-2">
                                            <button id="enviar"  type="submit" name="enviar" class="btn btn-primary">Add</button>
                                        </div>
                                    </div>   
                                </div>
                                <div class="row">
                                    <!--Tabla-->
                                    <div class="scrollArloys scrollCaracterist">
                                        <table class="table">   
                                            <thead class="thead-dark">
                                                <tr>
                                                    <th scope="col">Id</th>
                                                    <th scope="col">Name</th>
                                                    <th scope="col">Actions</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${tablaPaises}" var="TablaPaises">
                                                    <tr>
                                                        <td><c:out value="${TablaPaises.idPais}"/></td>
                                                        <td><c:out value="${TablaPaises.nombrePais}"/></td>
                                                        <td> 
                                                            <a href="<c:url value="deletePais.htm?id=${TablaPaises.idPais}"/>"><img  src="<c:url value="public/images/icons8-trash-can-25.png"/>"/></a>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-8">
                                <!--Mapa-->
                                <div class="col-md-8 ml-auto">
                                    <div class="container-fluid">
                                        <iframe src="https://www.google.com/maps/embed?pb=" width="720" height="465" frameborder="0" style="border:0" allowfullscreen></iframe>
                                    </div>
                                </div>
                            </div>
                        </div> 
                    </fieldset>
                </f:form>
            </div>
        </div> 
    </body>
</html>
