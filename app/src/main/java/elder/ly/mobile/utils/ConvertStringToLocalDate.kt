package elder.ly.mobile.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

object ConvertStringToLocalDate {

    fun convert(input: String): LocalDate? {
        // Primeiro, formata a data no padrão dd/MM/yyyy
        val formattedDate = formatBirthDate(input)
        // Em seguida, tenta converter a data formatada para LocalDate
        return parseFormattedBirthDate(formattedDate)
    }

    private fun formatBirthDate(input: String): String? {
        if (input.length == 8) {
            return "${input.substring(0, 2)}/${input.substring(2, 4)}/${input.substring(4, 8)}"
        }
        return null
    }

    private fun parseFormattedBirthDate(birthDate: String?): LocalDate? {
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        return try {
            birthDate?.let { LocalDate.parse(it, formatter) }
        } catch (e: DateTimeParseException) {
            null
        }
    }
}
