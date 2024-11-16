package ru.flmz.timetracker.repository.impl

import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.update
import org.koin.core.component.KoinComponent
import ru.flmz.timetracker.model.UserModel
import ru.flmz.timetracker.repository.UserRepository
import ru.flmz.timetracker.repository.entity.UserTable
import ru.flmz.timetracker.repository.entity.UserTasksTable
import ru.flmz.timetracker.repository.mapper.toInsertStatement
import ru.flmz.timetracker.repository.mapper.toUpdateStatement
import ru.flmz.timetracker.repository.mapper.toUser
import ru.flmz.timetracker.repository.mapper.toUserWithoutAggregates
import ru.flmz.timetracker.repository.suspendTransaction

val UsersWithTasks = (UserTable innerJoin UserTasksTable)

class UserRepositoryImpl : UserRepository, KoinComponent {
    override suspend fun findByTgIdWithoutTasks(tgId: Long): UserModel? =
        suspendTransaction {
            UserTable
                .select(UserTable.columns)
                .where(UserTable.tgId eq tgId)
                .firstOrNull()?.toUserWithoutAggregates()
        }

    override suspend fun findByTgId(tgId: Long): UserModel? =
        suspendTransaction {
            UserTable
                .select(UserTable.columns)
                .where(UserTable.tgId eq tgId)
                .groupBy { UserTable.id }
                .entries.firstOrNull()?.toUser()
        }

    override suspend fun create(userModel: UserModel): UserModel? =
        suspendTransaction {
            val a = UserTable.insert {
                userModel.toInsertStatement(it)
            }
            return@suspendTransaction a.resultedValues?.first()?.toUserWithoutAggregates()
        }

    override suspend fun update(userModel: UserModel) {
        suspendTransaction {
            UserTable.update({ UserTable.tgId eq userModel.tgId }) {
                userModel.toUpdateStatement(it)
            }
        }
    }
}