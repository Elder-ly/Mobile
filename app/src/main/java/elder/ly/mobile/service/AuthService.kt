package elder.ly.mobile.service

import elder.ly.mobile.model.enums.TypeUserEnum
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AuthService {
    @GET("/usuarios/email/{email}")
    suspend fun login(@Path("email") email: String): Response<UserLoginResponse>
}

data class UserLoginResponse(
    val id: Long,
    val tipoUsuario: TypeUserEnum
)
