package elder.ly.mobile.data.repository.viacep

import elder.ly.mobile.domain.service.AddressResponse
import retrofit2.Response

interface IViaCepRepository {

    suspend fun getAddress(zipcode : String) : Response<AddressResponse>
}