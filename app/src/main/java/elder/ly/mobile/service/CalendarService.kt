package elder.ly.mobile.service

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query
import java.time.LocalDateTime

interface CalendarService {

    @POST("/calendarios/eventos")
    suspend fun insertEvent(@Header("accessToken") accessToken : String, @Body createEventInput : CreateEventInput) : Response<GetEventOutput>

    @POST("/calendarios")
    suspend fun createCalendar(@Header("accessToken") accessToken : String, @Query("userId") userId: Long) : Response<GetEventOutput>

    @GET("/calendarios/eventos")
    suspend fun getListCaregiverEvents(@Header("accessToken") accessToken : String, @Query("ordenarPor") sortBy: String = "dataHoraInicio") : Response<GetEventOutput>
}

data class CreateEventInput(
    val proposalName: String,
    val clientEmail: String,
    val employeeEmail: String,
    val startDateTime: LocalDateTime,
    val endDateTime: LocalDateTime,
    val recurrence: String?,
    val description: String?
)

data class GetEventOutput(
    val id: String,
    val proposalName: String,
    val clientEmail: String,
    val employeeEmail: String,
    val startDateTime: String,
    val endDateTime: String,
    val recurrence: List<String>,
    val description: String
)

data class CalendarOutput(
    val id: Long,
    val calendarId: String,
    val user: UserCalendarView
)

data class UserCalendarView(
    val id: Long,
    val name: String,
    val email: String
)