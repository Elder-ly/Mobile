package elder.ly.mobile.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import elder.ly.mobile.data.repository.user.IUserRepository
import elder.ly.mobile.domain.service.GetUsersOutput
import elder.ly.mobile.domain.service.UpdateAddressInput
import elder.ly.mobile.domain.service.UpdateUserInput
import elder.ly.mobile.ui.composables.stateholders.CreateStateHolder
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AddressInfoViewModel(
    private val userRepository: IUserRepository
) : ViewModel() {

    // Armazena os dados do usuário
    private val _user = MutableStateFlow<GetUsersOutput?>(null)
    val user: StateFlow<GetUsersOutput?> = _user

    private val _userCreationStatus = MutableStateFlow<CreateStateHolder>(CreateStateHolder.Loading)
    val userCreationStatus: StateFlow<CreateStateHolder> = _userCreationStatus

    // Estado de carregamento e erro
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        getUser()
    }

    // Função para buscar dados do usuário
    fun getUser() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            try {
                val response = userRepository.getUser(6)
                if (response.isSuccessful) {
                    _user.value = response.body()
                } else {
                    _error.value = "Erro ao buscar dados do usuário: ${response.code()}"
                }
            } catch (e: Exception) {
                _error.value = "Erro: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}