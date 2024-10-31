package elder.ly.mobile.data.repository.message

import elder.ly.mobile.domain.service.CreateMessageInput
import elder.ly.mobile.domain.service.GetMessageOutput
import elder.ly.mobile.domain.service.MessageWithProposalOutput
import retrofit2.Response

interface IMessageRepository {

    suspend fun createMessage(createMessageInput : CreateMessageInput) : Response<GetMessageOutput>

    suspend fun getMessageUser(senderId: Long, recipientId: Long) : Response<List<MessageWithProposalOutput>>

    suspend fun getConversations(userId: Long) : Response<List<GetMessageOutput>>
}