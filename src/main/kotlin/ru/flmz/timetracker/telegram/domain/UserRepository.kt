package ru.flmz.timetracker.telegram.domain

interface UserRepository {
    suspend fun findById(tgId: Long): User?
}