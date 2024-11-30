package elder.ly.mobile.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import elder.ly.mobile.data.repository.addresses.IAddressRepository
import elder.ly.mobile.domain.service.AddressOutput
import elder.ly.mobile.domain.service.UpdateAddressInput
import elder.ly.mobile.ui.composables.stateholders.AddressStateHolder
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AddressInfoViewModel(
    private val addressesRepository: IAddressRepository
) : ViewModel() {
    var userId by mutableLongStateOf(-1L)

    // Armazena os dados do usuário
    private val _address = MutableStateFlow<AddressOutput?>(null)
    val address: StateFlow<AddressOutput?> = _address

    private val _userCreationStatus = MutableStateFlow<AddressStateHolder>(AddressStateHolder.Loading)
    val addressCreationStatus: StateFlow<AddressStateHolder> = _userCreationStatus

    // Estado de carregamento e erro
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    // Função para buscar dados do usuário
    fun getAddress() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            try {
                val response = addressesRepository.getAddresses(userId)
                if (response.isSuccessful) {
                    response.body()?.let { userData ->
                        _address.value = userData } ?: run {
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

    fun updateAddress(id: Long, updateAddressInput: UpdateAddressInput) {
        viewModelScope.launch {
            _userCreationStatus.value = AddressStateHolder.Loading
            try {
                // Envia o objeto para a API
                val response = addressesRepository.updateAddresses(id, updateAddressInput)

                if (response.isSuccessful) {
                    response.body()?.let { updatedAddress ->
                        _address.value = updatedAddress // Atualiza `_user` com os dados do usuário
                        _userCreationStatus.value = AddressStateHolder.Content(updatedAddress)
                    } ?: run {
                        _userCreationStatus.value = AddressStateHolder.Error("Resposta vazia do servidor.")
                    }
                } else {
                    _userCreationStatus.value = AddressStateHolder.Error("Erro: ${response.code()}")
                }
            } catch (e: Exception) {
                _userCreationStatus.value = AddressStateHolder.Error("Erro: ${e.message}")
            }
        }
    }
}