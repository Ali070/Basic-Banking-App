package com.alisamir.basicbankingapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.alisamir.basicbankingapp.pojo.Transfer
import com.alisamir.basicbankingapp.pojo.Users

@Database(entities = [Users::class,Transfer::class],version = 1,exportSchema = true)
abstract class BankDatabaseClient:RoomDatabase() {
    abstract fun bankDao():BankDao

    companion object{
        private var instance:BankDatabaseClient? = null

        fun getInstance(context: Context):BankDatabaseClient?{
            synchronized(this){
                if (instance == null){
                    instance = Room.databaseBuilder(context.applicationContext,BankDatabaseClient::class.java,"Bank")
                        .createFromAsset("databases/bank_database.db")
                        .build()
                }
                return instance
            }
        }
    }
}