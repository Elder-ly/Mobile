package elder.ly.mobile.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import elder.ly.mobile.data.repository.user.IUserRepository
import elder.ly.mobile.domain.model.Specialtie
import kotlinx.coroutines.launch

class SearchResultViewModel(
    private val userRepository: IUserRepository
) : ViewModel() {

    var url by mutableStateOf("")
    var nome by mutableStateOf("")
    var bairro by mutableStateOf("")
    var biografia by mutableStateOf("")
    var especialidades by mutableStateOf<List<Specialtie>>(emptyList())
    var preco by mutableStateOf("")

    init {
        getUserProfileDetails()
    }

    private fun getUserProfileDetails(){
        viewModelScope.launch {
            val response = userRepository.getUserProfileDetails(1)

            if(response.isSuccessful){
                nome = response.body()?.name!!
                url = response.body()?.profilePicture!!
                bairro = response.body()?.address?.neighborhood ?: ""
                biografia = response.body()?.biography ?: ""
                especialidades = response.body()?.specialties ?: emptyList()
                preco = response.body()?.price ?: ""
            }
        }
    }
}