package elder.ly.mobile.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import elder.ly.mobile.data.repository.user.IUserRepository
import elder.ly.mobile.domain.model.User
import elder.ly.mobile.domain.service.GetUsersOutput
import elder.ly.mobile.utils.getUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val userRepository: IUserRepository
) : ViewModel() {

    private val _user = MutableStateFlow<GetUsersOutput?>(null)
    val user: StateFlow<GetUsersOutput?> = _user

    init {
        getUserProfile()
    }

    private fun getUserProfile() {
        viewModelScope.launch {
            val response = userRepository.getUser(6)

            if (response.isSuccessful) {
                val userResponse = response.body()
                if (userResponse != null) {
                    _user.value = userResponse
                    // Adicione um log para verificar os dados recebidos
                    Log.d("ProfileViewModel", "Usuário recebido: $userResponse")
                } else {
                    Log.e("ProfileViewModel", "A resposta da API é nula.")
                }
            } else {
                Log.e("ProfileViewModel", "Erro na resposta da API: ${response.errorBody()?.string()}")
            }
        }
    }
}