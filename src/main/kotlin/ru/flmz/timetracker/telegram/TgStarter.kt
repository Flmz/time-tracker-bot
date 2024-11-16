package ru.flmz.timetracker.telegram

import dev.inmo.kslog.common.KSLog
import dev.inmo.kslog.common.warning
import dev.inmo.micro_utils.coroutines.subscribeSafelyWithoutExceptions
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.extensions.api.bot.setMyCommands
import dev.inmo.tgbotapi.extensions.api.edit.edit
import dev.inmo.tgbotapi.extensions.api.send.sendMessage
import dev.inmo.tgbotapi.extensions.api.telegramBot
import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.behaviour_builder.buildBehaviourWithLongPolling
import dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling.onCommand
import dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling.onMessageDataCallbackQuery
import dev.inmo.tgbotapi.extensions.utils.extensions.raw.from
import dev.inmo.tgbotapi.extensions.utils.updates.retrieving.flushAccumulatedUpdates
import dev.inmo.tgbotapi.extensions.utils.withContent
import dev.inmo.tgbotapi.types.BotCommand
import dev.inmo.tgbotapi.types.message.content.TextContent
import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.regular
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.flmz.timetracker.service.UserService
import ru.flmz.timetracker.telegram.keyboards.TRACK_TIME_STAGE_KEYBOARD
import ru.flmz.timetracker.telegram.keyboards.WELCOME_KEYBOARDS
import ru.flmz.timetracker.telegram.keyboards.trackTimeInline
import ru.flmz.timetracker.telegram.mapper.toUserDomain

class TgStarter : KoinComponent {
    private val userService: UserService by inject();
    private lateinit var bot: TelegramBot;
    suspend fun startBot() {
        bot = telegramBot("7889864849:AAHWea_uR7vxAlmpRUgH7UVv02IfYkvZHi0")
        bot.buildBehaviourWithLongPolling(
            CoroutineScope(Dispatchers.IO),
            defaultExceptionsHandler = { t: Throwable -> KSLog.warning(t.message!!, t) }) {
            allUpdatesFlow.subscribeSafelyWithoutExceptions(this) {
                println(it)
            }
            flushAccumulatedUpdates()
            setMyCommand()
            onCommandStart(this)
            dataCallBack1(this)
            dataCallback2(this)

        }.join()
    }

    private suspend fun setMyCommand() {
        bot.setMyCommands(
            BotCommand("start", "Начать"),
            BotCommand("reset", "Сбросить"),
        )
    }

    @OptIn(RiskFeature::class)
    private suspend fun onCommandStart(context: BehaviourContext) {
        context.onCommand("start", requireOnlyCommandInMessage = true) {
            userService.createOrInsertOrUpdateUser(toUserDomain(it.from))
            sendMessage(
                chatId = it.chat.id,
                text = "Hello in your Time Tracker Bot",
                replyMarkup = WELCOME_KEYBOARDS
            )
        }
    }

    private suspend fun dataCallback2(context: BehaviourContext) {
        context.onMessageDataCallbackQuery(Regex(".*_track")) {
            println(it)
            edit(
                it.message.withContent<TextContent>()!!,
                replyMarkup = TRACK_TIME_STAGE_KEYBOARD
            ) {
                regular("Выберите что будем трекать")
            }
        }
    }

    private suspend fun dataCallBack1(context: BehaviourContext) {
        context.onMessageDataCallbackQuery(Regex(trackTimeInline.second)) {
            edit(
                it.message.withContent<TextContent>()!!,
                replyMarkup = TRACK_TIME_STAGE_KEYBOARD
            ) {
                regular("Выберите что будем трекать")
            }
        }
    }
}

