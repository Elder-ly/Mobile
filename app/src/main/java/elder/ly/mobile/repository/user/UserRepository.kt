package elder.ly.mobile.repository.user

import elder.ly.mobile.service.CreateUserInput
import elder.ly.mobile.service.GetUsersClientsOutput
import elder.ly.mobile.service.GetUsersCollaboratorInput
import elder.ly.mobile.service.GetUsersCollaboratorOutput
import elder.ly.mobile.service.GetUsersOutput
import elder.ly.mobile.service.UpdateUserInput
import elder.ly.mobile.service.UserService
import retrofit2.Response

class UserRepository(
    private val service : UserService
) : IUserRepository {

    override suspend fun createUserClient(createUserClientInput: CreateUserInput): Response<GetUsersOutput> {
        return service.createUserClient(createUserClientInput);
    }

    override suspend fun createUserCollaborator(createUserCollaboratorInput: CreateUserInput): Response<GetUsersOutput> {
        return service.createUserCollaborator(createUserCollaboratorInput);
    }

    override suspend fun getAvailableCollaborators(
        accessToken: String,
        getUsersCollaboratorInput: GetUsersCollaboratorInput
    ): Response<List<GetUsersOutput>> {
        return service.getAvailableCollaborators(accessToken, getUsersCollaboratorInput);
    }

    override suspend fun getUsersClients(): Response<List<GetUsersClientsOutput>> {
        return service.getUsersClients();
    }

    override suspend fun getUsersCollaborator(): Response<List<GetUsersCollaboratorOutput>> {
        return service.getUsersCollaborator();
    }

    override suspend fun getUsers(): Response<List<GetUsersOutput>> {
        return service.getUsers();
    }

    override suspend fun getUser(id: Long): Response<List<GetUsersOutput>> {
        return service.getUser(id);
    }

    override suspend fun updateUsers(
        id: Long,
        updateUserInput: UpdateUserInput
    ): Response<List<GetUsersOutput>> {
        return service.updateUsers(id, updateUserInput);
    }

    override suspend fun deleteUsers(id: Long): Void {
       return service.deleteUsers(id);
    }

}