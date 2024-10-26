package elder.ly.mobile.repository.message

import elder.ly.mobile.service.CreateMessageInput
import elder.ly.mobile.service.GetMessageOutput
import elder.ly.mobile.service.MessageWithProposalOutput
import retrofit2.Response

interface IMessageRepository {

    suspend fun createMessage(createMessageInput : CreateMessageInput) : Response<GetMessageOutput>

    suspend fun getMessageUser(senderId: Long, recipientId: Long) : Response<List<MessageWithProposalOutput>>

    suspend fun getConversations(userId: Long) : Response<List<GetMessageOutput>>
}