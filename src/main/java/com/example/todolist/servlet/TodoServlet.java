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

        request.setAttribute("todos", todos);

        request.getRequestDispatcher("/todos.jsp")
                .forward(request, response);
    }
    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        String title = request.getParameter("title");

        if (title != null && !title.trim().isEmpty()) {
            todoService.createTodo(title.trim());
        }

        response.sendRedirect(
                request.getContextPath() + "/todos"
        );
    }
}