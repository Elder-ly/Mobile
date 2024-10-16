package elder.ly.mobile.utils

import elder.ly.mobile.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Rest {
    val api by lazy {
        Retrofit
            .Builder()
            .client(client)
            .baseUrl(BuildConfig.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val client by lazy{
        OkHttpClient.Builder().build()
    }
}