package elder.ly.mobile.domain.service

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface AddressService {

    @GET("/enderecos/{id}")
    suspend fun getAddress(@Path("id") id : Long) : Response<AddressOutput>

    @PUT("/enderecos/{id}")
    suspend fun updateAddress(@Path("id") id : Long, @Body updateAddressInput: UpdateAddressInput) : Response<AddressOutput>
}

data class UpdateAddressInput(
    val cep: String,
    val logradouro: String,
    val complemento: String?,
    val bairro: String,
    val numero: String?,
    val cidade: String,
    val uf: String
)