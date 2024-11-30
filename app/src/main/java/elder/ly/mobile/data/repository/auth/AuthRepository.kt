package elder.ly.mobile.data.repository.auth

import elder.ly.mobile.domain.service.AuthService
import elder.ly.mobile.domain.service.UserLoginResponse
import retrofit2.Response

class AuthRepository(
    private val service : AuthService
) : IAuthRepository {

    override suspend fun login(email: String): Response<UserLoginResponse> {
        return service.login(email)
    }

}