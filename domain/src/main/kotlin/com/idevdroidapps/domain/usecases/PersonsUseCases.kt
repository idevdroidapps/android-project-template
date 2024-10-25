package com.idevdroidapps.domain.usecases

import com.idevdroidapps.domain.interfaces.PersonsRepository

class PersonsUseCases(private val personsRepository: PersonsRepository) {
    fun getAllPersons() = personsRepository.allPersons()
}