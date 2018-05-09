<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>

<html>
    <head>
        <title>Manage Persons</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script>
            $(document).ready(function() {
                $("[name=delete-button]").click(function() {
                    $("#test").show();
                    var idNum=$(this).attr("id");
                    var decision=confirm("Are you sure?");
                    if (decision) {
                        $.post("/DeletePerson", {id : idNum}, function() {
                            location.reload();
                        });
                    }

                });
            });
        </script>
    </head>

    <body>
        <h3>Manage Persons</h3>
        <hr/>

        <form action="/managePersons.htm" method="GET">
            <input type="text" name="lastNameFilter" placeholder="Filter by last name" value="${param.lastNameFilter}"/>
            <input type="submit" value="FILTER">

                <table border="1" width="100%">
                    <tr bgcolor="#77929b">
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

                    <c:forEach items="${personList}" var="person">
                        <tr>
                            <td>
                                <a href="/fullPersonDetails.htm?personId=${person.id}">${person.id}</a>
                            </td>
                            <td>${person.name.firstName}</td>
                            <td>${person.name.middleName}</td>
                            <td>${person.name.lastName}</td>
                            <td>${person.dateHired}</td>
                            <td>${person.gwa}</td>
                            <td>${person.roles}</td>
                            <td>
                                <a href="/addOrUpdatePerson.htm?personId=${person.id}">Update Person</a>
                            </td>
                            <td>
                                <a href="/deletePerson.htm?personId=${person.id}">Delete Person</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>

                Sort by:
                <select name="orderBy">
                    <option value="id" ${param.orderBy == "id" ? "selected" : ""}>ID</option>
                    <option value="name.lastName" ${param.orderBy == "name.lastName" ? "selected" : ""}>Last Name</option>
                    <option value="dateHired" ${param.orderBy == "dateHired" ? "selected" : ""}>Date Hired</option>
                    <option value="gwa" ${param.orderBy == "gwa" ? "selected" : ""}>GWA</option>
                </select>
                Order type:
                <select name="orderType">
                    <option value="asc" ${param.orderType == "asc" ? "selected" : ""}>Ascending</option>
                    <option value="desc" ${param.orderType == "desc" ? "selected" : ""}>Descending</option>
                </select>
                <input type="submit" value="SORT"/>
            </form>
            <hr/>
            <a href="/addOrUpdatePerson.htm">Add Person</a> <br>
            <a href="/manageRoles.htm">Manage Roles</a> <br>
            <a href="/index.jsp">HOME</a>
        </body>
    </html>
