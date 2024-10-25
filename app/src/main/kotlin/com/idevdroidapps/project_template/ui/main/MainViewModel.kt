package com.idevdroidapps.project_template.ui.main

import androidx.lifecycle.ViewModel
import com.idevdroidapps.domain.usecases.PersonsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val personsUseCases: PersonsUseCases) :
    ViewModel() {

    fun getAllPersons() = personsUseCases.getAllPersons()

}