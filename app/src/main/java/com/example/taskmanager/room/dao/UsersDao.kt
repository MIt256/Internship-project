package com.example.taskmanager.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.taskmanager.room.entities.UserDbEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UsersDao {

    @Query("SELECT * FROM users WHERE email = :email")
    suspend fun getUserByEmail(email:String): UserDbEntity?
    @Insert
    suspend fun createAccount(userDbEntity: UserDbEntity)

//    @Query("SELECT * FROM users WHERE id = :userId")
//    fun getById(userId:String): Flow<UserDbEntity?>
}