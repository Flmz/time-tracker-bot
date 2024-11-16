package ru.flmz.timetracker.telegram.keyboards

val trackTimeInline = "Затрекать время" to "track_time"
val showStatistic = "Показать статистику" to "show_stat"
val sport = "Спорт" to "sport_track"
val games = "Игры" to "games_track"
val reading = "Чтение" to "reading_track"
val customTracking = "Добавить свое" to "custom_track"

class StaticCallBackQueryAnswer(
    val text: String,
    val callBackAnswer: List<Pair<String, String>>
)

val WELCOME_ANSWER = StaticCallBackQueryAnswer(
    text = "Hello",
    callBackAnswer = listOf(
        trackTimeInline.first to trackTimeInline.second,
        showStatistic.first to showStatistic.second
    )
)


