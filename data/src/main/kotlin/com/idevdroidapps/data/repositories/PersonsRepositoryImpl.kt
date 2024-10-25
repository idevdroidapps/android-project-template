package com.idevdroidapps.data.repositories

import com.idevdroidapps.domain.entities.Person
import com.idevdroidapps.domain.interfaces.PersonsRepository

class PersonsRepositoryImpl: PersonsRepository {
    override fun allPersons(): List<Person> = listOf(Person("Keanu", 60))
}