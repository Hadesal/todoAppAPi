package com.alhlow.todoApp.service

import com.alhlow.todoApp.model.Person
import com.alhlow.todoApp.repository.PersonRepo
import org.springframework.stereotype.Service
import java.util.*
@Service
class PersonService(private val personRepo: PersonRepo) {

    fun getAllPersons():List<Person>{
        return personRepo.findAll()
    }

    fun getPersonByID(id: UUID): Optional<Person> {
        return personRepo.findById(id)
    }
    fun updatePerson(person: Person): Person {
        validatePersonExists(person.id)
        return personRepo.save(person)
    }

    fun saveNewPerson(person: Person): Person {
        validatePersonDoesNotExistByName(person.name)

        return personRepo.save(person)
    }

    fun deletePersonById(id: UUID) {
        validatePersonExists(id)
        personRepo.deleteById(id)
    }

    private fun validatePersonExists(id: UUID) {
        if (!personRepo.existsById(id)) {
            throw NoSuchElementException("Person with ID $id does not exist.")
        }
    }

    private fun validatePersonDoesNotExistByName(name: String) {
        if (personRepo.existsByName(name)) {
            throw IllegalArgumentException("Person with name $name already exists.")
        }
    }
}