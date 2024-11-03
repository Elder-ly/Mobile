package elder.ly.mobile.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import elder.ly.mobile.data.repository.message.IMessageRepository
import elder.ly.mobile.domain.service.UserConversationOutput
import kotlinx.coroutines.launch


class ChatListViewModel(
    private val messageRepository: IMessageRepository
) : ViewModel() {

    var conversations = mutableListOf<UserConversationOutput>();

    var message by mutableStateOf("")

    var isLoading by mutableStateOf(false)

    init {
        loadConversations()
    }

    fun loadConversations() {
        isLoading = true

        viewModelScope.launch {
            val response = messageRepository.getConversations(1)

            if (response.isSuccessful) {
                conversations = response.body() as MutableList<UserConversationOutput>
            } else {
                message = "Ocorreu um erro ao buscar as conversas.\nVerifique sua conexão\n(${response.code()})"
            }

            isLoading = false
        }
    }
}