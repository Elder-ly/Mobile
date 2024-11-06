package elder.ly.mobile.data.repository.message

import elder.ly.mobile.domain.service.AddressOutput
import elder.ly.mobile.domain.service.CreateMessageInput
import elder.ly.mobile.domain.service.GetMessageOutput
import elder.ly.mobile.domain.service.GetUsersOutput
import elder.ly.mobile.domain.service.MessageService
import elder.ly.mobile.domain.service.MessageWithProposalOutput
import elder.ly.mobile.domain.service.ResidenceOutput
import elder.ly.mobile.domain.service.ResumeOutput
import elder.ly.mobile.domain.service.SpecialtieOutput
import elder.ly.mobile.domain.service.UserConversationOutput
import retrofit2.Response

class MessageRepositoryLocal(
    private val service : MessageService
) : IMessageRepository {
    override suspend fun createMessage(createMessageInput: CreateMessageInput): Response<GetMessageOutput> {
        return service.createMessage(createMessageInput);
    }

    override suspend fun getMessageUser(
        senderId: Long,
        recipientId: Long
    ): Response<List<MessageWithProposalOutput>> {
        return service.getMessageUser(senderId, recipientId);
    }

    override suspend fun getConversations(userId: Long): Response<List<UserConversationOutput>> {
        val user = GetUsersOutput(
            id = 100.toLong(),
            name = "TesteName",
            email = "a@a.com",
            document = "0",
            birthDate = null,
            biography = null,
            profilePicture = "https://static.wikia.nocookie.net/sonic/images/c/c2/PSN_Avatar_Shadow.png",
            userType = 2.toLong(),
            gender = 2.toLong(),
            address = AddressOutput(
                id = 200.toLong(),
                cep = "01001000",
                street = "Teste",
                complement = "Teste",
                neighborhood = "Artur Alvim",
                number = "1",
                city = "São Paulo",
                state = "SP"
            ),
            specialties = List(2) {
                SpecialtieOutput(
                    id = 1.toLong(),
                    nome = "A $it"
                )
            }
        )

        return Response.success(List(10) {
            UserConversationOutput(
                id = it.toLong(),
                nome = "Shadow Fan #$it",
                fotoPerfil = "https://static.wikia.nocookie.net/sonic/images/c/c2/PSN_Avatar_Shadow.png",
                endereco = ResidenceOutput(
                    bairro = "Artur Alvim",
                    cidade = "São Paulo"
                ),
                especialidades = List(1) {
                    SpecialtieOutput(
                        id = it.toLong(),
                        nome = "A $it"
                    )
                }
            )
        });
    }

}