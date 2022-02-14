package com.alisamir.basicbankingapp.ui.transactions

import android.app.Application
import androidx.lifecycle.*
import com.alisamir.basicbankingapp.data.BankDatabaseClient
import com.alisamir.basicbankingapp.pojo.Transfer
import kotlinx.coroutines.launch

class TransactionsViewModel (application: Application): AndroidViewModel(application){
    private val context =application
    private val _transactions = MutableLiveData<List<Transfer>>()
    val transactions:LiveData<List<Transfer>>
    get() = _transactions
    init {
        getAllTransactions()
    }
    fun getAllTransactions(){
        viewModelScope.launch {
            val transfersList = BankDatabaseClient.getInstance(context)?.bankDao()?.getAllTransfers()
            _transactions.value = transfersList!!
        }
    }
}