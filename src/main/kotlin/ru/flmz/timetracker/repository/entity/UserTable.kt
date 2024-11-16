package ru.flmz.timetracker.repository.entity

import org.jetbrains.exposed.dao.id.IdTable


object UserTable : IdTable<Long>("users") {
    override val id = long("id").entityId()
    var tgId = long("tg_id").uniqueIndex()
    var firstName = varchar("first_name", 1500).nullable()
    var tgUserName = varchar("tg_user_name", 1500).nullable()
    var surName = varchar("sur_name", 1500).nullable()

    var languageCode = varchar("language_code", 20).nullable()
}



