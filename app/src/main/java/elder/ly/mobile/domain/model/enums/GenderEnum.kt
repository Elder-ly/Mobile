package elder.ly.mobile.domain.model.enums

enum class GenderEnum(val id: Long, val description: String) {

    MALE(1, "Masculino"),
    FEMALE(2, "Feminino"),
    PREFER_NOT_TO_SAY(3, "Prefiro não Informar");

    companion object {
        fun fromCode(code: Long?): GenderEnum? {
            return entries.find { it.id == code }
        }
    }
}