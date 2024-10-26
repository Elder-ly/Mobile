package elder.ly.mobile.repository.specialtie

import elder.ly.mobile.service.CreateSpecialtie
import elder.ly.mobile.service.GetUsersOutput
import elder.ly.mobile.service.SpecialtieOutput
import elder.ly.mobile.service.UpdateSpecialtieInput
import retrofit2.Response

interface ISpecialtieRepository {

    suspend fun createSpecialtie(createSpecialtieInput : CreateSpecialtie) : Response<CreateSpecialtie>

    suspend fun getSpecialtie(id : Long) : Response<SpecialtieOutput>

    suspend fun updateSpecialtie(id : Long, updateSpecialtieInput: UpdateSpecialtieInput) : Response<List<GetUsersOutput>>

    suspend fun deleteUsers(id : Long) : Void
}