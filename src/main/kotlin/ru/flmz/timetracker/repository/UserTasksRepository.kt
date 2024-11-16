package ru.flmz.timetracker.repository

import ru.flmz.timetracker.model.UserTask
import ru.flmz.timetracker.repository.entity.UserTable

interface UserTasksRepository {
    fun insert(user: UserTable, task: UserTask)
}