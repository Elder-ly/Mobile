package elder.ly.mobile.utils

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.Locale

object ConvertDate {

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

    fun convertDate(input: String): String {
        // Define o formato de entrada e saída
        val inputFormat = SimpleDateFormat("ddMMyyyy", Locale.getDefault())
        val outputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        return try {
            // Converte a string de entrada para um objeto Date
            val date = inputFormat.parse(input)
            // Formata a data no novo formato
            outputFormat.format(date!!)
        } catch (e: Exception) {
            // Em caso de erro, retorna uma string vazia ou trate o erro conforme necessário
            ""
        }
    }

    fun formatDateToDisplay(date: String): String {
        // Verifica se a data está no formato esperado "YYYY-MM-DD"
        if (date.matches(Regex("\\d{4}-\\d{2}-\\d{2}"))) {
            val (year, month, day) = date.split("-")
            // Valida se o ano, mês e dia são numéricos e se fazem sentido
            if (year.toIntOrNull() != null && month.toIntOrNull() != null && day.toIntOrNull() != null) {
                // Formata e retorna a data
                return "$day$month$year"
            }
        }
        // Retorna uma string vazia ou uma mensagem de erro se a data for inválida
        return date // Ou "Data inválida" se preferir
    }
}
