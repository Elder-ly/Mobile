package elder.ly.mobile.data.repository.specialtie

import elder.ly.mobile.domain.service.GetUsersOutput
import elder.ly.mobile.domain.service.SpecialtieOutput
import elder.ly.mobile.domain.service.UpdateSpecialtieInput
import retrofit2.Response

interface ISpecialtieRepository {
    suspend fun getSpecialtie(id : Long) : Response<SpecialtieOutput>

    suspend fun getSpecialties() : Response<List<SpecialtieOutput>>

    suspend fun updateSpecialtie(id : Long, updateSpecialtieInput: UpdateSpecialtieInput) : Response<List<GetUsersOutput>>

    suspend fun deleteUsers(id : Long) : Void
}