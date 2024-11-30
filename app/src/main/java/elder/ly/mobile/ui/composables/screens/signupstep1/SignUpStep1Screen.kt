package elder.ly.mobile.ui.composables.screens.signupstep1

import android.content.Context
import android.util.Log
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.gson.Gson
import elder.ly.mobile.SignUpStep2
import elder.ly.mobile.domain.model.enums.GenderEnum
import elder.ly.mobile.domain.model.enums.TypeUserEnum
import elder.ly.mobile.domain.service.CreateClientInput
import elder.ly.mobile.ui.composables.components.DefaultDropdownMenu
import elder.ly.mobile.ui.composables.components.DefaultTextInput
import elder.ly.mobile.ui.composables.components.NextButton
import elder.ly.mobile.ui.composables.components.TopBar
import elder.ly.mobile.utils.ConvertDate
import elder.ly.mobile.utils.CustomMaskTranformation
import elder.ly.mobile.utils.getUser


@Composable
fun SignUpStep1Screen(showTopBar: Boolean = true, navController: NavController) {
    val context = LocalContext.current

    var profilePicture by remember { mutableStateOf<String?>(null) }
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var document by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var googleToken by remember { mutableStateOf("") }


    LaunchedEffect(key1 = Unit) {
        getUser(context).collect { user ->
            email = user.email ?: ""
            profilePicture = user.pictureURL
            googleToken = user.googleToken ?: ""

            Log.d("SignUpStep1Screen", "GoogleToke: $googleToken")
        }
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

            // Uso no botão
            NextButton(
                label = "Avançar",
                onclick = {
                    val createClientInput = validateAndPrepareClientInput(
                        fullName = fullName,
                        email = email,
                        document = document,
                        birthDate = birthDate,
                        gender = gender,
                        profilePicture = profilePicture,
                        context = context
                    )

                    // Se os dados forem válidos, navegue para a próxima tela
                    val gson = Gson()
                    val createClientInputJson = gson.toJson(createClientInput)

                    // Navega para a segunda tela passando os dados
                    navController.currentBackStackEntry?.savedStateHandle?.set("createClientInputJson", createClientInputJson)
                    navController.navigate(SignUpStep2)
                }
            )
        }
    }
}

private fun validateAndPrepareClientInput(
    fullName: String,
    email: String,
    document: String,
    birthDate: String,
    gender: String,
    profilePicture: String?,
    context: Context
): CreateClientInput? {
    // Verificação dos campos obrigatórios
    if (fullName.isBlank() || email.isBlank() || document.isBlank() || gender.isBlank()) {
        Toast.makeText(
            context,
            "Por favor, preencha todos os campos obrigatórios.",
            Toast.LENGTH_LONG
        ).show()
        return null
    }

    // Conversão da data de nascimento
    val formattedDate = ConvertDate.convertDate(birthDate)
    if (formattedDate.isBlank()) {
        Toast.makeText(context, "Data inválida: $birthDate.", Toast.LENGTH_LONG).show()
        return null
    }

    // Retorna um objeto CreateClientInput se todos os dados forem válidos
    return CreateClientInput(
        nome = fullName,
        email = email,
        documento = document,
        dataNascimento = formattedDate,
        biografia = null,
        fotoPerfil = profilePicture,
        tipoUsuario = TypeUserEnum.CLIENT.id,
        genero = when (gender) {
            "Masculino" -> GenderEnum.MALE.id
            "Feminino" -> GenderEnum.FEMALE.id
            else -> GenderEnum.PREFER_NOT_TO_SAY.id
        },
        especialidades = listOf()
    )
}

/*@Preview(showBackground = true)
@Composable
private fun SignUpStep1ScreenPreview() {
    MobileTheme {
        val navController = rememberNavController()
        SignUpStep1Screen(navController = navController, )
    }
}*/
