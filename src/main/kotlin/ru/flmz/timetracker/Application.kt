package ru.flmz.timetracker

import dev.inmo.kslog.common.KSLog
import dev.inmo.kslog.common.LogLevel
import dev.inmo.kslog.common.defaultMessageFormatter
import dev.inmo.kslog.common.setDefaultKSLog
import org.koin.core.context.startKoin
import org.koin.dsl.module
import ru.flmz.timetracker.repository.UserRepository
import ru.flmz.timetracker.repository.impl.UserRepositoryImpl
import ru.flmz.timetracker.service.UserService
import ru.flmz.timetracker.telegram.TgStarter

val appModule = module {
    single<UserRepository> { UserRepositoryImpl() }
    single<UserService> { UserService() }
    single<TgStarter> { TgStarter() }
}

suspend fun main() {
    startKoin {
        modules(appModule)
    }
    setDefaultKSLog(
        KSLog { level: LogLevel, tag: String?, message: Any, throwable: Throwable? ->
            println(defaultMessageFormatter(level, tag, message, throwable))
        }
    )
    TgStarter().startBot()
}





