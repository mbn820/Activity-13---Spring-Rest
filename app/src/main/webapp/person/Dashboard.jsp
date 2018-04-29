<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>

<html>
    <head>
        <title>Persons Dashboard</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script>
            $(document).ready(function() {
                $("#delete-button").click(function() {
                    var idNum = $(this).attr("name");
                    $.post("/DeletePerson", {id : idNum}, function() {
                        location.reload();
                    });

                });
            });
        </script>
    </head>

    <body>
        <h3>DASHBOARD</h3>
        <hr/>

        <form action = "Dashboard" method = "GET">
            <div>
                <input type = "text" name = "lastNameFilter" placeholder = "Filter by last name" value = "${param.lastNameFilter}"/>
                <input type = "submit" value = "FILTER">
            </div>

            <div>
                Sort by:
                <select name = "sortBy">
                    <option value = "id" ${param.sortBy == "id" ? "selected" : ""}>ID</option>
                    <option value = "name.lastName" ${param.sortBy == "name.lastName" ? "selected" : ""}>Last Name</option>
                    <option value = "dateHired" ${param.sortBy == "dateHired" ? "selected" : ""}>Date Hired</option>
                    <option value = "gwa" ${param.sortBy == "gwa" ? "selected" : ""}>GWA</option>
                </select>
                Order:
                <select name = "orderBy">
                    <option value = "asc" ${param.orderBy == "asc" ? "selected" : ""}>Ascending</option>
                    <option value = "des" ${param.orderBy == "des" ? "selected" : ""}>Descending</option>
                </select>
                <input type = "submit" value = "SORT"/>
            </div>
        </form>

        <div>
            <table border = "1">
                <tr bgcolor = "#77929b">
                    <th>ID</th>
                    <th>First Name</th>
                    <th>Middle Name</th>
                    <th>Last Name</th>
                    <th>Date Hired</th>
                    <th>GWA</th>
                    <th>Roles</th>
                    <th>Update</th>
                    <th>Delete</th>
                </tr>

                <c:forEach items = "${personList}" var = "person">
                    <tr>
                        <th>
                            <a href = "/person/FullPersonDetails?id={person.id}">${person.id}</a>
                        </th>
                        <th>${person.name.firstName}</th>
                        <th>${person.name.middleName}</th>
                        <th>${person.name.lastName}</th>
                        <th>${person.dateHired}</th>
                        <th>${person.gwa}</th>
                        <th>${person.roles}</th>
                        <th>
                            <a href = "/person/UpdatePerson?id=${person.id}">Update Person</a>
                        </th>
                        <th>
                            <input type = "button" value = "DELETE" id = "delete-button" name = "${person.id}">
                        </th>
                    </tr>
                </c:forEach>
            </table>
        </div>


    </body>
</html>
