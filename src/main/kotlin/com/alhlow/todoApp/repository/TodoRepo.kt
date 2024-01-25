package com.alhlow.todoApp.repository

import com.alhlow.todoApp.model.Person
import com.alhlow.todoApp.model.Todo
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface TodoRepo:JpaRepository<Todo, UUID>{
    fun findAllByPerson(person: Person): List<Todo>

}