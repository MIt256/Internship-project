package com.example.taskmanager.room

import com.example.taskmanager.dto.NetworkResult
import com.example.taskmanager.room.dao.UsersDao
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class RoomRepository(private val usersDao: UsersDao) {

    suspend fun createUser(){

    }

    suspend fun getUserByEmail(email: String) = flow {
        emit(NetworkResult.Loading(true))
        val response = usersDao.getUserByEmail(email)
        if (response != null) {
            emit(NetworkResult.Success(response.toUserSettings()))
        }
    }.catch { e ->
        emit(NetworkResult.Failure(e.message ?: "Unknown Error"))
    }
}