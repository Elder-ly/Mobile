package elder.ly.mobile.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import elder.ly.mobile.data.repository.user.IUserRepository
import elder.ly.mobile.domain.model.Specialtie
import elder.ly.mobile.domain.service.GetUsersCollaboratorOutput
import kotlinx.coroutines.launch

class SearchResultViewModel(
    private val userRepository: IUserRepository
) : ViewModel() {

    val cuidadores = mutableStateListOf<GetUsersCollaboratorOutput>()


    init {
        getUsersCollaborator()
    }

    private fun getUsersCollaborator() {
        viewModelScope.launch {
            val response = userRepository.getUsersCollaborator()

            if (response.isSuccessful) {
                response.body()?.let { colaboradores ->
                    cuidadores.addAll(colaboradores)
                }
            } else {
                // Lidar com erro na resposta (ex: mostrar mensagem de erro)
            }
        }
    }
}