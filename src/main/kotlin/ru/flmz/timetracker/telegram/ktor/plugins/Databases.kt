package ru.flmz.timetracker.telegram.ktor.plugins

import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database

fun Application.configureDatabases() {
    Database.connect(
        "jdbc:postgresql://localhost:5432/time-tracker-db",
        user = "postgres",
        password = "postgres"
    )
}