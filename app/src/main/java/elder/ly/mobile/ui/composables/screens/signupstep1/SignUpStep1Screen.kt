package elder.ly.mobile.ui.composables.screens.signupstep1

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import elder.ly.mobile.SignUpStep2
import elder.ly.mobile.domain.model.enums.GenderEnum
import elder.ly.mobile.domain.model.enums.TypeUserEnum
import elder.ly.mobile.ui.composables.components.DefaultDropdownMenu
import elder.ly.mobile.ui.composables.components.DefaultTextInput
import elder.ly.mobile.ui.composables.components.NextButton
import elder.ly.mobile.ui.composables.components.TopBar
import elder.ly.mobile.ui.viewmodel.SignUpStepViewModel
import elder.ly.mobile.utils.ConvertStringToLocalDate
import elder.ly.mobile.utils.CustomMaskTranformation
import elder.ly.mobile.utils.UserNotFoundException
import elder.ly.mobile.utils.getUser
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SignUpStep1Screen(showTopBar: Boolean = true, navController: NavController) {

    val context = LocalContext.current
    val signUpStepViewModel = koinViewModel<SignUpStepViewModel>()
    val coroutineScope = rememberCoroutineScope()

    var profilePicture by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        coroutineScope.launch {
                getUser(context)
                .catch { e ->
                    if (e is UserNotFoundException) {
                        Toast.makeText(context, "Usuário não encontrado!", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(context, "Erro ao carregar usuário.", Toast.LENGTH_LONG).show()
                    }
                }
                .collect { user ->
                    profilePicture = user.pictureURL // Atualiza a URL do profile
                }
        }
    }

    var fullName by remember {
        mutableStateOf("")
    }

    var email by remember {
        mutableStateOf("")
    }

    var document by remember {
        mutableStateOf("")
    }

    var birthDate by remember {
        mutableStateOf("")
    }

    var gender by remember {
        mutableStateOf("")
    }

    Scaffold (
        topBar = {
            if (showTopBar){
                TopBar(
                    title = "Cadastro",
                    modifier = Modifier.padding(top = 44.dp, bottom = 16.dp),
                    showBackButton = false,
                    navController = navController
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
                options = listOf("Masculino", "Feminino", "Prefiro não Informar"),
                value = gender,
                changeValue = { newGender : String ->
                    gender = newGender
                }
            )

            Spacer(modifier = Modifier.weight(1f))

            NextButton(
                label = "Avançar",
                onclick = {
                    val birthDateLocalDate = ConvertStringToLocalDate.convert(birthDate);

                    signUpStepViewModel.updatePersonalData(
                        name = fullName,
                        email = email,
                        document = document,
                        birthDate = birthDateLocalDate,
                        biography = null,
                        profilePicture = profilePicture, // Passando a URL do profile picture
                        userType = TypeUserEnum.CLIENT,
                        gender = when (gender) {
                            "Masculino" -> GenderEnum.MALE
                            "Feminino" -> GenderEnum.FEMALE
                            else -> GenderEnum.PREFER_NOT_TO_SAY
                        },
                        specialties = listOf()
                    )

                    navController.navigate(SignUpStep2)
                }
            )
        }
    }
}

/*@Preview(showBackground = true)
@Composable
private fun SignUpStep1ScreenPreview() {
    MobileTheme {
        val navController = rememberNavController()
        SignUpStep1Screen(navController = navController, )
    }
}*/
