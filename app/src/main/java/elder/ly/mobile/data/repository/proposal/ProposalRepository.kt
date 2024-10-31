package elder.ly.mobile.data.repository.proposal

import elder.ly.mobile.data.repository.proposal.IProposalRepository
import elder.ly.mobile.domain.service.MessageWithProposalInput
import elder.ly.mobile.domain.service.MessageWithProposalOutput
import elder.ly.mobile.domain.service.ProposalOutput
import elder.ly.mobile.domain.service.ProposalService
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