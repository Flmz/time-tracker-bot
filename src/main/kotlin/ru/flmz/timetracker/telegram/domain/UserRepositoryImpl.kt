package ru.flmz.timetracker.telegram.domain

class UserRepositoryImpl : UserRepository {
    override suspend fun findById(tgId: Long): User? {
        return suspendTransaction {
            println("ENTITY")
            val entity = UserDao.findById(tgId)?.daoToModel()
             entity;
        }
    }
}