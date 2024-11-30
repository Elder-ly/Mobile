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
            nome = "TesteName",
            email = "a@a.com",
            documento = "0",
            dataNascimento = null,
            biografia = null,
            fotoPerfil = "https://static.wikia.nocookie.net/sonic/images/c/c2/PSN_Avatar_Shadow.png",
            tipoUsuario = 2.toLong(),
            genero = 2.toLong(),
            endereco = AddressOutput(
                id = 200.toLong(),
                cep = "01001000",
                logradouro = "Teste",
                complemento = "Teste",
                bairro = "Artur Alvim",
                numero = "1",
                cidade = "São Paulo",
                uf = "SP"
            ),
            especialidades = List(2) {
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