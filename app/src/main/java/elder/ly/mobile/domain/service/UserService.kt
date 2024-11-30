package elder.ly.mobile.domain.service

import elder.ly.mobile.domain.model.Specialtie
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

    @POST("/usuarios/colaboradores-disponiveis")
    suspend fun getAvailableCollaborators(@Header("accessToken") accessToken : String, @Body getUsersCollaboratorInput: GetUsersCollaboratorInput) : Response<List<GetUsersCollaboratorOutput>>

    @GET("/usuarios/clientes")
    suspend fun getUsersClients() : Response<List<GetUsersClientsOutput>>

    @GET("/usuarios/colaboradores")
    suspend fun getUsersCollaborator() : Response<List<GetUsersCollaboratorOutput>>

    @GET("/usuarios")
    suspend fun getUsers() : Response<List<GetUsersOutput>>

    @GET("/usuarios/{id}")
    suspend fun getUser(@Path("id") id : Long) : Response<GetUsersOutput>

    @GET("/usuarios/{id}")
    suspend fun getUserProfile(@Path("id") id : Long) : Response<GetProfileUse>

    @GET("/usuarios/{id}")
    suspend fun getUserProfileDetails(@Path("id") id : Long) : Response<GetProfileDetails>

    @PUT("/usuarios/{id}")
    suspend fun updateUsers(@Path("id") id : Long, @Body updateUserInput: UpdateUserInput) : Response<GetUsersOutput>

    @DELETE("/usuarios/{id}")
    suspend fun deleteUsers(@Path("id") id : Long) : Void

}

data class CreateUserInput(
    val nome: String,
    val email: String,
    val documento: String,
    val dataNascimento: String?,
    val biografia: String?,
    val fotoPerfil: String?,
    val tipoUsuario: Long,
    val genero: Long,
    val endereco: CreateAddressInput,
    val especialidades: List<Long>
)

data class CreateClientInput(
    val nome: String,
    val email: String,
    val documento: String,
    val dataNascimento: String?,
    val biografia: String?,
    val fotoPerfil: String?,
    val tipoUsuario: Long,
    val genero: Long,
    val especialidades: List<Long>
)

data class UpdateUserInput(
    val nome: String,
    val email: String,
    val documento: String,
    val dataNascimento: String?,
    val biografia: String?,
    val fotoPerfil: String?,
    val genero: Long,
    val updateAddressInput: UpdateAddressInput?,
    val especialidades: List<Long>
)

data class UpdateClientInput(
    val nome: String,
    val email: String,
    val documento: String,
    val dataNascimento: String?,
    val biografia: String?,
    val genero: Long,
    val especialidades: List<Long>
)

data class CreateAddressInput(
    val cep: String,
    val logradouro: String,
    val complemento: String,
    val bairro: String,
    val numero: String,
    val cidade: String,
    val uf: String
)

data class GetUsersOutput(
    val id: Long,
    val nome: String,
    val email: String,
    val documento: String,
    val dataNascimento: String?,
    val biografia: String?,
    val fotoPerfil: String?,
    val tipoUsuario: Long,
    val genero: Long,
    val endereco: AddressOutput,
    val especialidades: List<SpecialtieOutput>
)

data class GetProfileUse(
    val name: String,
    val profilePicture: String?
)

data class GetProfileDetails(
    val id: Long,
    val name: String,
    val profilePicture: String?,
    val address: AddressBairro,
    val biography: String?,
    val specialties: List<Specialtie>?,
    val price: String
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
    val nome: String,
    val email: String,
    val documento: String,
    val dataNascimento: String,
    val fotoPerfil: String?,
    val biografia: String?,
    val endereco: AddressOutput,
    val especialidades: List<Specialtie>
)

data class AddressOutput(
    val id : Long,
    val cep: String,
    val logradouro: String,
    val complemento: String?,
    val bairro: String?,
    val numero: String?,
    val cidade: String,
    val uf: String
)

data class AddressBairro (
    val neighborhood: String
)

data class GetUsersCollaboratorInput(
    val especialidades: List<String>,
    val dataHoraInicio: String,
    val dataHoraFim: String
)

data class ResidenceOutput(
    val bairro : String,
    val cidade: String
)

data class ResumeOutput(
    val id: Long,
    val user : GetUsersOutput,
    val specialtie : SpecialtieOutput
)

data class GetDataSearchScreen(
    val startDate: String,
    val endDate: String,
    val startTime: String,
    val endTime: String,
    val specialties: List<String>
)
