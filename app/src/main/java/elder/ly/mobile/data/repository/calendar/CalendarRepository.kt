package elder.ly.mobile.repository.calendar

import elder.ly.mobile.service.CalendarService
import elder.ly.mobile.service.CreateEventInput
import elder.ly.mobile.service.GetEventOutput
import retrofit2.Response

class CalendarRepository(
    private val service : CalendarService
) : ICalendarRepository {
    override suspend fun insertEvent(
        accessToken: String,
        createEventInput: CreateEventInput
    ): Response<GetEventOutput> {
        return service.insertEvent(accessToken, createEventInput);
    }

    override suspend fun createCalendar(
        accessToken: String,
        userId: Long
    ): Response<GetEventOutput> {
        return service.createCalendar(accessToken, userId);
    }

    override suspend fun getListCaregiverEvents(
        accessToken: String,
        sortBy: String
    ): Response<GetEventOutput> {
        return service.getListCaregiverEvents(accessToken, sortBy);
    }


}