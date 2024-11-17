package elder.ly.mobile.data.repository.addresses

import elder.ly.mobile.domain.service.AddressOutput
import elder.ly.mobile.domain.service.AddressesService
import elder.ly.mobile.domain.service.UpdateAddressInput
import retrofit2.Response

class AddressesRepository(
    private val service: AddressesService
) : IAddressesRepository {
    override suspend fun getAddresses(id: Long): Response<AddressOutput> {
        return service.getAddresses(id);
    }

    override suspend fun updateAddresses(
        id: Long,
        updateAddressInput: UpdateAddressInput
    ): Response<AddressOutput> {
        return service.updateAddresses(id, updateAddressInput)
    }
}