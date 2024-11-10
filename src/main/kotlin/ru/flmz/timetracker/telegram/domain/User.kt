package ru.flmz.timetracker.telegram.domain

data class User(
    val tgId: Long,
    val firstName: String?,
    val secondName: String?
)