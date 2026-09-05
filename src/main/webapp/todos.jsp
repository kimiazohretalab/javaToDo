<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Todo List</title>
</head>

<body>

<h1>Todo List</h1>

<form method="post" action="${pageContext.request.contextPath}/todos">

    <input
            type="text"
            name="title"
            placeholder="Enter todo"
    >

    <button type="submit">
        Add
    </button>

</form>

<hr>

<ul>

    <c:forEach var="todo" items="${todos}">

        <li>

            <form
                    method="post"
                    action="${pageContext.request.contextPath}/todos/toggle"
                    style="display:inline;"
            >

                <input
                        type="hidden"
                        name="id"
                        value="${todo.id}"
                >

                <button type="submit">
                        ${todo.completed ? '☑' : '☐'}
                </button>

            </form>

                ${todo.title}

            <c:if test="${todo.completed}">
                - Completed
            </c:if>

            <c:if test="${!todo.completed}">
                - Not completed
            </c:if>

            <form
                    method="post"
                    action="${pageContext.request.contextPath}/todos/delete"
                    style="display:inline;"
            >

                <input
                        type="hidden"
                        name="id"
                        value="${todo.id}"
                >

                <button type="submit">
                    Delete
                </button>

            </form>

        </li>

    </c:forEach>

</ul>

</body>
</html>