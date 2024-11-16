package ru.flmz.timetracker.telegram.mapper

import dev.inmo.tgbotapi.extensions.utils.asWithOptionalLanguageCode
import dev.inmo.tgbotapi.types.chat.User
import dev.inmo.tgbotapi.utils.PreviewFeature
import ru.flmz.timetracker.model.UserModel

@OptIn(PreviewFeature::class)
fun toUserDomain(from: User?): UserModel =
    UserModel(
        firstName = from?.firstName,
        surName = from?.lastName,
        languageCode = from?.asWithOptionalLanguageCode()?.languageCode,
        tgUserName = from?.username?.withoutAt,
        tgId = from?.id?.chatId?.long!!
    )