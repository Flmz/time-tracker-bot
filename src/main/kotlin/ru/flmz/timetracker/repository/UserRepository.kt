package ru.flmz.timetracker.repository

import ru.flmz.timetracker.model.UserModel

interface UserRepository {
    suspend fun findByTgIdWithoutTasks(tgId: Long): UserModel?
    suspend fun findByTgId(tgId: Long): UserModel?
    suspend fun create(userModel: UserModel): UserModel?
    suspend fun update(userModel: UserModel)
}