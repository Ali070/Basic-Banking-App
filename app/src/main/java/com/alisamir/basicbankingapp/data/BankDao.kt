package com.alisamir.basicbankingapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.alisamir.basicbankingapp.pojo.Transfer
import com.alisamir.basicbankingapp.pojo.Users

@Dao
interface BankDao {
    @Query("select * from User")
    suspend fun getAllUsers():List<Users>

    @Query("select * from Transfer")
    suspend fun getAllTransfers():List<Transfer>

    @Query("select * from User where id != (:id) ")
    suspend fun getOtherUsers(id:Int):List<Users>

    @Insert
    suspend fun addTransaction(transfer: Transfer)

    @Update
    suspend fun updateUser(users: Users)


}