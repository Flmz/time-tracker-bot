package ru.flmz.timetracker.repository.mapper

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.statements.InsertStatement
import org.jetbrains.exposed.sql.statements.UpdateStatement
import ru.flmz.timetracker.model.UserModel
import ru.flmz.timetracker.repository.entity.UserTable
import ru.flmz.timetracker.repository.entity.UserTasksTable

fun Map.Entry<Column<EntityID<Long>>, List<ResultRow>>.toUser(): UserModel = let {
    val userResultRow = this.value.first()
    UserModel(
        id = userResultRow[UserTable.id].value,
        tgId = userResultRow[UserTable.tgId],
        firstName = userResultRow.getOrNull(UserTable.firstName),
        surName = userResultRow.getOrNull(UserTable.surName),
        tgUserName = userResultRow.getOrNull(UserTable.tgUserName),
        languageCode = userResultRow.getOrNull(UserTable.languageCode),
        tasks = this.value
            .filter { it.getOrNull(UserTasksTable.id) != null }
            .map { it.toUserTask() },
    )
}

fun ResultRow.toUserWithoutAggregates(): UserModel = UserModel(
    tgId = this[UserTable.tgId],
    firstName = this[UserTable.firstName],
    surName = this[UserTable.surName],
    tgUserName = this[UserTable.tgUserName],
    languageCode = this[UserTable.languageCode],
)

fun ResultRow.toUserWithJoin(): UserModel = UserModel(
    id = this[UserTable.id].value,
    tgId = this[UserTable.tgId],
    firstName = this[UserTable.firstName],
    surName = this[UserTable.surName],
    tgUserName = this[UserTable.tgUserName],
    languageCode = this[UserTable.languageCode],
)

fun UserModel.toInsertStatement(statement: InsertStatement<Number>): InsertStatement<Number> = statement.also {
    it[UserTable.firstName] = this.firstName
    it[UserTable.surName] = this.surName
    it[UserTable.tgId] = this.tgId
    it[UserTable.tgUserName] = this.tgUserName
    it[UserTable.languageCode] = this.languageCode
}

fun UserModel.toUpdateStatement(statement: UpdateStatement): UpdateStatement = statement.also {
    it[UserTable.firstName] = this.firstName
    it[UserTable.surName] = this.surName
    it[UserTable.tgId] = this.tgId
    it[UserTable.tgUserName] = this.tgUserName
    it[UserTable.languageCode] = this.languageCode
}