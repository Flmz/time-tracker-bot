package ru.flmz.timetracker.repository

import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import ru.flmz.timetracker.config.DatabaseConfig

val dbConfig = DatabaseConfig()
suspend fun <T> suspendTransaction(block: Transaction.() -> T?): T? =
    newSuspendedTransaction(
        db = dbConfig.database,
        context = Dispatchers.IO
    ) {
        addLogger(StdOutSqlLogger)
        block()
    }