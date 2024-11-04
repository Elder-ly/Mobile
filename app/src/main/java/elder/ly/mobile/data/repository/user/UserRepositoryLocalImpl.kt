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
        val specialtyList = listOf(
            Specialtie(id = 1, name = "Enfermagem"),
            Specialtie(id = 2, name = "Fisioterapia"),
            Specialtie(id = 3, name = "Cuidados Paliativos")
        )

        return Response.success(
            listOf(
                GetUsersCollaboratorOutput(
                    id = 1,
                    name = "Bruno",
                    email = "bruno@gmail.com",
                    document = "403.980.471-02",
                    birthDate = "21/02/2001",
                    profilePicture = "https://w7.pngwing.com/pngs/426/286/png-transparent-goku-ultra-instinct-thumbnail.png",
                    biography = "",
                    address = AddressOutput(
                        id = 1, cep = "12345-678", street = "Rua das Flores", complement = "Apto 101", neighborhood = "Centro", number = "100", city = "São Paulo", state = "SP"
                    ),
                    specialties = specialtyList
                ),
                GetUsersCollaboratorOutput(
                    id = 2,
                    name = "Maria",
                    email = "maria@gmail.com",
                    document = "123.456.789-01",
                    birthDate = "15/05/1998",
                    profilePicture = "https://w7.pngwing.com/pngs/422/965/png-transparent-dragonball-z-son-goku-goku-dragon-ball-z-dokkan-battle-trunks-dragon-ball-xenoverse-2-vegeta-goku-cartoon-fictional-character-anime-thumbnail.png",
                    biography = "Experiência em atendimento domiciliar para idosos e reabilitação física.",
                    address = AddressOutput(
                        id = 2,
                        cep = "87654-321",
                        street = "Avenida Paulista",
                        complement = "Sala 12",
                        neighborhood = "Bela Vista",
                        number = "1200",
                        city = "São Paulo",
                        state = "SP"
                    ),
                    specialties = listOf(
                        Specialtie(id = 4, name = "Psicologia"),
                        Specialtie(id = 5, name = "Terapia Ocupacional")
                    )
                ),
                GetUsersCollaboratorOutput(
                    id = 3,
                    name = "Carlos",
                    email = "carlos@gmail.com",
                    document = "789.456.123-98",
                    birthDate = "10/09/1985",
                    profilePicture = "https://w7.pngwing.com/pngs/767/162/png-transparent-goku-face-dragon-ball-cartoon-goku-thumbnail.png",
                    biography = "Profissional com vasta experiência em cuidados para pacientes acamados.",
                    address = AddressOutput(
                        id = 3,
                        cep = "54321-987",
                        street = "Rua do Comércio",
                        complement = "Casa",
                        neighborhood = "Jardins",
                        number = "250",
                        city = "Rio de Janeiro",
                        state = "RJ"
                    ),
                    specialties = listOf(
                        Specialtie(id = 6, name = "Cuidador de Idosos"),
                        Specialtie(id = 7, name = "Nutrição")
                    )
                )
            )
        )
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
                id = 1,
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