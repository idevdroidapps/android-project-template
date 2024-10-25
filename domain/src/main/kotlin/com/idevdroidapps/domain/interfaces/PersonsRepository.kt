package com.idevdroidapps.domain.interfaces

import com.idevdroidapps.domain.entities.Person

interface PersonsRepository{
    fun allPersons():List<Person>
}
