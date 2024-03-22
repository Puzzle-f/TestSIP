package com.example.testsip.repository.usersrepo

import android.content.Context
import com.example.testsip.data.UsersItem
import kotlinx.coroutines.flow.Flow

class UsersRepositoryImpl(private val userDataRepository: UserDataRepository)
     {
    suspend fun getUsers(context: Context) =
         userDataRepository.getUsers(context)

}