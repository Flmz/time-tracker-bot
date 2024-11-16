package ru.flmz.timetracker.repository.impl

import org.jetbrains.exposed.sql.Database
import ru.flmz.timetracker.model.UserTask
import ru.flmz.timetracker.repository.UserTasksRepository
import ru.flmz.timetracker.repository.entity.UserTable

class UserTasksRepositoryImpl(private val database: Database) : UserTasksRepository {
    override fun insert(user: UserTable, task: UserTask): Unit {

    }

}