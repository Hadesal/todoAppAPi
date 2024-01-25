package com.alhlow.todoApp.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import java.util.UUID
@Entity
data class Person(@Id
                  @GeneratedValue
                  val id:UUID = UUID.randomUUID(),
                  val name:String,
                  @OneToMany(mappedBy = "person")
                  val todos: List<Todo>)
