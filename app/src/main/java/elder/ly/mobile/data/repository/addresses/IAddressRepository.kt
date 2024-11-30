package elder.ly.mobile.data.repository.addresses

import elder.ly.mobile.domain.service.AddressOutput
import elder.ly.mobile.domain.service.UpdateAddressInput
import retrofit2.Response

interface IAddressRepository {

    suspend fun getAddresses(id : Long) : Response<AddressOutput>

    suspend fun updateAddresses(id : Long, updateAddressInput: UpdateAddressInput) : Response<AddressOutput>
}