package elder.ly.mobile.data.repository.addresses

import elder.ly.mobile.domain.service.AddressOutput
import elder.ly.mobile.domain.service.AddressService
import elder.ly.mobile.domain.service.UpdateAddressInput
import retrofit2.Response

class AddressRepository(
    private val service: AddressService
) : IAddressRepository {
    override suspend fun getAddresses(id: Long): Response<AddressOutput> {
        return service.getAddress(id);
    }

    override suspend fun updateAddresses(
        id: Long,
        updateAddressInput: UpdateAddressInput
    ): Response<AddressOutput> {
        return service.updateAddress(id, updateAddressInput)
    }
}