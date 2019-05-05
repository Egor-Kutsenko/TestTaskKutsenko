<%--
  Created by IntelliJ IDEA.
  User: Егор
  Date: 4/8/2019
  Time: 11:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>


<html>
<head>
    <title>Employees</title>
    <link rel="stylesheet" href="main.css">
</head>
<body>

<form method="post" action="/servlet">
    <input type="submit" name="command" value="Back">
</form>

<div>
    <table border="1" cellpadding="5">

        <tr>

            <th>Date</th>
            <th>Mail</th>
            <th>Full Name</th>
            <th>Control</th>
        </tr>

        <c:forEach var="employee" items="${employeeList}">

            <tr>
                <td> ${employee.date}</td>
                <td> ${employee.email}</td>
                <td> ${employee.fullName}</td>
                <td>
                    <div>
                        <FORM action="/servlet?id=${employee.id}" method="POST">
                            <INPUT type="submit" name="command" value="Fire an employee"
                                   onclick="if(!(confirm('Are you sure you want to delete this employee?')))
                                   return false">
                            <input type="hidden" name="department_id" value="${employee.departmentName}">

                            <div>
                                <input type="submit" name="command" value="Transfer employee">
                                <c:forEach var="department" items="${list}">
                                    <input type="radio" id="contactChoice1"
                                           name="new department" value="${department.departmentName}">
                                    <input type="hidden" name="id" value="${employee.id}">
                                    <input type="hidden" name="id" value="${employee.departmentName}">
                                    <label for="contactChoice1">${department.departmentName}</label>

                                </c:forEach>

                            </div>
                            <div>

                                <INPUT type="submit" name="command" value="Edit mail">
                                <INPUT type="text" placeholder="input new email" name="email">
                            </div>
                        </FORM>

                    </div>
                </td>
            </tr>

        </c:forEach>

    </table>
</div>

<div>
    <input type="button" value="New employee" class="button" onclick=showFieldAddDepartment()>

    <form method="post" id="add" class="editDep button">

        <INPUT type="text" placeholder="enter email  (\w+@\w+.\w+)" name="email">
        <INPUT type="text" placeholder="enter name  ([A-Z][a-z]{1,20})" name="name">
        <input type="hidden" name="department_id" value="${department_id}">
        <INPUT type="submit" name="command" value="Add employee">

    </form>
</div>

</body>
</html>

<script>
    function showFieldAddDepartment() {
        var element = document.getElementById("add");
        element.classList.remove("editDep");
    }

</script>
