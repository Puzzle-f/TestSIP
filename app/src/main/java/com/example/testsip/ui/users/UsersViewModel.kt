package com.example.testsip.ui.users

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testsip.data.UsersItem
import com.example.testsip.usecases.GetUsersUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UsersViewModel(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {

    private val dispatcherIO = Dispatchers.IO
    private var _usersList = MutableStateFlow<List<UsersItem>>(emptyList())
    val usersList: StateFlow<List<UsersItem>> = _usersList.asStateFlow()

    fun emitDataUsers(context: Context) {
        viewModelScope.launch(dispatcherIO) {
            _usersList.emit(getData(context))
        }
    }

    private suspend fun getData(context: Context) = getUsersUseCase.getUsers(context)


}