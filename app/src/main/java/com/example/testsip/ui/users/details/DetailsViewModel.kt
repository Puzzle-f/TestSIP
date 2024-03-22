package com.example.testsip.ui.users.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testsip.data.UsersItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailsViewModel: ViewModel() {

    private val dispatcherIO = Dispatchers.IO
    private var _user = MutableStateFlow<UsersItem?>(null)
    val user: StateFlow<UsersItem?> = _user.asStateFlow()

    fun emitDetailsUser(usersItem: UsersItem){
        viewModelScope.launch (dispatcherIO) {
            _user.emit(usersItem)
        }
    }
}