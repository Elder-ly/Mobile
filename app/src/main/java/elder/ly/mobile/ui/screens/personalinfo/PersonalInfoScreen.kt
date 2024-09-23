package elder.ly.mobile.ui.screens.personalinfo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import elder.ly.mobile.ui.components.BackIconButton
import elder.ly.mobile.ui.components.DefaultDropdownMenu
import elder.ly.mobile.ui.components.DefaultTextInput
import elder.ly.mobile.ui.components.NextButton
import elder.ly.mobile.ui.theme.MobileTheme
import elder.ly.mobile.utils.CustomMaskTranformation

@Composable
fun PersonalInfoScreen() {
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 44.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            BackIconButton()

            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append("Informações\nAdicionais")
                    }
                },
                fontSize = 36.sp,
                textAlign = TextAlign.Center,
                lineHeight = 40.sp
            )

            Spacer(modifier = Modifier.width(56.dp))
        }

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

        NextButton(label = "Avançar")
    }
}

@Preview(showBackground = true)
@Composable
private fun PersonalInfoScreenPreview() {
    MobileTheme {
        PersonalInfoScreen()
    }
}
