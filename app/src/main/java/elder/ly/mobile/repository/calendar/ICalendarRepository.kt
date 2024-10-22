package elder.ly.mobile.repository.calendar

import elder.ly.mobile.service.CreateEventInput
import elder.ly.mobile.service.GetEventOutput
import retrofit2.Response

interface ICalendarRepository {

    suspend fun insertEvent(accessToken : String, createEventInput : CreateEventInput) : Response<GetEventOutput>

    suspend fun createCalendar(accessToken : String, userId: Long) : Response<GetEventOutput>

    suspend fun getListCaregiverEvents(accessToken : String, sortBy: String = "dataHoraInicio") : Response<GetEventOutput>
}