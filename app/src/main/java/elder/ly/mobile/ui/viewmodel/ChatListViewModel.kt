package elder.ly.mobile.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import elder.ly.mobile.data.repository.message.IMessageRepository
import elder.ly.mobile.domain.service.UserConversationOutput
import kotlinx.coroutines.launch


class ChatListViewModel(
    private val messageRepository: IMessageRepository
) : ViewModel() {

    var userId by mutableLongStateOf(-1L)

    var conversations = mutableStateListOf<UserConversationOutput>();

    var message by mutableStateOf("")

    var isLoading by mutableStateOf(false)

    fun loadConversations() {
        isLoading = true

        viewModelScope.launch {
            val response = messageRepository.getConversations(userId)

            if (response.isSuccessful) {
                conversations.clear()
                response.body()?.let { conversations.addAll(it) }
                Log.d("ChatListViewModel", "Conversas carregadas: $conversations")
            } else {
                message = "Ocorreu um erro ao buscar as conversas.\nVerifique sua conexão\n(${response.code()})"
            }

            isLoading = false
        }
    }
}