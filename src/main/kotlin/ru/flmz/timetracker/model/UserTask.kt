package ru.flmz.timetracker.model

data class UserTask(
    val id: Long? = null,
    val status: TaskStatus = TaskStatus.CREATED,
    val trackName: String
)

