<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>

    <title>Departments</title>
    <link rel="stylesheet" href="main.css">
    <link href="https://fonts.googleapis.com/css?family=Roboto+Condensed" rel="stylesheet">


</head>

<body>
<h1> <div style="text-align: center;">List of departments</div></h1>

<h3 >
    <div style="text-align: center;">${message}</div>
</h3>

<div>
    <table border="1" cellpadding="5">

        <tr>
            <th width="200px">DepartmentName</th>
            <th>Control</th>
        </tr>

        <c:forEach var="department" items="${list}">
            <tr>

                <td> ${department.departmentName}</td>
                <td>

                    <div>
                        <FORM action="/servlet?id=${department.id}" method="POST" class="button">
                          <div class="hl">  <input type="submit" name="command" value="Remove department"
                                   onclick="if(!(confirm('Are you sure you want to delete this department?')))
                                       return false"
                                      class="button">

                            <input type="submit" name="command" value="Show employees" class="button">
                          </div>
                        </FORM>

                        <form method="POST"  class="button">
                            <input type="hidden" name="id" value="${department.id}">
                            <input type="submit" name="command" value="Edit department">
                            <input placeholder="input new name" type="text" name="newName">
                        </form>

                    </div>
                </td>
            </tr>

        </c:forEach>
    </table>
</div>

<div>
    <input type="button" value="New Department" class="button" onclick=showFieldAddDepartment()>
    <form method="post" id="add" class="button editDep">

        <p><input placeholder="input department's name" type="text" name="name"></p>
        <p><input type="submit" name="command" value="Add department"></p>

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
