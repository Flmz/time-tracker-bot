package ru.flmz.timetracker.repository.mapper

import org.jetbrains.exposed.sql.ResultRow
import ru.flmz.timetracker.model.UserTask
import ru.flmz.timetracker.repository.entity.UserTasksTable

fun ResultRow.toUserTask(): UserTask = UserTask(
    id = this[UserTasksTable.id].value,
    trackName = this[UserTasksTable.trackName],
    status = this[UserTasksTable.status],
)