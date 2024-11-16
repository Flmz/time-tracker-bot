package ru.flmz.timetracker.service

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.flmz.timetracker.model.UserModel
import ru.flmz.timetracker.repository.UserRepository

class UserService : KoinComponent {
    private val userRepo: UserRepository by inject()

    suspend fun createOrInsertOrUpdateUser(userModel: UserModel): UserModel? {
        return userRepo.findByTgIdWithoutTasks(userModel.tgId)?.let {
            if (userModel != it) {
                userRepo.update(userModel)
            }
            return userModel
        } ?: return userRepo.create(userModel)
    }
}

fun checkIfNeedUpdate(fromDb: UserModel, currentUser: UserModel): Boolean {
    return fromDb == currentUser
}
