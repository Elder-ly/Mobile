package elder.ly.mobile.model

import elder.ly.mobile.model.enums.GenderEnum
import elder.ly.mobile.model.enums.TypeUserEnum

data class User(
    val id: Long,
    val type: TypeUserEnum, //TODO replace by Enum
    val gender : GenderEnum?,
    var name: String?,
    var email: String,
    var googleToken: String,
    var phoneNumber: String?,
    var pictureURL: String?,
    var residences: List<Residence>?,
    var resumes: List<Resumes>?
)