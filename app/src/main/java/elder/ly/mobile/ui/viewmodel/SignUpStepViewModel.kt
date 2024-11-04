package elder.ly.mobile.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import elder.ly.mobile.data.repository.user.IUserRepository
import elder.ly.mobile.domain.service.CreateAddressInput
import elder.ly.mobile.domain.service.CreateClientInput
import elder.ly.mobile.domain.service.CreateUserInput
import elder.ly.mobile.ui.composables.stateholders.CreateStateHolder
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SignUpStepViewModel(
    private val userRepository : IUserRepository
) : ViewModel() {
    private val _userCreationStatus = MutableStateFlow<CreateStateHolder>(CreateStateHolder.Loading)
    val userCreationStatus: StateFlow<CreateStateHolder> = _userCreationStatus

    // Função para enviar os dados ao servidor
    fun createUser(createClientInput: CreateClientInput, createAddressInput: CreateAddressInput) {
        viewModelScope.launch {
            _userCreationStatus.value = CreateStateHolder.Loading
            try {
                // Criação do `CreateUserInput` com os dados de `CreateClientInput` e `CreateAddressInput`
                val completeUserInput = CreateUserInput(
                        nome = createClientInput.nome,
                        email = createClientInput.email,
                        documento = createClientInput.documento,
                        dataNascimento = createClientInput.dataNascimento,
                        biografia = createClientInput.biografia,
                        fotoPerfil = createClientInput.fotoPerfil,
                        tipoUsuario = createClientInput.tipoUsuario,
                        genero = createClientInput.genero,
                        endereco = createAddressInput, // Passa o endereço completo usando `createAddressInput`
                        especialidades = createClientInput.especialidades
                )

                // Enviar o objeto completo para a API
                val response = userRepository.createUserClient(completeUserInput)

                // Verificar a resposta da API
                if (response.isSuccessful) {
                    response.body()?.let { user ->
                        _userCreationStatus.value = CreateStateHolder.Content(user) // Atualiza o status com os dados do usuário criado
                    } ?: run {
                        _userCreationStatus.value = CreateStateHolder.Error("Resposta vazia do servidor")
                    }
                } else {
                    _userCreationStatus.value = CreateStateHolder.Error("Erro: ${response.code()}")
                }
            } catch (e: Exception) {
                _userCreationStatus.value = CreateStateHolder.Error("Erro: ${e.message}")
            }
        }
    }
}