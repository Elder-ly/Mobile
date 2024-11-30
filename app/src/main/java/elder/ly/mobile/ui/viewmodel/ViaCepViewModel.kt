package elder.ly.mobile.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import elder.ly.mobile.data.repository.viacep.IViaCepRepository
import elder.ly.mobile.domain.service.AddressResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ViaCepViewModel(
    private val repository: IViaCepRepository
) : ViewModel() {
    // Expor os dados do endereço
    private val _address = MutableStateFlow<AddressResponse?>(null)
    val address: StateFlow<AddressResponse?> = _address

    // Expor o estado de erro
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    // Método para buscar endereço por CEP
    fun fetchAddress(cep : String) {
        viewModelScope.launch {
            try {
                _error.value = null
                val result = repository.getAddress(cep)
                _address.value = result.body()
            } catch (e: Exception) {
                _error.value = "Failed to fetch address: ${e.message}"
                _address.value = null
            }
        }
    }
}