package elder.ly.mobile.utils

import java.time.LocalDateTime
import java.time.ZoneId
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

    fun convertToIso8601(input: String): String {
        // Formato original
        val inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyyHH:mm")

        // Parse o valor recebido
        val localDateTime = LocalDateTime.parse(input, inputFormatter)

        // Converta para ZonedDateTime no UTC
        val utcZonedDateTime = localDateTime.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("UTC"))

        val isoFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        return isoFormatter.format(utcZonedDateTime)
    }
}
