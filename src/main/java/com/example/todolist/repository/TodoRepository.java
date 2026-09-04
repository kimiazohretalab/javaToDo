package com.example.todolist.repository;

import com.example.todolist.config.JPAUtil;
import com.example.todolist.entity.Todo;

import javax.persistence.EntityManager;
import java.util.List;

public class TodoRepository {

    public Todo save(Todo todo) {

        EntityManager em = JPAUtil.getEntityManager();

        try {
            em.getTransaction().begin();

            em.persist(todo);

            em.getTransaction().commit();

            return todo;

        } catch (Exception e) {

            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            throw e;

        } finally {
            em.close();
        }
    }

    public List<Todo> findAll() {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            return em.createQuery(
                    "SELECT t FROM Todo t",
                    Todo.class
            ).getResultList();

        } finally {
            em.close();
        }
    }
}