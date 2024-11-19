package elder.ly.mobile.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import elder.ly.mobile.data.repository.user.IUserRepository
import elder.ly.mobile.domain.model.Specialtie
import elder.ly.mobile.domain.service.GetDataSearchScreen
import elder.ly.mobile.domain.service.GetUsersCollaboratorInput
import elder.ly.mobile.domain.service.GetUsersCollaboratorOutput
import elder.ly.mobile.utils.DateTimeUtils
import elder.ly.mobile.utils.DateTimeUtils.convertToIso8601
import kotlinx.coroutines.launch

class SearchResultViewModel(
    private val userRepository: IUserRepository
) : ViewModel() {

    val cuidadores = mutableStateListOf<GetUsersCollaboratorOutput>()

    var isLoading by mutableStateOf(false)


    fun getUsersCollaborator(token : String, getDataSearchScreen: GetDataSearchScreen) {
        isLoading = true
        viewModelScope.launch {
            val getUsersCollaboratorInput = GetUsersCollaboratorInput(
                especialidades = getDataSearchScreen.specialties,
                dataHoraInicio = convertToIso8601(getDataSearchScreen.startDate + getDataSearchScreen.startTime),
                dataHoraFim = convertToIso8601(getDataSearchScreen.endDate + getDataSearchScreen.endTime)
            )

            val response = userRepository.getAvailableCollaborators(token, getUsersCollaboratorInput)

            if (response.isSuccessful) {
                response.body()?.let { colaboradores ->
                    cuidadores.addAll(response.body()!!)
                }
            } else {
                // Lidar com erro na resposta (ex: mostrar mensagem de erro)
            }

            isLoading = false
        }
    }
}