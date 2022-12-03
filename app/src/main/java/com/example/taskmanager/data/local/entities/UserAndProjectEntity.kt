package com.example.taskmanager.data.local.entities

import androidx.room.Embedded
import androidx.room.Relation

data class UserAndProjectEntity(
    @Embedded val user: UserDbEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "ownerId"
    )
    val project: ProjectDbEntity
){
    //todo add mapper func
}
