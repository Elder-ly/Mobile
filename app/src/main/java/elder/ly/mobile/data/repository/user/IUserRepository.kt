package elder.ly.mobile.repository.user

import elder.ly.mobile.service.CreateUserInput
import elder.ly.mobile.service.GetUsersClientsOutput
import elder.ly.mobile.service.GetUsersCollaboratorInput
import elder.ly.mobile.service.GetUsersCollaboratorOutput
import elder.ly.mobile.service.GetUsersOutput
import elder.ly.mobile.service.UpdateUserInput
import retrofit2.Response

interface IUserRepository {
    suspend fun createUserClient(createUserClientInput : CreateUserInput) : Response<GetUsersOutput>

    suspend fun createUserCollaborator(createUserCollaboratorInput : CreateUserInput) : Response<GetUsersOutput>

    suspend fun getAvailableCollaborators(accessToken : String, getUsersCollaboratorInput: GetUsersCollaboratorInput) : Response<List<GetUsersOutput>>

    suspend fun getUsersClients() : Response<List<GetUsersClientsOutput>>

    suspend fun getUsersCollaborator() : Response<List<GetUsersCollaboratorOutput>>

    suspend fun getUsers() : Response<List<GetUsersOutput>>

    suspend fun getUser(id : Long) : Response<List<GetUsersOutput>>

    suspend fun updateUsers(id : Long, updateUserInput: UpdateUserInput) : Response<List<GetUsersOutput>>

    suspend fun deleteUsers(id : Long) : Void
}