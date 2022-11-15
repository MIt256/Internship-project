package com.example.taskmanager.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.taskmanager.room.entities.UserDbEntity

@Dao
interface UsersDao {

    @Query("SELECT * FROM users WHERE email = :email")
    suspend fun getUserByEmail(email: String): UserDbEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createAccount(userDbEntity: UserDbEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllUsers(users: List<UserDbEntity>)

//    @Query("SELECT * FROM users WHERE id = :userId")
//    fun getById(userId:String): Flow<UserDbEntity?>
}