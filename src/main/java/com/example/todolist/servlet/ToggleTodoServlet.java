package com.example.todolist.servlet;

import com.example.todolist.service.TodoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/todos/toggle")
public class ToggleTodoServlet extends HttpServlet {

    private TodoService todoService;

    @Override
    public void init() throws ServletException {
        todoService = new TodoService();
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        String idParam = request.getParameter("id");

        if (idParam != null) {

            try {

                Long id = Long.parseLong(idParam);

                todoService.toggleTodo(id);

            } catch (NumberFormatException e) {

                response.sendError(
                        HttpServletResponse.SC_BAD_REQUEST,
                        "Invalid Todo ID"
                );

                return;
            }
        }

        response.sendRedirect(
                request.getContextPath() + "/todos"
        );
    }
}
