package ru.flmz.timetracker.telegram.keyboards

import dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.CallbackDataInlineKeyboardButton
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup

val WELCOME_KEYBOARDS = InlineKeyboardMarkup(
    listOf(
        createCallbackDataInlineKeyboard(WELCOME_ANSWER.callBackAnswer)
    )
)

fun createCallbackDataInlineKeyboard(callbacks: List<Pair<String, String>>): List<CallbackDataInlineKeyboardButton> {
    return callbacks.map {
        CallbackDataInlineKeyboardButton(it.first, it.second)
    }
        .toList()
}

val TRACK_TIME_STAGE_KEYBOARD = InlineKeyboardMarkup(
    listOf(
        listOf(
            CallbackDataInlineKeyboardButton(sport.first, sport.second),
            CallbackDataInlineKeyboardButton(games.first, games.second),
            CallbackDataInlineKeyboardButton(reading.first, reading.second),
        ),
        listOf(
            CallbackDataInlineKeyboardButton(customTracking.first, customTracking.second)
        )
    )
)