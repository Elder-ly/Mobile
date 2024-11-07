package elder.ly.mobile.data.repository.user

import elder.ly.mobile.domain.service.CreateUserInput
import elder.ly.mobile.domain.service.GetProfileDetails
import elder.ly.mobile.domain.service.GetProfileUse
import elder.ly.mobile.domain.service.GetUsersClientsOutput
import elder.ly.mobile.domain.service.GetUsersCollaboratorInput
import elder.ly.mobile.domain.service.GetUsersCollaboratorOutput
import elder.ly.mobile.domain.service.GetUsersOutput
import elder.ly.mobile.domain.service.UpdateUserInput
import elder.ly.mobile.domain.service.UserService
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

    override suspend fun getUser(id: Long): Response<GetUsersOutput> {
        return service.getUser(id);
    }

    override suspend fun getUserProfile(id: Long): Response<GetProfileUse> {
        return service.getUserProfile(id)
    }

    override suspend fun getUserProfileDetails(id: Long): Response<GetProfileDetails> {
        return service.getUserProfileDetails(id)
    }

    override suspend fun updateUsers(
        id: Long,
        updateUserInput: UpdateUserInput
    ): Response<GetUsersOutput> {
        return service.updateUsers(id, updateUserInput);
    }

    override suspend fun deleteUsers(id: Long): Void {
       return service.deleteUsers(id);
    }

}