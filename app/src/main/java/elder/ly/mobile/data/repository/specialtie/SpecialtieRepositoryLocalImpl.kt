package elder.ly.mobile.data.repository.specialtie

import elder.ly.mobile.domain.model.Specialtie
import elder.ly.mobile.domain.service.CreateSpecialtie
import elder.ly.mobile.domain.service.GetUsersOutput
import elder.ly.mobile.domain.service.SpecialtieOutput
import elder.ly.mobile.domain.service.SpecialtieService
import elder.ly.mobile.domain.service.UpdateSpecialtieInput
import retrofit2.Response

class SpecialtieRepositoryLocalImpl (
    private val service : SpecialtieService
) : ISpecialtieRepository {

    override suspend fun createSpecialtie(createSpecialtieInput: CreateSpecialtie): Response<CreateSpecialtie> {
        return service.createSpecialtie(createSpecialtieInput);
    }

    override suspend fun getSpecialtie(id: Long): Response<SpecialtieOutput> {
        return service.getSpecialtie(id);
    }

    override suspend fun getSpecialties(): Response<List<SpecialtieOutput>> {
        return Response.success(
            listOf(
                SpecialtieOutput(id = 1, name = "Fraldas"),
                SpecialtieOutput(id = 2, name = "Bingo"),
                SpecialtieOutput(id = 3, name = "Medicação"),
                SpecialtieOutput(id = 4, name = "Banho"),
                SpecialtieOutput(id = 5, name = "Acompanhamento")
            )
        )
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