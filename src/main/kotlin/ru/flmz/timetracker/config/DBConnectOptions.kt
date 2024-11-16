package ru.flmz.timetracker.config

data class DBConnectOptions(
    val attempts: Int = 3,
    val delay: Long = 1000L
)
