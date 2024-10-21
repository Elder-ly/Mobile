package elder.ly.mobile.service

import android.adservices.adid.AdId
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import java.math.BigDecimal
import java.time.LocalDateTime

interface ProposalService {

    @POST("/propostas")
    suspend fun createProposal(@Body messageWithProposalInput: MessageWithProposalInput) : Response<MessageWithProposalOutput>

    @PATCH("/propostas/aceitar/{idProposta}")
    suspend fun acceptProposal(@Path("idProposta") proposalId: Long) : Response<ProposalOutput>
}

data class MessageWithProposalInput(
    val senderId: Long,
    val recipientId: Long,
    val content: String,
    val proposal: ProposalInput
)

data class ProposalInput(
    val description: String,
    val startDateTime: LocalDateTime,
    val endDateTime: LocalDateTime,
    val price: BigDecimal
)
