package elder.ly.mobile.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
    private val _address = MutableLiveData<AddressOutput?>()
    val address: LiveData<AddressOutput?> = _address

    private val _userCreationStatus = MutableLiveData<AddressStateHolder>()
    val addressCreationStatus: LiveData<AddressStateHolder> = _userCreationStatus

    // Estado de carregamento e erro
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun getAddress() {
        viewModelScope.launch {
            _isLoading.postValue(true)
            _error.postValue(null)

            try {
                val response = addressesRepository.getAddresses(userId)
                if (response.isSuccessful) {
                    response.body()?.let { userData ->
                        _address.postValue(userData)
                    } ?: run {
                        _error.postValue("Os dados do usuário não foram encontrados.")
                    }
                } else {
                    _error.postValue("Erro ao buscar dados do usuário: Código ${response.code()}")
                }
            } catch (e: Exception) {
                _error.postValue("Erro ao buscar dados do usuário: ${e.message}")
            } finally {
                _isLoading.postValue(false)
            }
        }
    }

    fun updateAddress(id: Long, updateAddressInput: UpdateAddressInput) {
        viewModelScope.launch {
            _userCreationStatus.value = AddressStateHolder.Loading
            try {
                val response = addressesRepository.updateAddresses(id, updateAddressInput)

                if (response.isSuccessful) {
                    response.body()?.let { updatedAddress ->
                        _address.postValue(updatedAddress)
                        _userCreationStatus.postValue(AddressStateHolder.Content(updatedAddress))
                    } ?: run {
                        _userCreationStatus.postValue(AddressStateHolder.Error("Resposta vazia do servidor."))
                    }
                } else {
                    _userCreationStatus.postValue(AddressStateHolder.Error("Erro: ${response.code()}"))
                }
            } catch (e: Exception) {
                _userCreationStatus.postValue(AddressStateHolder.Error("Erro: ${e.message}"))
            }
        }
    }
}