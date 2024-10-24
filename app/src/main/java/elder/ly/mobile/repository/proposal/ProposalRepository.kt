package elder.ly.mobile.repository.proposal

import elder.ly.mobile.service.MessageWithProposalInput
import elder.ly.mobile.service.MessageWithProposalOutput
import elder.ly.mobile.service.ProposalOutput
import elder.ly.mobile.service.ProposalService
import retrofit2.Response

class ProposalRepository(
    private val service : ProposalService
) : IProposalRepository {
    override suspend fun createProposal(messageWithProposalInput: MessageWithProposalInput): Response<MessageWithProposalOutput> {
        return service.createProposal(messageWithProposalInput);
    }

    override suspend fun acceptProposal(
        accessToken: String,
        proposalId: Long
    ): Response<ProposalOutput> {
        return service.acceptProposal(accessToken, proposalId);
    }

}