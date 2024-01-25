package com.alhlow.todoApp.repository

import com.alhlow.todoApp.model.Person
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface PersonRepo:JpaRepository<Person,UUID>{
    fun existsByName(name:String):Boolean
}