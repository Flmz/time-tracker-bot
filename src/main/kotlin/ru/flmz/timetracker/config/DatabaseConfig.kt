package ru.flmz.timetracker.config

import dev.inmo.kslog.common.e
import dev.inmo.kslog.common.logger
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transactionManager
import org.postgresql.Driver
import java.sql.Connection

data class DatabaseConfig(
    val url: String = "jdbc:postgresql://localhost:5434/time-tracker-db",
    val driver: String = Driver::class.qualifiedName!!,
    val username: String = "postgres",
    val password: String = "postgres",
    val reconnectOptions: DBConnectOptions? = DBConnectOptions()
) {

    val database: Database = (0 until (reconnectOptions?.attempts ?: 1)).firstNotNullOfOrNull {
        runCatching {
            Database.connect(
                url,
                driver,
                username,
                password
            ).also {
                it.transactionManager.defaultIsolationLevel =
                    Connection.TRANSACTION_READ_COMMITTED
                it.connector().close()
            }
        }.onFailure {
            logger.e(it)
            Thread.sleep(reconnectOptions?.delay ?: return@onFailure)
        }.getOrNull()
    } ?: error("Unable to create database")
}

