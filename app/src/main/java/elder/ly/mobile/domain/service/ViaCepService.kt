package elder.ly.mobile.domain.service

import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ViaCepService {

    @GET("{zipcode}/json")
    suspend fun getAddress(@Path("zipcode") zipcode : String) : Response<AddressResponse>
}

data class AddressResponse(

    @SerializedName("cep")
    val zipcode: String?,

    @SerializedName("logradouro")
    val street: String?,

    @SerializedName("bairro")
    val district: String?,

    @SerializedName("localidade")
    val city: String?,

    @SerializedName("uf")
    val state: String?,
)