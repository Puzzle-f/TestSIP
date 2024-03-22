package com.example.testsip.usecases

import android.content.Context
import com.example.testsip.repository.usersrepo.UsersRepositoryImpl

class GetUsersUseCase(val usersRepositoryImpl: UsersRepositoryImpl) {
    suspend fun getUsers(context: Context) = usersRepositoryImpl.getUsers(context)
}