package elder.ly.mobile.domain.model

import java.time.LocalDateTime

data class Messages(
    val id : Long,
    var content : String,
    var dateTime : LocalDateTime,
    val recipient : User,
    val sender : User,
    val proposal: Proposal?
)
