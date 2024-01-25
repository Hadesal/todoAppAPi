package com.alhlow.todoApp.controllers

import com.alhlow.todoApp.model.Todo
import com.alhlow.todoApp.service.TodoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/todo")
class TodoController(private val todoService: TodoService) {

    @GetMapping
    fun getAllTodos(): ResponseEntity<List<Todo>> {
        val todos = todoService.getAllTodos()
        return if (todos.isEmpty()) {
            ResponseEntity.ok(emptyList())
        } else {
            ResponseEntity.ok(todos)
        }
    }

    @PostMapping
    fun addNewTodo(@RequestBody newTodo: Todo): ResponseEntity<Todo> {
        val savedTodo = todoService.saveNewTodo(newTodo)
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTodo)
    }

    @GetMapping("/byPersonId/{personId}")
    fun getAllTodosByPersonId(@PathVariable personId: UUID): ResponseEntity<List<Todo>> {
        val todos = todoService.findAllTodosByPersonId(personId)
        return if (todos.isEmpty()) {
            ResponseEntity.ok(emptyList())
        } else {
            ResponseEntity.ok(todos)
        }
    }

    @GetMapping("/{id}")
    fun getTodoById(@PathVariable id: UUID): ResponseEntity<Todo> {
        val todo = todoService.getTodoById(id).orElse(null)
        return if (todo != null) {
            ResponseEntity.ok(todo)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PutMapping("/{id}")
    fun updateTodo( @RequestBody updatedTodo: Todo): ResponseEntity<Todo> {
        val updatedTodoEntity = todoService.updateTodo(updatedTodo)
        return ResponseEntity.ok(updatedTodoEntity)
    }

    @DeleteMapping("/{id}")
    fun deleteTodoById(@PathVariable id: UUID): ResponseEntity<Unit> {
        todoService.deleteTodo(id)
        return ResponseEntity.noContent().build()
    }
}
