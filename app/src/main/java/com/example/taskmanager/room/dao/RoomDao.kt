package com.example.taskmanager.room.dao

import androidx.room.*
import com.example.taskmanager.room.entities.*
import kotlinx.coroutines.flow.Flow

@Dao
abstract class RoomDao {

    @Query("SELECT * FROM tasks WHERE owner_id = :ownerId ")
    abstract fun getTasks(ownerId: String): Flow<List<TaskDbEntity?>>

    //todo check
    @Query("SELECT * FROM users, task_members WHERE users.id = task_members.user_id AND task_members.task_id = :taskId")
    abstract fun getTaskMembers(taskId: String): Flow<List<UserDbEntity?>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun addTask(task: TaskDbEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun addAllTasks(tasks: List<TaskDbEntity>)

    @Transaction
    @Query("SELECT * FROM tasks WHERE owner_id = :ownerId")
    abstract suspend fun getTaskWithMembers(ownerId: String): List<TaskWithMembers>

    @Query("SELECT * FROM tasks WHERE owner_id = :ownerId")
    abstract suspend fun getUserTasks(ownerId: String): List<TaskDbEntity>


    @Query("SELECT * FROM users WHERE email = :email")
    abstract suspend fun getUserByEmail(email: String): UserDbEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun createAccount(userDbEntity: UserDbEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun addAllUsers(users: List<UserDbEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun addUser(user: UserDbEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun addTaskMemberCrossRef(item: TaskMemberCrossRef)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun addAllTaskMemberCrossRefs(items: List<TaskMemberCrossRef>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun addProject(project: ProjectDbEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun addAllProjects(projects: List<ProjectDbEntity>)

    @Query("SELECT * FROM projects WHERE owner_id = :ownerId")
    abstract suspend fun getProjects(ownerId: String): List<ProjectDbEntity>

    @Transaction
    open suspend fun addAllSyncInfo(tasks: List<TaskDbEntity>, crossRefs: List<TaskMemberCrossRef>, projects: List<ProjectDbEntity>, users: List<UserDbEntity>){
        addAllUsers(users)
        addAllProjects(projects)
        addAllTasks(tasks)
        addAllTaskMemberCrossRefs(crossRefs)
    }

}