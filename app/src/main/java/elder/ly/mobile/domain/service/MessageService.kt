package elder.ly.mobile.domain.service

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import java.math.BigDecimal
import java.time.LocalDateTime

interface MessageService {

    @POST("/mensagens")
    suspend fun createMessage(@Body createMessageInput : CreateMessageInput) : Response<GetMessageOutput>

    @GET("/mensagens/{remetenteId}/{destinatarioId}")
    suspend fun getMessageUser(@Path("remetenteId") senderId: Long, @Path("destinatarioId") recipientId: Long) : Response<List<MessageWithProposalOutput>>

    @GET("/mensagens/conversas/{userId}")
    suspend fun getConversations(@Path("userId") userId: Long) : Response<List<UserConversationOutput>>
}

data class CreateMessageInput(
    val remetenteId: Long,
    val destinatarioId: Long,
    val conteudo: String
)

data class GetMessageOutput(
    val id: Long,
    val conteudo: String,
    val dataHora: String,
    val remetente: UserMessageOutput,
    val destinatario: UserMessageOutput
)

data class UserMessageOutput(
    val id: Long,
    val nome: String
)

data class MessageWithProposalOutput(
    val id: Long,
    val conteudo: String,
    val dataHora: String,
    val remetente: UserMessageOutput,
    val destinatario: UserMessageOutput,
    val proposta: ProposalOutput
)

data class ProposalOutput(
    val id: Long,
    val descricao: String,
    val dataHoraInicio: LocalDateTime,
    val dataHoraFim: LocalDateTime,
    val preco: BigDecimal,
    val aceita: Boolean
)

data class UserConversationOutput(
    val id: Long,
    val nome: String,
    val fotoPerfil: String?,
    val endereco: ResidenceOutput,
    val especialidades: List<SpecialtieOutput> = emptyList()
)

data class SimplifiedAddress(
    val neighborhood: String,
    val city: String
)