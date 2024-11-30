package elder.ly.mobile.data.repository.message

import elder.ly.mobile.domain.service.CreateMessageInput
import elder.ly.mobile.domain.service.GetMessageOutput
import elder.ly.mobile.domain.service.MessageService
import elder.ly.mobile.domain.service.MessageWithProposalOutput
import elder.ly.mobile.domain.service.UserConversationOutput
import retrofit2.Response

class MessageRepository(
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
        return service.getConversations(userId);
    }

}