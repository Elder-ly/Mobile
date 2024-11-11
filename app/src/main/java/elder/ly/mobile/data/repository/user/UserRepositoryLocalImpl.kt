package elder.ly.mobile.data.repository.user

import elder.ly.mobile.domain.model.Specialtie
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
            Specialtie(id = 1, nome = "Enfermagem"),
            Specialtie(id = 2, nome = "Fisioterapia"),
            Specialtie(id = 3, nome = "Cuidados Paliativos")
        )

        return Response.success(
            listOf(
                GetUsersCollaboratorOutput(
                    id = 1,
                    nome = "Bruno",
                    email = "bruno@gmail.com",
                    documento = "403.980.471-02",
                    dataNascimento = "21/02/2001",
                    fotoPerfil = "https://w7.pngwing.com/pngs/426/286/png-transparent-goku-ultra-instinct-thumbnail.png",
                    biografia = "",
                    endereco = AddressOutput(
                        id = 1, cep = "12345-678", logradouro = "Rua das Flores", complemento = "Apto 101", bairro = "Centro", numero = "100", cidade = "São Paulo", uf = "SP"
                    ),
                    especialidades = specialtyList
                ),
                GetUsersCollaboratorOutput(
                    id = 2,
                    nome = "Maria",
                    email = "maria@gmail.com",
                    documento = "123.456.789-01",
                    dataNascimento = "15/05/1998",
                    fotoPerfil = "https://w7.pngwing.com/pngs/422/965/png-transparent-dragonball-z-son-goku-goku-dragon-ball-z-dokkan-battle-trunks-dragon-ball-xenoverse-2-vegeta-goku-cartoon-fictional-character-anime-thumbnail.png",
                    biografia = "Experiência em atendimento domiciliar para idosos e reabilitação física.",
                    endereco = AddressOutput(
                        id = 2,
                        cep = "87654-321",
                        logradouro = "Avenida Paulista",
                        complemento = "Sala 12",
                        bairro = "Bela Vista",
                        numero = "1200",
                        cidade = "São Paulo",
                        uf = "SP"
                    ),
                    especialidades = listOf(
                        Specialtie(id = 4, nome = "Psicologia"),
                        Specialtie(id = 5, nome = "Terapia Ocupacional")
                    )
                ),
                GetUsersCollaboratorOutput(
                    id = 3,
                    nome = "Carlos",
                    email = "carlos@gmail.com",
                    documento = "789.456.123-98",
                    dataNascimento = "10/09/1985",
                    fotoPerfil = "https://w7.pngwing.com/pngs/767/162/png-transparent-goku-face-dragon-ball-cartoon-goku-thumbnail.png",
                    biografia = "Profissional com vasta experiência em cuidados para pacientes acamados.",
                    endereco = AddressOutput(
                        id = 3,
                        cep = "54321-987",
                        logradouro = "Rua do Comércio",
                        complemento = "Casa",
                        bairro = "Jardins",
                        numero = "250",
                        cidade = "Rio de Janeiro",
                        uf = "RJ"
                    ),
                    especialidades = listOf(
                        Specialtie(id = 6, nome = "Cuidador de Idosos"),
                        Specialtie(id = 7, nome = "Nutrição")
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
            Specialtie(id = 1, nome = "Enfermagem"),
            Specialtie(id = 2, nome = "Fisioterapia"),
            Specialtie(id = 3, nome = "Cuidados Paliativos")
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
    ): Response<GetUsersOutput> {
        return service.updateUsers(id, updateUserInput);
    }

    override suspend fun deleteUsers(id: Long): Void {
       return service.deleteUsers(id);
    }

}