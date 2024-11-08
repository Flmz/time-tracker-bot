package ru.flmz.timetracker.telegram.keyboards

import dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.CallbackDataInlineKeyboardButton
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup

val WELCOME_KEYBOARDS = InlineKeyboardMarkup(
    listOf(
        listOf(
            CallbackDataInlineKeyboardButton(trackTimeInline.first, trackTimeInline.second),
            CallbackDataInlineKeyboardButton(showStatistic.first, showStatistic.second),
        )
    )
)

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