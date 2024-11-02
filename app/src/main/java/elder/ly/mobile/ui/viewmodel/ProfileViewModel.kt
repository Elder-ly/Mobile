package elder.ly.mobile.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import elder.ly.mobile.data.repository.user.IUserRepository
import elder.ly.mobile.domain.model.User
import elder.ly.mobile.utils.getUser
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val userRepository: IUserRepository
) : ViewModel() {

    var url by mutableStateOf("")
    var nome by mutableStateOf("")

    init {
        getUserProfile()
    }


    fun getUserProfile(){
        viewModelScope.launch {
            val response = userRepository.getUserProfile(1)

            if(response.isSuccessful){
                nome = response.body()?.name!!
                url = response.body()?.profilePicture!!
            }
        }
    }
}