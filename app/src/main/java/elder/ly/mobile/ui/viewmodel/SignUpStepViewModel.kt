package elder.ly.mobile.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import elder.ly.mobile.data.repository.user.IUserRepository
import elder.ly.mobile.domain.model.enums.GenderEnum
import elder.ly.mobile.domain.model.enums.TypeUserEnum
import elder.ly.mobile.domain.service.CreateAddressInput
import elder.ly.mobile.domain.service.CreateUserInput
import elder.ly.mobile.domain.service.GetUsersOutput
import kotlinx.coroutines.launch
import retrofit2.Response
import java.time.LocalDate

class SignUpStepViewModel(
    private val userRepository : IUserRepository
) : ViewModel() {

    val userInput : MutableLiveData<CreateUserInput> by lazy {
        MutableLiveData<CreateUserInput>(CreateUserInput(
            name = "",
            email = "",
            document = "",
            birthDate = null,
            biography = null,
            profilePicture = null,
            userType = TypeUserEnum.CLIENT,
            gender = GenderEnum.PREFER_NOT_TO_SAY,
            address = CreateAddressInput(
                cep = "",
                street = "",
                complement = null,
                neighborhood = "",
                number = null,
                city = "",
                state = ""
            ),
            specialties = emptyList()
        ))
    }

    fun updatePersonalData(
        name: String,
        email: String,
        document: String,
        birthDate: LocalDate?,
        biography: String?,
        profilePicture: String?,
        userType: TypeUserEnum,
        gender: GenderEnum,
        specialties: List<Long>
    ) {
        val currentAddress = userInput.value?.address ?: CreateAddressInput(
            cep = "",
            street = "",
            complement = null,
            neighborhood = "",
            number = null,
            city = "",
            state = ""
        )
        userInput.value = CreateUserInput(
            name = name,
            email = email,
            document = document,
            birthDate = birthDate,
            biography = biography,
            profilePicture = profilePicture,
            userType = userType,  // Usando o TypeUserEnum
            gender = gender,      // Usando o GenderEnum
            address = currentAddress,
            specialties = specialties
        )
    }

    fun updateAddressData(
        cep: String,
        street: String,
        complement: String?,
        neighborhood: String,
        number: String?,
        city: String,
        state: String
    ) {
        val currentUser = userInput.value ?: CreateUserInput(
            name = "",
            email = "",
            document = "",
            birthDate = null,
            biography = null,
            profilePicture = null,
            userType = TypeUserEnum.CLIENT,
            gender = GenderEnum.PREFER_NOT_TO_SAY,
            address = CreateAddressInput(
                cep = cep,
                street = street,
                complement = complement,
                neighborhood = neighborhood,
                number = number,
                city = city,
                state = state
            ),
            specialties = emptyList()
        )
        userInput.value = currentUser.copy(
            address = CreateAddressInput(
                cep = cep,
                street = street,
                complement = complement,
                neighborhood = neighborhood,
                number = number,
                city = city,
                state = state
            )
        )
    }

    // Função para enviar os dados do usuário ao servidor
    fun createUser() {
        val userData = userInput.value ?: return

        viewModelScope.launch {
            try {
                val response: Response<GetUsersOutput> = userRepository.createUserClient(userData)
                if (response.isSuccessful) {
                    response.body()!!
                } else {
                    ("Erro: ${response.code()}")
                }
            } catch (e: Exception) {
                (e.message ?: "Erro desconhecido")
            }
        }
    }
}