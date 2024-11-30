package elder.ly.mobile.domain.service

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface SpecialtieService {
    @GET("/especialidades/{id}")
    suspend fun getSpecialtie(@Path("id") id : Long) : Response<SpecialtieOutput>

    @GET("/especialidades")
    suspend fun getSpecialties() : Response<List<SpecialtieOutput>>

    @PUT("/especialidades/{id}")
    suspend fun updateSpecialtie(@Path("id") id : Long, @Body updateSpecialtieInput: UpdateSpecialtieInput) : Response<List<GetUsersOutput>>

    @DELETE("/especialidades/{id}")
    suspend fun deleteUsers(@Path("id") id : Long) : Void
}

data class SpecialtieOutput(
    val id: Long,
    val nome: String
)

data class UpdateSpecialtieInput(
    val name: String
)