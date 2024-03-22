package com.example.testsip.di

import com.example.testsip.repository.usersrepo.UserDataRepository
import com.example.testsip.repository.usersrepo.UsersRepositoryImpl
import com.example.testsip.ui.users.UsersViewModel
import com.example.testsip.ui.users.details.DetailsViewModel
import com.example.testsip.usecases.GetUsersUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val module = module {
    single { UserDataRepository() }
    single { UsersRepositoryImpl(userDataRepository = get()) }
    single { GetUsersUseCase(usersRepositoryImpl = get()) }
    viewModel { UsersViewModel(getUsersUseCase = get()) }
    viewModel { DetailsViewModel() }
}