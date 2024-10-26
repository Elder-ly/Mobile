package elder.ly.mobile.model.enums

enum class TypeUserEnum(val id: Long, val description: String) {

    ADMIN(1, "Administrator"),
    COLLABORATOR(2, "Collaborator"),
    CLIENT(3, "Client");

    companion object {
        fun fromCodigo(code: Long): TypeUserEnum? {
            return entries.find { it.id == code }
        }
    }
}