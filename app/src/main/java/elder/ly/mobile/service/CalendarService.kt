package elder.ly.mobile.service

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import java.time.LocalDateTime

interface CalendarService {

    @POST("/eventos")
    suspend fun insertEvent(@Body createEventInput : CreateEventInput) : Response<GetEventOutput>

    @GET("/eventos")
    suspend fun getListCaregiverEvents(@Body createEventInput : CreateEventInput) : Response<GetEventOutput>
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