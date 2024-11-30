package elder.ly.mobile.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import elder.ly.mobile.data.repository.message.IMessageRepository
import elder.ly.mobile.data.repository.proposal.IProposalRepository
import elder.ly.mobile.domain.service.CreateMessageInput
import elder.ly.mobile.domain.service.MessageWithProposalOutput
import elder.ly.mobile.utils.getUser
import kotlinx.coroutines.launch

class ChatViewModel(
    private val messageRepository: IMessageRepository,
    private val proposalRepository: IProposalRepository,
) : ViewModel() {

    var isLoading by mutableStateOf(false)

    var senderId by mutableLongStateOf(-1L)

    var recipientId by mutableLongStateOf(-1L)

    var userType by mutableLongStateOf(-1L)

    var accessToken by mutableStateOf("")

    var messages = mutableStateListOf<MessageWithProposalOutput>()

    fun loadMessages() {
        if (senderId > 0 && recipientId > 0) {
            viewModelScope.launch {
                val response = messageRepository.getMessageUser(senderId = senderId, recipientId = recipientId)

                if (response.isSuccessful) {
                    messages.clear()
                    response.body()?.let { messages.addAll(it) }
                    messages.sortBy {
                        it.dataHora
                    }
                    Log.d("ChatViewModel", "Mensagens carregadas: $messages")
                }

                isLoading = false
            }
        }
    }

    fun sendMessages(content: String) {
        viewModelScope.launch {
            val response = messageRepository.createMessage(CreateMessageInput(
                conteudo = content,
                remetenteId = senderId,
                destinatarioId = recipientId
            ))
            if (response.isSuccessful) {
                Log.d("ChatViewModel", "Mensagem enviada com sucesso")
                loadMessages()
            } else {
                Log.d("ChatViewModel", "Erro ao enviar mensagem")
            }
        }
    }

    fun acceptProposal(proposalId: Long) {
        viewModelScope.launch {
            val response = proposalRepository.acceptProposal(accessToken, proposalId)
            if (response.isSuccessful) {
                Log.d("ChatViewModel", "Proposta aceita com sucesso")
                loadMessages()
            } else {
                Log.d("ChatViewModel", "Erro ao aceitar proposta")
            }
        }
    }
}