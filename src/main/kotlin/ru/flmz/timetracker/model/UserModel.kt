package ru.flmz.timetracker.model

data class UserModel(
    val id: Long? = null,
    val firstName: String? = null,
    val surName: String? = null,
    val tgId: Long,
    val tgUserName: String? = null,
    val languageCode: String? = "en",
    var tasks: List<UserTask>? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is UserModel) return false

        return this.firstName == other.firstName && this.tgUserName == other.tgUserName &&
                this.surName == other.surName && this.languageCode == other.languageCode && this.tasks == other.tasks
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}