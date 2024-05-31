package com.example.myapplication.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.api.getUsers
import com.example.myapplication.model.UserModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

class UserViewModel : ViewModel() {

    private var _users = MutableLiveData<List<UserModel>>(emptyList());
    private var _isLoading = MutableLiveData<Boolean>(false);
    private var _isError = MutableLiveData<Boolean>(false);
    private var _error = MutableLiveData<String>(null);
    val users: LiveData<List<UserModel>>
        get() = _users
    val isLoading: LiveData<Boolean>
        get() = _isLoading
    val isError: LiveData<Boolean>
        get() = _isError
    val error: LiveData<String>
        get() = _error

    init {
        viewModelScope.launch {
            getUsers(_users, _isLoading, _isError, _error)
        }
    }

    fun fetchUsers() {
        viewModelScope.launch {
            getUsers(_users, _isLoading, _isError, _error)
        }
    }
}
/*
private suspend fun getUsers():List<UserModel> {
    delay(2000);
    return listOf(
        UserModel("abhimanyu", "chakraborty", "ranchi", 36),
        UserModel("anomita", "chakraborty", "ranchi", 31),
        UserModel("User 2", "fdgffg", "ranchi", 32),
        UserModel("User 3", "fgh", "ranchi", 21),
        UserModel("User 4", "ghj", "ranchi", 45),
        UserModel("User 5", "ghjhgj", "ranchi", 32),
        UserModel("abhimanyu", "chakraborty", "ranchi", 36),
        UserModel("anomita", "chakraborty", "ranchi", 31),
        UserModel("User 2", "fdgffg", "ranchi", 32),
        UserModel("User 3", "fgh", "ranchi", 21),
        UserModel("User 4", "ghj", "ranchi", 45),
        UserModel("User 5", "ghjhgj", "ranchi", 32),
        UserModel("abhimanyu", "chakraborty", "ranchi", 36),
        UserModel("anomita", "chakraborty", "ranchi", 31),
        UserModel("User 2", "fdgffg", "ranchi", 32),
        UserModel("User 3", "fgh", "ranchi", 21),
        UserModel("User 4", "ghj", "ranchi", 45),
        UserModel("User 5", "ghjhgj", "ranchi", 32),
        UserModel("abhimanyu", "chakraborty", "ranchi", 36),
        UserModel("anomita", "chakraborty", "ranchi", 31),
        UserModel("User 2", "fdgffg", "ranchi", 32),
        UserModel("User 3", "fgh", "ranchi", 21),
        UserModel("User 4", "ghj", "ranchi", 45),
        UserModel("User 5", "ghjhgj", "ranchi", 32),
        UserModel("abhimanyu", "chakraborty", "ranchi", 36),
        UserModel("anomita", "chakraborty", "ranchi", 31),
        UserModel("User 2", "fdgffg", "ranchi", 32),
        UserModel("User 3", "fgh", "ranchi", 21),
        UserModel("User 4", "ghj", "ranchi", 45),
        UserModel("User 5", "ghjhgj", "ranchi", 32)
    )
}
 */