package elder.ly.mobile.data.repository.user

import elder.ly.mobile.domain.model.Specialtie
import elder.ly.mobile.domain.model.User
import elder.ly.mobile.domain.model.enums.GenderEnum
import elder.ly.mobile.domain.model.enums.TypeUserEnum
import elder.ly.mobile.domain.service.AddressBairro
import elder.ly.mobile.domain.service.AddressOutput
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

class UserRepositoryLocalImpl(
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
        return service.getUser(id)
    }

    override suspend fun getUserProfile(id: Long): Response<GetProfileUse> {
        return Response.success(
            GetProfileUse (
                name = "Gerson",
                profilePicture = "https://cdn-icons-png.flaticon.com/512/3135/3135715.png",
            )
        )
    }

    override suspend fun getUserProfileDetails(id: Long): Response<GetProfileDetails> {
        val specialtyList = listOf(
            Specialtie(id = 1, name = "Enfermagem"),
            Specialtie(id = 2, name = "Fisioterapia"),
            Specialtie(id = 3, name = "Cuidados Paliativos")
        )

        return Response.success(
            GetProfileDetails (
                name = "Gerson",
                profilePicture = "https://cdn-icons-png.flaticon.com/512/3135/3135715.png",
                address = AddressBairro("Santana"),
                biography = "Sou cuidadora de idosos com mais de 10 anos de experiência, apaixonada por oferecer um atendimento acolhedor e respeitoso. Meu objetivo é garantir conforto e segurança aos meus pacientes, promovendo sua autonomia. Já trabalhei com idosos de diferentes perfis, incluindo aqueles com doenças crônicas. Gosto de criar momentos de alegria através de conversas e atividades recreativas. Acredito que cada dia é uma nova chance de fazer a diferença na vida de alguém.",
                specialties = specialtyList,
                price = ""
            )
        )
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