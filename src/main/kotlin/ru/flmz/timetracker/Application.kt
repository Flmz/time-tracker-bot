package ru.flmz.timetracker

import dev.inmo.tgbotapi.extensions.api.bot.setMyCommands
import dev.inmo.tgbotapi.extensions.api.send.sendMessage
import dev.inmo.tgbotapi.extensions.api.telegramBot
import dev.inmo.tgbotapi.extensions.behaviour_builder.buildBehaviourWithLongPolling
import dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling.onCommand
import dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling.onDataCallbackQuery
import dev.inmo.tgbotapi.types.BotCommand
import ru.flmz.timetracker.telegram.keyboards.TRACK_TIME_STAGE_KEYBOARD
import ru.flmz.timetracker.telegram.keyboards.WELCOME_KEYBOARDS
import ru.flmz.timetracker.telegram.keyboards.trackTimeInline

suspend fun main() {
    val bot = telegramBot("7889864849:AAHWea_uR7vxAlmpRUgH7UVv02IfYkvZHi0")

    bot.buildBehaviourWithLongPolling {
        setMyCommands(
            BotCommand("start", "Начать"),
            BotCommand("reset", "Сбросить"),
        )
        onCommand("qqq") {
            sendMessage(
                chatId = it.chat.id,
                text = "asdadasd.",
                replyMarkup = WELCOME_KEYBOARDS
            )
        }

        onCommand("start") {
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
