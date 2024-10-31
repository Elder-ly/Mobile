package elder.ly.mobile.domain.model

import elder.ly.mobile.domain.model.Messages
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
