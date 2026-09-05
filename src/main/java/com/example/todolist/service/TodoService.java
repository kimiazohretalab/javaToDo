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

    public void toggleTodo(Long id) {

        Todo todo = repository.findById(id);

        if (todo == null) {
            return;
        }

        todo.setCompleted(!todo.isCompleted());

        repository.update(todo);
    }

    public void deleteTodo(Long id) {
        repository.delete(id);
    }
}