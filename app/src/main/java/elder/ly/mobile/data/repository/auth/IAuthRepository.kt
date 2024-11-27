package elder.ly.mobile.data.repository.auth

import elder.ly.mobile.domain.service.UserLoginResponse
import retrofit2.Response

interface IAuthRepository {

    suspend fun login(email: String): Response<UserLoginResponse>
}
