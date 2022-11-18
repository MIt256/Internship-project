package com.example.taskmanager.room.dao

import androidx.room.*
import com.example.taskmanager.room.entities.*
import kotlinx.coroutines.flow.Flow

@Dao
interface RoomDao {

    @Query("SELECT * FROM tasks WHERE owner_id = :ownerId ")
    fun getTasks(ownerId: String): Flow<List<TaskDbEntity?>>

    //todo check
    @Query("SELECT * FROM users, task_members WHERE users.id = task_members.user_id AND task_members.task_id = :taskId")
    fun getTaskMembers(taskId: String): Flow<List<UserDbEntity?>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTask(task: TaskDbEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllTasks(tasks: List<TaskDbEntity>)

    @Transaction
    @Query("SELECT * FROM tasks WHERE owner_id = :ownerId")
    suspend fun getTaskWithMembers(ownerId: String): List<TaskWithMembers>


    @Query("SELECT * FROM users WHERE email = :email")
    suspend fun getUserByEmail(email: String): UserDbEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createAccount(userDbEntity: UserDbEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllUsers(users: List<UserDbEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(user: UserDbEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTaskMemberCrossRef(item: TaskMemberCrossRef)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProject(project: ProjectDbEntity)

    @Query("SELECT * FROM projects WHERE owner_id = :ownerId ")
    fun getProjects(ownerId: String): Flow<List<ProjectDbEntity?>>


}