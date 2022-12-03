package com.example.taskmanager.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.taskmanager.data.local.entities.TaskMemberCrossRef
import com.example.taskmanager.data.local.entities.UserDbEntity

@Dao
abstract class UserDao {
    @Query("SELECT * FROM users WHERE email = :email")
    abstract suspend fun getUserByEmail(email: String): UserDbEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun createAccount(userDbEntity: UserDbEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun addAllUsers(users: List<UserDbEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun addUser(user: UserDbEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun addTaskMemberCrossRef(item: TaskMemberCrossRef)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun addAllTaskMemberCrossRefs(items: List<TaskMemberCrossRef>)
}