package elder.ly.mobile.repository.message

import elder.ly.mobile.service.CreateMessageInput
import elder.ly.mobile.service.GetMessageOutput
import elder.ly.mobile.service.MessageService
import elder.ly.mobile.service.MessageWithProposalOutput
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

    override suspend fun getConversations(userId: Long): Response<List<GetMessageOutput>> {
        return service.getConversations(userId);
    }

}