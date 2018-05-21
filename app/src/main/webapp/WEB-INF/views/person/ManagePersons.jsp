<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page isELIgnored="false" %>

<html>
    <head>
        <title>Manage Persons</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<script src="/resources/jqueryscript.js"></script>
		<link rel="stylesheet" type="text/css" href="/resources/style.css"/>
    </head>

    <body>
        <c:set var="currentPage" value="${requestScope['javax.servlet.forward.request_uri']}" scope="request"/>
        <c:import url="../util/NavigationBar.jsp"/>
        <div class="mainWrapper">
        <h3>Manage Persons</h3>
        <hr/>
        <c:import url="../util/LanguageSelect.jsp"/>
        <c:set var="currentPage" value="${requestScope['javax.servlet.forward.request_uri']}"/>

        <form action="/person/managePersons" method="GET">
            <input type="text" name="lastNameFilter" placeholder="Filter by last name" value="${param.lastNameFilter}"/>
            <input type="submit" value="FILTER">
            <br/>

                <table border="1" width="100%">
                    <tr bgcolor="#77929b">
                        <th>ID</th>
                        <th><spring:message code="label.name.firstName"/></th>
                        <th><spring:message code="label.name.middleName"/></th>
                        <th><spring:message code="label.name.lastName"/></th>
                        <th><spring:message code="label.dateHired"/></th>
                        <th><spring:message code="label.gwa"/></th>
                        <th><spring:message code="label.roles"/></th>
                        <th><spring:message code="label.update"/></th>
                        <th><spring:message code="label.delete"/></th>
                    </tr>

                    <c:forEach items="${personList}" var="person">
                        <tr>
                            <td align="center">
                                <a href="/person/fullPersonDetails/${person.id}">${person.id}</a>
                            </td>
                            <td>${person.name.firstName}</td>
                            <td>${person.name.middleName}</td>
                            <td>${person.name.lastName}</td>
                            <td>${person.dateHired}</td>
                            <td>${person.gwa}</td>
                            <td>${person.roles}</td>
                            <td align="center">
                                <a href="/person/addOrUpdatePerson?personId=${person.id}"><spring:message code="label.update"/></a>
                            </td>
                            <td align="center">
                                <a href="/person/deletePerson/${person.id}"><spring:message code="label.delete"/></a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
                <br>

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
            </div>
        </body>
    </html>
