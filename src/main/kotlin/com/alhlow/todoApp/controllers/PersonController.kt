package com.alhlow.todoApp.controllers

import com.alhlow.todoApp.model.Person
import com.alhlow.todoApp.service.PersonService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/person")
class PersonController(private val personService: PersonService) {

    @GetMapping
    fun getAllPersons(): ResponseEntity<List<Person>> {
        val persons = personService.getAllPersons()
        return if (persons.isEmpty()) {
            ResponseEntity.ok(emptyList())
        } else {
            ResponseEntity.ok(persons)
        }
    }

    @PostMapping
    fun addNewPerson(@RequestBody newPerson: Person): ResponseEntity<Person> {
        val savedPerson = personService.saveNewPerson(newPerson)
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPerson)
    }

    @GetMapping("/{id}")
    fun getPersonById(@PathVariable id: UUID): ResponseEntity<Person> {
        val person = personService.getPersonByID(id).orElse(null)
        return if (person != null) {
            ResponseEntity.ok(person)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PutMapping("/{id}")
    fun updatePerson(@RequestBody updatedPerson: Person): ResponseEntity<Person> {
        val updatedPersonEntity = personService.updatePerson(updatedPerson)
        return ResponseEntity.ok(updatedPersonEntity)
    }

    @DeleteMapping("/{id}")
    fun deletePersonById(@PathVariable id: UUID): ResponseEntity<Unit> {
        personService.deletePersonById(id)
        return ResponseEntity.noContent().build()
    }
}
