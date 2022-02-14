package com.alisamir.basicbankingapp.ui.users

import android.app.Application
import androidx.lifecycle.*
import com.alisamir.basicbankingapp.data.BankDatabaseClient
import com.alisamir.basicbankingapp.pojo.Users
import kotlinx.coroutines.launch

class UsersViewModel(application: Application):AndroidViewModel(application) {
    private val context = application
    private val _users = MutableLiveData<List<Users>>()
    val users:LiveData<List<Users>>
    get() = _users
    init {
        getAllUsers()
    }
    fun getAllUsers(){
        viewModelScope.launch {
            val usersList = BankDatabaseClient.getInstance(context)?.bankDao()?.getAllUsers()
            _users.value = usersList!!
        }
    }
}