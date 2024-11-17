package elder.ly.mobile.data

import elder.ly.mobile.BuildConfig
import elder.ly.mobile.domain.service.AddressesService
import elder.ly.mobile.domain.service.CalendarService
import elder.ly.mobile.domain.service.MessageService
import elder.ly.mobile.domain.service.ProposalService
import elder.ly.mobile.domain.service.SpecialtieService
import elder.ly.mobile.domain.service.UserService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Rest {

    val client by lazy{
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor())
            .build()
    }

    val api by lazy {
        Retrofit
            .Builder()
            .client(client)
            .baseUrl(BuildConfig.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val addressesService by lazy {
        api.create(AddressesService::class.java)
    }

    val calendarService by lazy {
        api.create(CalendarService::class.java);
    }

    val messageService by lazy {
        api.create(MessageService::class.java);
    }

    val proposalService by lazy {
        api.create(ProposalService::class.java);
    }

    val specialtieService by lazy {
        api.create(SpecialtieService::class.java);
    }

    val userService: UserService by lazy {
        api.create(UserService::class.java)
    }

    fun loggingInterceptor() : HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
}