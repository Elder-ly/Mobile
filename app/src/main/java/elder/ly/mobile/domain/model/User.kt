package elder.ly.mobile.domain.model

import elder.ly.mobile.domain.model.enums.GenderEnum
import elder.ly.mobile.domain.model.enums.TypeUserEnum

data class User(
    val id: Long,
    val type: Long, //TODO replace by Enum
    val gender : Long?,
    var name: String?,
    var email: String,
    var googleToken: String,
    var phoneNumber: String?,
    var pictureURL: String?,
    var residences: List<Residence>?,
    var resumes: List<Resumes>?
)