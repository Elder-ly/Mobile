package elder.ly.mobile.data.repository.viacep

import elder.ly.mobile.domain.service.AddressResponse
import elder.ly.mobile.domain.service.ViaCepService
import retrofit2.Response

class ViaCepRepository(
    private val service : ViaCepService
) : IViaCepRepository {

    override suspend fun getAddress(zipcode: String): Response<AddressResponse> {
        return service.getAddress(zipcode);
    }
}