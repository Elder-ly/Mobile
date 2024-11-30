package elder.ly.mobile.data.repository.specialtie

import elder.ly.mobile.domain.service.GetUsersOutput
import elder.ly.mobile.domain.service.SpecialtieOutput
import elder.ly.mobile.domain.service.SpecialtieService
import elder.ly.mobile.domain.service.UpdateSpecialtieInput
import retrofit2.Response

class SpecialtieRepository(
    private val service : SpecialtieService
) : ISpecialtieRepository {

    override suspend fun getSpecialtie(id: Long): Response<SpecialtieOutput> {
        return service.getSpecialtie(id);
    }

    override suspend fun getSpecialties(): Response<List<SpecialtieOutput>> {
        return service.getSpecialties()
    }

    override suspend fun updateSpecialtie(
        id: Long,
        updateSpecialtieInput: UpdateSpecialtieInput
    ): Response<List<GetUsersOutput>> {
        return service.updateSpecialtie(id, updateSpecialtieInput);
    }

    override suspend fun deleteUsers(id: Long): Void {
        return service.deleteUsers(id);
    }
}