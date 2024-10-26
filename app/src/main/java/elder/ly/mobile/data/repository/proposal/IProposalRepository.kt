package elder.ly.mobile.repository.proposal

import elder.ly.mobile.service.MessageWithProposalInput
import elder.ly.mobile.service.MessageWithProposalOutput
import elder.ly.mobile.service.ProposalOutput
import retrofit2.Response

interface IProposalRepository {

    suspend fun createProposal(messageWithProposalInput: MessageWithProposalInput) : Response<MessageWithProposalOutput>

    suspend fun acceptProposal(accessToken : String, proposalId: Long) : Response<ProposalOutput>
}