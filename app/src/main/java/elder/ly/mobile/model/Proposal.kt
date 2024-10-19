package elder.ly.mobile.model

import java.math.BigDecimal
import java.time.LocalDateTime

data class Proposal(
    val id : Long,
    var description : String,
    var startTime : LocalDateTime,
    var endTime : LocalDateTime,
    var price : BigDecimal,
    var accepted : Boolean,
    val message : Messages
)
