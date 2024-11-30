package elder.ly.mobile.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import elder.ly.mobile.data.repository.user.IUserRepository
import elder.ly.mobile.domain.service.GetUsersOutput
import elder.ly.mobile.domain.service.UpdateClientInput
import elder.ly.mobile.domain.service.UpdateUserInput
import elder.ly.mobile.ui.composables.stateholders.UserStateHolder
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PersonalInfoViewModel(private val userRepository: IUserRepository) : ViewModel() {
    var userId by mutableLongStateOf(-1L)

    // Armazena os dados do usuário
    private val _user = MutableStateFlow<GetUsersOutput?>(null)
    val user: StateFlow<GetUsersOutput?> = _user

    // Estado de criação/atualização do usuário
    private val _userCreationStatus = MutableStateFlow<UserStateHolder>(UserStateHolder.Loading)
    val userCreationStatus: StateFlow<UserStateHolder> = _userCreationStatus

    // Estado de carregamento e erro
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    // Função para buscar dados do usuário
    fun getUser() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            try {
                val response = userRepository.getUser(userId)
                if (response.isSuccessful) {
                    response.body()?.let { userData ->
                        _user.value = userData } ?: run {
                        _error.value = "Os dados do usuário não foram encontrados."
                    }
                } else {
                    _error.value = "Erro ao buscar dados do usuário: Código ${response.code()}"
                }
            } catch (e: Exception) {
                _error.value = "Erro ao buscar dados do usuário: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun updateUser(
        id: Long,
        updateClientInput: UpdateClientInput
    ) {
        viewModelScope.launch {
            _userCreationStatus.value = UserStateHolder.Loading
            try {
                val updateUserInput = UpdateUserInput(
                    nome = updateClientInput.nome,
                    email = updateClientInput.email,
                    documento = updateClientInput.documento,
                    dataNascimento = updateClientInput.dataNascimento,
                    biografia = updateClientInput.biografia,
                    fotoPerfil = null,
                    genero = updateClientInput.genero,
                    updateAddressInput = null,
                    especialidades = updateClientInput.especialidades
                )

                // Envia o objeto para a API
                val response = userRepository.updateUsers(id, updateUserInput)

                if (response.isSuccessful) {
                    response.body()?.let { updatedUser ->
                        _user.value = updatedUser // Atualiza `_user` com os dados do usuário
                        _userCreationStatus.value = UserStateHolder.Content(updatedUser)
                    } ?: run {
                        _userCreationStatus.value = UserStateHolder.Error("Resposta vazia do servidor.")
                    }
                } else {
                    _userCreationStatus.value = UserStateHolder.Error("Erro: ${response.code()}")
                }
            } catch (e: Exception) {
                _userCreationStatus.value = UserStateHolder.Error("Erro: ${e.message}")
            }
        }
    }
}
