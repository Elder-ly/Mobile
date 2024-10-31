package elder.ly.mobile.ui.composables.screens.signupstep1

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import elder.ly.mobile.SignUpStep2
import elder.ly.mobile.ui.composables.components.TopBar
import elder.ly.mobile.ui.composables.components.DefaultDropdownMenu
import elder.ly.mobile.ui.composables.components.DefaultTextInput
import elder.ly.mobile.ui.composables.components.NextButton
import elder.ly.mobile.ui.theme.MobileTheme
import elder.ly.mobile.utils.CustomMaskTranformation

@Composable
fun SignUpStep1Screen(showTopBar: Boolean = true, navController: NavController) {
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
                    navController.navigate(SignUpStep2)
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpStep1ScreenPreview() {
    MobileTheme {
        val navController = rememberNavController()
        SignUpStep1Screen(navController = navController)
    }
}
