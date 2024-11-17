package elder.ly.mobile.ui.composables.screens.personalinfo

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.gson.Gson
import elder.ly.mobile.AddressInfo
import elder.ly.mobile.Profile
import elder.ly.mobile.domain.model.enums.GenderEnum
import elder.ly.mobile.domain.service.UpdateAddressInput
import elder.ly.mobile.domain.service.UpdateClientInput
import elder.ly.mobile.ui.composables.components.BottomBar
import elder.ly.mobile.ui.composables.components.TopBar
import elder.ly.mobile.ui.composables.components.DefaultDropdownMenu
import elder.ly.mobile.ui.composables.components.DefaultTextInput
import elder.ly.mobile.ui.composables.components.NextButton
import elder.ly.mobile.ui.viewmodel.PersonalInfoViewModel
import elder.ly.mobile.utils.ConvertDate
import elder.ly.mobile.utils.CustomMaskTranformation
import elder.ly.mobile.utils.getUser
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun PersonalInfoScreen(showTopBar: Boolean = true, showBottomBar: Boolean = true, navController: NavController) {
    val context = LocalContext.current
    val viewModel: PersonalInfoViewModel = koinViewModel()
    val user by viewModel.user.collectAsState()

    LaunchedEffect(key1 = Unit) {
        getUser(context).collect { userId ->
            viewModel.userId = userId.id ?: -1
            viewModel.getUser()
        }
    }

    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var document by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }

    LaunchedEffect(user) {
        user?.let {
            fullName = it.nome
            email = it.email
            document = it.documento
            birthDate = ConvertDate.formatDateToDisplay(it.dataNascimento ?: "")
            gender = when (it.genero) {
                GenderEnum.MALE.id -> "Masculino"
                GenderEnum.FEMALE.id -> "Feminino"
                else -> "Prefiro não Informar"
            }
        }
    }

    Scaffold (
        topBar = {
            if(showTopBar){
                TopBar(
                    title = "Informações\nAdicionais",
                    modifier = Modifier.padding(top = 44.dp, bottom = 16.dp),
                    navController = navController
                )
            }
        },
        bottomBar = {
            if (showBottomBar){
                BottomBar(
                    navController = navController,
                    colorBlueProfile = true
                )
            }
        }
    ){ paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DefaultTextInput(
                label = "Nome Completo",
                value = fullName,
                changeValue = { newFullName : String ->
                    fullName = newFullName
                }
            )

            DefaultTextInput(
                label = "Email",
                placeholder = "seuemail@gmail.com",
                keyboardType = KeyboardType.Email,
                value = email,
                changeValue = { newEmail : String ->
                    email = newEmail
                }
            )

            DefaultTextInput(
                label = "Documento (CPF/CNPJ)",
                placeholder = "12345678910",
                keyboardType = KeyboardType.Number,
                maxChar = 11,
                value = document,
                changeValue = { newDocument : String ->
                    document = newDocument
                }
            )

            DefaultTextInput(
                label = "Data de Nascimento",
                placeholder = "23/06/1991",
                keyboardType = KeyboardType.Number,
                mask = CustomMaskTranformation(mask = "##/##/####"),
                maxChar = 8,
                value = birthDate,
                changeValue = { newBirthDate : String ->
                    birthDate = newBirthDate
                },
            )

            DefaultDropdownMenu(
                label = "Gênero",
                placeholder = "Selecione um Gênero",
                value = gender,
                options = listOf("Masculino", "Feminino", "Prefiro não Informar"),
                changeValue = { newGender: String ->
                    gender = newGender
                }
            )

            Spacer(modifier = Modifier.weight(1f))

            NextButton(
                label = "Salvar",
                onclick = {
                    // Conversão da data de nascimento
                    val formattedDate = ConvertDate.convertDate(birthDate)
                    if (formattedDate.isBlank()) {
                        Toast.makeText(context, "Data inválida: $birthDate.", Toast.LENGTH_LONG).show()
                    }

                    val updateClientInput = UpdateClientInput(
                        nome = fullName,
                        email = email,
                        documento = document,
                        dataNascimento = formattedDate,
                        biografia = null,
                        genero = when (gender) {
                            "Masculino" -> GenderEnum.MALE.id
                            "Feminino" -> GenderEnum.FEMALE.id
                            else -> GenderEnum.PREFER_NOT_TO_SAY.id
                        },
                        especialidades = listOf()
                    )

                    viewModel.updateUser(viewModel.userId, updateClientInput)
                }
            )
        }
    }
}

/*@Preview(showBackground = true)
@Composable
private fun PersonalInfoScreenPreview() {
    MobileTheme {
        val navController = rememberNavController()
        PersonalInfoScreen(navController = navController)
    }
}*/
