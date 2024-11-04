package elder.ly.mobile.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object DateTimeUtils {
    fun parseToLocalDateTime(dateString: String): LocalDateTime {
        val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
        return LocalDateTime.parse(dateString.substring(0, 19), inputFormatter)
    }

    fun formatLocalDateTime(localDateTime: LocalDateTime): String {
        val outputFormatter = DateTimeFormatter.ofPattern("dd/MM HH:mm")
        return localDateTime.format(outputFormatter)
    }
}
