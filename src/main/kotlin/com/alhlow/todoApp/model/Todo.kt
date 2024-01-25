package com.alhlow.todoApp.model

import jakarta.persistence.*
import java.util.Optional
import java.util.UUID
@Entity
data class Todo(
    @Id
    @GeneratedValue
    val id:UUID = UUID.randomUUID(),
    val title:String,
    val description: String,
    val completed: String,
    @ManyToOne
    @JoinColumn(name = "person_id")
    val person:Person? = null
)
