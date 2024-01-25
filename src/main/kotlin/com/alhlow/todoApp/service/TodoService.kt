package com.alhlow.todoApp.service

import com.alhlow.todoApp.model.Todo
import com.alhlow.todoApp.repository.PersonRepo
import com.alhlow.todoApp.repository.TodoRepo
import org.springframework.stereotype.Service
import java.util.*

@Service
class TodoService(private val todoRepo: TodoRepo, private val personRepo: PersonRepo) {

    fun getAllTodos(): List<Todo> {
        return todoRepo.findAll()
    }

    fun getTodoById(id: UUID): Optional<Todo> {
        return todoRepo.findById(id)
    }

    fun updateTodo(todo: Todo): Todo {
        validateTodoExists(todo.id)
        return todoRepo.save(todo)
    }

    fun saveNewTodo(todo: Todo): Todo {
        // Additional validation can be added here if needed
        return todoRepo.save(todo)
    }

    fun deleteTodo(id: UUID) {
        validateTodoExists(id)
        todoRepo.deleteById(id)
    }

    fun findAllTodosByPersonId(personId: UUID): List<Todo> {
        validatePersonExists(personId)
        val person = personRepo.findById(personId).get() // Safe to use get() since we already validated the existence
        return todoRepo.findAllByPerson(person)
    }

    private fun validateTodoExists(id: UUID) {
        if (!todoRepo.existsById(id)) {
            throw NoSuchElementException("Todo with ID $id does not exist.")
        }
    }

    private fun validatePersonExists(id: UUID) {
        if (!personRepo.existsById(id)) {
            throw NoSuchElementException("Person with ID $id does not exist.")
        }
    }
}
