package elder.ly.mobile.model

import android.net.Uri

data class User(
    val id: Int,
    val type: String, //TODO replace by Enum
    var name: String?,
    var email: String,
    var googleToken: String,
    var phoneNumber: String?,
    var pictureURL: String?,
)