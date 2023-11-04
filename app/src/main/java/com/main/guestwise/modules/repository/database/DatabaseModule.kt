package com.main.guestwise.modules.repository.database

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DatabaseModule {

    @Binds
    fun bindDatabaseService(
        databaseServiceImpl: DatabaseServiceImpl
    ): DatabaseService
}