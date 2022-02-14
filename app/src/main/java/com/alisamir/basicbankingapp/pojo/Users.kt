package com.alisamir.basicbankingapp.pojo

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "User")
data class Users(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val name:String,
    val email:String,
    var current_balance:Double,
    val gender:String,
    val accountNumber:String,
    val IFSC_Code:String
    ):Parcelable
