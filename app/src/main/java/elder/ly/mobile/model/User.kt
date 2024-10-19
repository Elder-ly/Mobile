package elder.ly.mobile.model

import elder.ly.mobile.model.enums.GenderEnum
import elder.ly.mobile.model.enums.TypeUserEnum
import java.time.LocalDate

data class User(
    val id: Long,
    val type: TypeUserEnum,
    val gender: GenderEnum,
    var name: String?,
    var email: String,
    var document: String?,
    var birthDate: LocalDate?,
    var biography: String?,
    var pictureURL: String?,
    var residences: List<Residence>?,
    var resumes: List<Resumes>?
)