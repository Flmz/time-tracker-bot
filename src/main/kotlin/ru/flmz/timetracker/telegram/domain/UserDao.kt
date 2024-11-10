package ru.flmz.timetracker.telegram.domain

import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

object UserTable : IdTable<Long>("users") {
    override val id: Column<EntityID<Long>> = long("tg_id").uniqueIndex().entityId()
    val firstName = varchar("first_name", 1500)
    val secondName = varchar("sur_name", 1500)
}

class UserDao(id: EntityID<Long>) : LongEntity(id) {
    companion object : EntityClass<Long, UserDao>(UserTable)

    var tgId by UserTable.id
    var firstName by UserTable.firstName
    var secondName by UserTable.secondName
}

suspend fun <T> suspendTransaction(block: Transaction.() -> T): T =
    newSuspendedTransaction(
        db = Database.connect(
            "jdbc:postgresql://localhost:5432/time-tracker-db",
            user = "postgres",
            password = "postgres"
        ),
        context = Dispatchers.IO, statement = block
    )

fun UserDao.daoToModel() = User(
    tgId = this.tgId.value,
    firstName = this.firstName,
    secondName = this.secondName
)