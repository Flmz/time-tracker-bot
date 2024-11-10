package ru.flmz.timetracker.telegram.keyboards

val trackTimeInline = "Затрекать время" to "track_time"
val showStatistic = "Показать статистику" to "show_stat"
val sport = "Спорт" to "track_sport"
val games = "Игры" to "track_games"
val reading = "Чтение" to "track_reading"
val customTracking = "Добавить свое" to "custom_tracking"

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


