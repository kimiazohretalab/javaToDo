package com.example.todolist.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.example.todolist.entity.Todo;
import com.example.todolist.service.TodoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet({
        "/todos"
})
public class TodoServlet extends HttpServlet {

    private TodoService todoService;

    @Override
    public void init() throws ServletException {
        todoService = new TodoService();
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        List<Todo> todos = todoService.getAllTodos();

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Todo List</title>");
        out.println("</head>");

        out.println("<body>");

        out.println("<h1>Todo List</h1>");
        out.println("<form method='post' action='todos'>");

        out.println("<input type='text' name='title' placeholder='Enter todo'>");

        out.println("<button type='submit'>Add</button>");

        out.println("</form>");

        out.println("<ul>");

        for (Todo todo : todos) {

            out.println("<li>");

            out.println("<form method='post' action='todos/toggle' style='display:inline;'>");

            out.println("<input type='hidden' name='id' value='" + todo.getId() + "'>");

            out.println("<button type='submit'>");

            if (todo.isCompleted()) {
                out.println("☑");
            } else {
                out.println("☐");
            }

            out.println("</button>");

            out.println("</form>");

            out.println(" " + todo.getTitle());

            if (todo.isCompleted()) {
                out.println(" - Completed");
            } else {
                out.println(" - Not completed");
            }

            out.println(
                    "<form method='post' " +
                            "action='" + request.getContextPath() + "/todos/delete' " +
                            "style='display:inline;'>"
            );

            out.println(
                    "<input type='hidden' name='id' value='" +
                            todo.getId() +
                            "'>"
            );

            out.println("<button type='submit'>Delete</button>");

            out.println("</form>");

            out.println("</li>");
        }

        out.println("</ul>");

        out.println("</body>");
        out.println("</html>");
    }
    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        String path = request.getPathInfo();

        if (request.getRequestURI().endsWith("/toggle")) {

            String idParam = request.getParameter("id");

            if (idParam != null) {

                Long id = Long.parseLong(idParam);

                todoService.toggleTodo(id);
            }

        } else {

            String title = request.getParameter("title");

            if (title != null && !title.trim().isEmpty()) {

                todoService.createTodo(title.trim());
            }
        }

        response.sendRedirect(
                request.getContextPath() + "/todos"
        );
    }
}