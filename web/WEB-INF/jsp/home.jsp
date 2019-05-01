<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="
              jlUTF-8">
        <title>CRUD</title>
        <%@include file="estilos.jsp" %> 
    </head>
    <body>
        <div class="container">
            <br>
            <h1>User List</h1>
            <br>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th scope="col">Id</th>
                        <th scope="col">Name</th>
                        <th scope="col">E-mail</th>
                        <th scope="col">Age</th>
                        <th scope="col">Date of birth</th>
                        <th scope="col">Country</th>
                        <th scope="col">Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${datos}" var="dato">
                        <tr>
                            <td><c:out value="${dato.id}"/></td>
                            <td><c:out value="${dato.nombre}"/></td>
                            <td><c:out value="${dato.email}"/></td>
                            <td><c:out value="${dato.edad}"/></td>
                            <td><c:out value="${dato.fechaNac}"/></td>
                            <td><c:out value="${dato.pais}"/></td>
                            <td><a href="<c:url value="edit.htm?id=${dato.id}"/>"><img  src="<c:url value="public/images/icons8-edit-25.png"/>"/></a>
                                <a href="<c:url value="delete.htm?id=${dato.id}"/>"><img  src="<c:url value="public/images/icons8-trash-can-25.png"/>"/></a>
                            </td>                       
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <div>
                <p>
                    <a href="<c:url value="add.htm"/>" class="btn btn-success"><img src="<c:url value="public/images/icons8-plus-math-filled-24.png"/>"/>Add</a>
                </p>
            </div>
        </div>
    </body>
</html>
