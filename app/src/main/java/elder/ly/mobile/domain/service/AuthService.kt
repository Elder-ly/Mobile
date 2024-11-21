package elder.ly.mobile.domain.service

import elder.ly.mobile.domain.model.enums.TypeUserEnum
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthService {
    @GET("/usuarios/email/{email}")
    suspend fun login(@Path("email") email: String): Response<UserLoginResponse>

    @FormUrlEncoded
    @POST("/token")
    suspend fun exchangeAuthCode(
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("code") authCode: String,
        @Field("grant_type") grantType: String = "authorization_code"
    ): Response<GoogleTokenResponse>
}

data class UserLoginResponse(
    val id: Long,
    val tipoUsuario: TypeUserEnum
)

data class GoogleTokenResponse(
    val access_token: String,
    val expires_in: Int,
    val refresh_token: String?,
    val scope: String,
    val token_type: String
)
