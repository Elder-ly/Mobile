package elder.ly.mobile.domain.service

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import java.math.BigDecimal
import java.time.LocalDateTime

interface ProposalService {

    @POST("/propostas")
    suspend fun createProposal(@Body messageWithProposalInput: MessageWithProposalInput) : Response<MessageWithProposalOutput>

    @PATCH("/propostas/aceitar/{idProposta}")
    suspend fun acceptProposal(@Header("accessToken") accessToken : String , @Path("idProposta") proposalId: Long) : Response<ProposalOutput>
}

data class MessageWithProposalInput(
    val remetenteId: Long,
    val destinatarioId: Long,
    val conteudo: String,
    val proposta: ProposalInput
)

data class ProposalInput(
    val descricao: String,
    val dataHoraInicio: String,
    val dataHoraFim: String,
    val preco: BigDecimal
)

data class GetDataProposalScreen(
    val titulo : String,
    val descricao : String,
    val startDate: String,
    val endDate: String,
    val startTime: String,
    val endTime: String,
    val preco : BigDecimal
)