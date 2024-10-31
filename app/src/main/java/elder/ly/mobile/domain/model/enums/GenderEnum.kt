package elder.ly.mobile.domain.model.enums

enum class GenderEnum(val id: Long, val description: String) {

    MALE(1, "Male"),
    FEMALE(2, "Female"),
    PREFER_NOT_TO_SAY(3, "Prefer not to say");

    companion object {
        fun fromCode(code: Long): GenderEnum? {
            return entries.find { it.id == code }
        }
    }
}