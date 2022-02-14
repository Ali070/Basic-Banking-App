package com.alisamir.basicbankingapp.pojo

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "Transfer",foreignKeys = arrayOf(ForeignKey(entity = Users::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("userId"))
)
)
data class Transfer(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val userId:Int,
    val amount:Double,
    val fromUser:String,
    val toUser:String
)
