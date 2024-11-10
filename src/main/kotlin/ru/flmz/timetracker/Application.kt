package ru.flmz.timetracker

import dev.inmo.kslog.common.KSLog
import dev.inmo.kslog.common.LogLevel
import dev.inmo.kslog.common.defaultMessageFormatter
import dev.inmo.kslog.common.setDefaultKSLog
import dev.inmo.tgbotapi.extensions.api.bot.setMyCommands
import dev.inmo.tgbotapi.extensions.api.send.sendMessage
import dev.inmo.tgbotapi.extensions.api.telegramBot
import dev.inmo.tgbotapi.extensions.behaviour_builder.buildBehaviourWithLongPolling
import dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling.onCommand
import dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling.onDataCallbackQuery
import dev.inmo.tgbotapi.types.BotCommand
import io.ktor.server.application.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import ru.flmz.timetracker.telegram.domain.UserRepositoryImpl
import ru.flmz.timetracker.telegram.keyboards.TRACK_TIME_STAGE_KEYBOARD
import ru.flmz.timetracker.telegram.keyboards.WELCOME_KEYBOARDS
import ru.flmz.timetracker.telegram.keyboards.trackTimeInline
import ru.flmz.timetracker.telegram.ktor.plugins.configureDatabases

fun Application.module() {
    configureDatabases()
}

suspend fun main() {
    val repository = UserRepositoryImpl()
    val bot = telegramBot("7889864849:AAHWea_uR7vxAlmpRUgH7UVv02IfYkvZHi0")
    setDefaultKSLog(
        KSLog { level: LogLevel, tag: String?, message: Any, throwable: Throwable? ->
            println(defaultMessageFormatter(level, tag, message, throwable))
        }
    )
    bot.buildBehaviourWithLongPolling(CoroutineScope(Dispatchers.IO)) {
        setMyCommands(
            BotCommand("start", "Начать"),
            BotCommand("reset", "Сбросить"),
        )

        onCommand("start") {
            println("hello")
            val entity = repository.findById(4)
            println("HELLO I AM ENTITY    $entity")
            sendMessage(
                chatId = it.chat.id,
                text = "In contrast to the matrices you've learned in school, keyboards are not always necessary square.",
                replyMarkup = WELCOME_KEYBOARDS
            )

        }

        onDataCallbackQuery(trackTimeInline.second) {
            sendMessage(
                chatId = it.user.id,
                text = "Hello from trackTimeInline",
                replyMarkup = TRACK_TIME_STAGE_KEYBOARD
            )
        }

    }.join()
}
