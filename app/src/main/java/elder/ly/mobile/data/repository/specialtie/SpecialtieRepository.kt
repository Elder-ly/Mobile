package elder.ly.mobile.data.repository.specialtie

import elder.ly.mobile.data.repository.specialtie.ISpecialtieRepository
import elder.ly.mobile.domain.service.CreateSpecialtie
import elder.ly.mobile.domain.service.GetUsersOutput
import elder.ly.mobile.domain.service.SpecialtieOutput
import elder.ly.mobile.domain.service.SpecialtieService
import elder.ly.mobile.domain.service.UpdateSpecialtieInput
import retrofit2.Response

class SpecialtieRepository(
    private val service : SpecialtieService
) : ISpecialtieRepository {

    override suspend fun createSpecialtie(createSpecialtieInput: CreateSpecialtie): Response<CreateSpecialtie> {
        return service.createSpecialtie(createSpecialtieInput);
    }

    override suspend fun getSpecialtie(id: Long): Response<SpecialtieOutput> {
        return service.getSpecialtie(id);
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