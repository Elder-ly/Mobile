package elder.ly.mobile.data.repository.proposal

import elder.ly.mobile.domain.service.MessageWithProposalInput
import elder.ly.mobile.domain.service.MessageWithProposalOutput
import elder.ly.mobile.domain.service.ProposalOutput
import retrofit2.Response

interface IProposalRepository {

    suspend fun createProposal(messageWithProposalInput: MessageWithProposalInput) : Response<MessageWithProposalOutput>

    suspend fun acceptProposal(accessToken : String, proposalId: Long) : Response<ProposalOutput>
}