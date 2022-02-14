package com.alisamir.basicbankingapp.ui.transfer

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.alisamir.basicbankingapp.data.BankDatabaseClient
import com.alisamir.basicbankingapp.pojo.Transfer
import com.alisamir.basicbankingapp.pojo.Users
import kotlinx.coroutines.launch

class ChooseUserViewModel(application: Application):AndroidViewModel(application) {
    private val context = application
    private val _users = MutableLiveData<List<Users>>()
    val users:LiveData<List<Users>>
    get() = _users

    fun getUsers(id:Int){
        viewModelScope.launch {
            val usersList = BankDatabaseClient.getInstance(context)?.bankDao()?.getOtherUsers(id)
            _users.value = usersList!!
        }
    }
    fun updateUserMoney(users: Users){
        viewModelScope.launch {
            BankDatabaseClient.getInstance(context)?.bankDao()?.updateUser(users)
        }
    }
    fun addTransaction(transfer: Transfer){
        viewModelScope.launch {
            BankDatabaseClient.getInstance(context)?.bankDao()?.addTransaction(transfer)
        }
    }
}