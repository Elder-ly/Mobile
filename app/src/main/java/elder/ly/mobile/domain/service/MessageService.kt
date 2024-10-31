package elder.ly.mobile.domain.service

import elder.ly.mobile.domain.model.Residence
import elder.ly.mobile.domain.model.Resumes
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
    suspend fun getConversations(@Path("userId") userId: Long) : Response<List<GetMessageOutput>>
}

data class CreateMessageInput(
    val senderId: Long,
    val recipientId: Long,
    val content: String
)

data class GetMessageOutput(
    val id: Long,
    val content: String,
    val dateTime: LocalDateTime,
    val sender: UserMessageOutput,
    val recipient: UserMessageOutput
)

data class UserMessageOutput(
    val id: Long,
    val name: String
)

data class MessageWithProposalOutput(
    val id: Long,
    val content: String,
    val dateTime: LocalDateTime,
    val sender: UserMessageOutput,
    val recipient: UserMessageOutput,
    val proposal: ProposalOutput
)

data class ProposalOutput(
    val id: Long,
    val description: String,
    val startDateTime: LocalDateTime,
    val endDateTime: LocalDateTime,
    val price: BigDecimal,
    val accepted: Boolean
)

data class UserConversationOutput(
    val id: Long,
    val name: String,
    val profilePicture: String?,
    val residences: List<Residence> = emptyList(),
    val resumes: List<Resumes> = emptyList()
) {
    // Obtém o endereço resumido a partir da primeira residência, se disponível
    fun getAddress(): SimplifiedAddress? {
        if (residences.isEmpty()) {
            return null
        }
        return SimplifiedAddress(
            residences[0].address.neighborhood ?: "",
            residences[0].address.city ?: ""
        )
    }
}

data class SimplifiedAddress(
    val neighborhood: String,
    val city: String
)