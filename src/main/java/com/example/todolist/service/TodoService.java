package com.example.todolist.service;

import com.example.todolist.entity.Todo;
import com.example.todolist.repository.TodoRepository;

import java.util.List;

public class TodoService {

    private final TodoRepository repository;

    public TodoService() {
        this.repository = new TodoRepository();
    }

    public Todo createTodo(String title) {
        Todo todo = new Todo(title);

        return repository.save(todo);
    }

    public List<Todo> getAllTodos() {
        return repository.findAll();
    }
}