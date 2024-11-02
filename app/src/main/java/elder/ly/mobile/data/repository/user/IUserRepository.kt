package elder.ly.mobile.data.repository.user

import elder.ly.mobile.domain.service.CreateUserInput
import elder.ly.mobile.domain.service.GetProfileDetails
import elder.ly.mobile.domain.service.GetProfileUse
import elder.ly.mobile.domain.service.GetUsersClientsOutput
import elder.ly.mobile.domain.service.GetUsersCollaboratorInput
import elder.ly.mobile.domain.service.GetUsersCollaboratorOutput
import elder.ly.mobile.domain.service.GetUsersOutput
import elder.ly.mobile.domain.service.UpdateUserInput
import retrofit2.Response

interface IUserRepository {
    suspend fun createUserClient(createUserClientInput : CreateUserInput) : Response<GetUsersOutput>

    suspend fun createUserCollaborator(createUserCollaboratorInput : CreateUserInput) : Response<GetUsersOutput>

    suspend fun getAvailableCollaborators(accessToken : String, getUsersCollaboratorInput: GetUsersCollaboratorInput) : Response<List<GetUsersOutput>>

    suspend fun getUsersClients() : Response<List<GetUsersClientsOutput>>

    suspend fun getUsersCollaborator() : Response<List<GetUsersCollaboratorOutput>>

    suspend fun getUsers() : Response<List<GetUsersOutput>>

    suspend fun getUser(id : Long) : Response<GetUsersOutput>

    suspend fun getUserProfile(id : Long) : Response<GetProfileUse>

    suspend fun getUserProfileDetails(id : Long) : Response<GetProfileDetails>

    suspend fun updateUsers(id : Long, updateUserInput: UpdateUserInput) : Response<List<GetUsersOutput>>

    suspend fun deleteUsers(id : Long) : Void
}