<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <link
            rel="stylesheet"
            href="${pageContext.request.contextPath}/css/style.css"
    >
    <title>Todo List</title>
</head>

<body>

<div class="container">

    <div class="todo-card">

        <h1>Todo List</h1>

        <form
                method="post"
                action="${pageContext.request.contextPath}/todos"
                class="add-form"
        >

            <input
                    type="text"
                    name="title"
                    placeholder="Enter a new todo..."
            >

            <button
                    type="submit"
                    class="add-button"
            >
                Add
            </button>

        </form>

        <ul class="todo-list">

            <c:forEach var="todo" items="${todos}">

                <li class="todo-item">

                    <form
                            method="post"
                            action="${pageContext.request.contextPath}/todos/toggle"
                    >

                        <input
                                type="hidden"
                                name="id"
                                value="${todo.id}"
                        >

                        <button
                                type="submit"
                                class="toggle-button"
                        >
                                ${todo.completed ? '☑' : '☐'}
                        </button>

                    </form>

                    <span class="todo-title ${todo.completed ? 'completed' : ''}">
                            ${todo.title}
                    </span>

                    <span class="status">

                        <c:choose>

                            <c:when test="${todo.completed}">
                                Completed
                            </c:when>

                            <c:otherwise>
                                Not completed
                            </c:otherwise>

                        </c:choose>

                    </span>

                    <form
                            method="post"
                            action="${pageContext.request.contextPath}/todos/delete"
                    >

                        <input
                                type="hidden"
                                name="id"
                                value="${todo.id}"
                        >

                        <button
                                type="submit"
                                class="delete-button"
                        >
                            Delete
                        </button>

                    </form>

                </li>

            </c:forEach>

        </ul>

    </div>

</div>

</body>
</html>