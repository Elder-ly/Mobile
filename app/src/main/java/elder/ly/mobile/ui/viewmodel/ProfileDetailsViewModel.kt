package elder.ly.mobile.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import elder.ly.mobile.data.repository.user.IUserRepository
import elder.ly.mobile.domain.model.Specialtie
import elder.ly.mobile.domain.service.GetUsersOutput
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileDetailsViewModel(
    private val userRepository: IUserRepository
) : ViewModel() {

    private val _user = MutableStateFlow<GetUsersOutput?>(null)
    val user: StateFlow<GetUsersOutput?> = _user
    var userId by mutableLongStateOf((-1).toLong())

    fun getUserProfileDetails() {
        viewModelScope.launch {
            val response = userRepository.getUser(userId)

            if (response.isSuccessful) {
                val userResponse = response.body()
                if (userResponse != null) {
                    _user.value = userResponse
                    // Adicione um log para verificar os dados recebidos
                    Log.d("ProfileDetailsViewModel", "Usuário recebido: $userResponse")
                    Log.d(
                        "ProfileDetailsViewModel",
                        "Especialidades recebidas: ${userResponse.especialidades}"
                    )
                } else {
                    Log.e("ProfileDetailsViewModel", "A resposta da API é nula.")
                }
            } else {
                Log.e(
                    "ProfileDetailsViewModel",
                    "Erro na resposta da API: ${response.errorBody()?.string()}"
                )
            }
        }
    }
}