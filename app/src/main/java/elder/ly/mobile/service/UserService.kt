package elder.ly.mobile.service

import elder.ly.mobile.model.Specialtie
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import java.time.LocalDate
import java.time.LocalDateTime

interface UserService {

    @POST("/usuarios/cliente")
    suspend fun createUserClient(@Body createUserClientInput : CreateUserInput) : Response<GetUsersOutput>

    @POST("/usuarios/colaborador")
    suspend fun createUserCollaborator(@Body createUserCollaboratorInput : CreateUserInput) : Response<GetUsersOutput>

    @POST("/usuarios/colaboradores-disponiveis")
    suspend fun getAvailableCollaborators(accessToken : String, @Body getUsersCollaboratorInput: GetUsersCollaboratorInput) : Response<List<GetUsersOutput>>

    @GET("/usuarios/clientes")
    suspend fun getUsersClients() : Response<List<GetUsersClientsOutput>>

    @GET("/usuarios/colaboradores")
    suspend fun getUsersCollaborator() : Response<List<GetUsersCollaboratorOutput>>

    @GET("/usuarios")
    suspend fun getUsers() : Response<List<GetUsersOutput>>

    @GET("/usuarios/{id}")
    suspend fun getUsers(@Path("id") id : Long) : Response<List<GetUsersOutput>>

    @PUT("/usuarios/{id}")
    suspend fun updateUsers(@Path("id") id : Long, @Body updateUserInput: UpdateUserInput) : Response<List<GetUsersOutput>>

    @DELETE("/usuarios/{id}")
    suspend fun deleteUsers(@Path("id") id : Long) : Void

}

data class CreateUserInput(
    val name: String,
    val email: String,
    val document: String,
    val birthDate: LocalDate?,
    val biography: String?,
    val profilePicture: String?,
    val userType: Long,
    val gender: Long,
    val address: CreateAddressInput,
    val specialties: List<Long>
)

data class UpdateUserInput(
    val name: String,
    val email: String,
    val document: String,
    val birthDate: LocalDate?,
    val biography: String?,
    val profilePicture: String?,
    val gender: Long,
    val address: CreateAddressInput,
    val specialties: List<Long>
)

data class CreateAddressInput(
    val cep: String,
    val street: String,
    val complement: String?,
    val neighborhood: String,
    val number: String?,
    val city: String,
    val state: String
)

data class GetUsersOutput(
    val id: Long,
    val name: String,
    val email: String,
    val document: String,
    val birthDate: LocalDate?,
    val biography: String?,
    val profilePicture: String?,
    val userType: Long,
    val gender: Long,
    val address: AddressOutput,
    val specialties: List<Specialtie>
)

data class GetUsersClientsOutput(
    val id: Long,
    val name: String,
    val email: String,
    val document: String,
    val birthDate: LocalDate?
)

data class GetUsersCollaboratorOutput(
    val id: Long,
    val name: String,
    val email: String,
    val document: String,
    val birthDate: LocalDate,
    val profilePicture: String?,
    val biography: String?,
    val address: AddressOutput,
    val specialties: List<Specialtie>
)

data class AddressOutput(
    val id : Long,
    val cep: String,
    val street: String,
    val complement: String?,
    val neighborhood: String,
    val number: String?,
    val city: String,
    val state: String
)

data class GetUsersCollaboratorInput(
    val specialties: List<String>,
    val startDateTime: LocalDateTime,
    val endDateTime: LocalDateTime
)
