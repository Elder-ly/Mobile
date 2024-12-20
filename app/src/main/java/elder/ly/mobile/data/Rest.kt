package elder.ly.mobile.data

import elder.ly.mobile.BuildConfig
import elder.ly.mobile.domain.service.AddressService
import elder.ly.mobile.domain.service.AuthService
import elder.ly.mobile.domain.service.CalendarService
import elder.ly.mobile.domain.service.MessageService
import elder.ly.mobile.domain.service.ProposalService
import elder.ly.mobile.domain.service.SpecialtieService
import elder.ly.mobile.domain.service.UserService
import elder.ly.mobile.domain.service.ViaCepService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object Rest {

    val client by lazy{
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor())
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .callTimeout(30, TimeUnit.SECONDS)
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

    val viaCep by lazy {
        Retrofit
            .Builder()
            .client(client)
            .baseUrl("https://viacep.com.br/ws/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val authService by lazy {
        api.create(AuthService::class.java)
    }

    val addressService by lazy {
        api.create(AddressService::class.java)
    }

    val viaCepService by lazy {
        viaCep.create(ViaCepService::class.java)
    }

    val googleAuthApi by lazy {
        Retrofit
            .Builder()
            .client(client)
            .baseUrl("https://oauth2.googleapis.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
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

    val userService by lazy {
        api.create(UserService::class.java)
    }

    fun loggingInterceptor() : HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
}