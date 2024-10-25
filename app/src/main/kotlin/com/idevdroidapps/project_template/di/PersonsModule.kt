package com.idevdroidapps.project_template.di

import com.idevdroidapps.data.repositories.PersonsRepositoryImpl
import com.idevdroidapps.domain.interfaces.PersonsRepository
import com.idevdroidapps.domain.usecases.PersonsUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class PersonsModule {

    @Singleton
    @Provides
    fun providePersonsRepository(): PersonsRepository = PersonsRepositoryImpl()

    @Provides
    fun provideVehicleUseCases(
        personsRepository: PersonsRepository,
    ): PersonsUseCases = PersonsUseCases(personsRepository)

}