package ru.flmz.timetracker.repository.entity

import org.jetbrains.exposed.dao.id.IdTable
import ru.flmz.timetracker.model.TaskStatus

object UserTasksTable : IdTable<Long>("user_tasks") {
    override val id = long("id").entityId()
    val userId = reference("user_id", UserTable)
    val status = enumerationByName<TaskStatus>("status", 100)
    val trackName = varchar("track_name", 100)
}