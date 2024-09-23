package elder.ly.mobile.ui.screens.signupstep2

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import elder.ly.mobile.ui.components.BackIconButton
import elder.ly.mobile.ui.components.DefaultDropdownMenu
import elder.ly.mobile.ui.components.DefaultTextInput
import elder.ly.mobile.ui.components.NextButton
import elder.ly.mobile.utils.CustomMaskTranformation
import elder.ly.mobile.ui.theme.MobileTheme

@Composable
fun SignUpStep2Screen() {
    val brazilStates = listOf(
        "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA",
        "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN",
        "RS", "RO", "RR", "SC", "SP", "SE", "TO"
    )

    var cep by remember {
        mutableStateOf("")
    }

    var street by remember {
        mutableStateOf("")
    }

    var number by remember {
        mutableStateOf("")
    }

    var complement by remember {
        mutableStateOf("")
    }

    var state by remember {
        mutableStateOf("")
    }

    var city by remember {
        mutableStateOf("")
    }

    var district by remember {
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
                .fillMaxWidth(),
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
                        append("Cadastro")
                    }
                },
                fontSize = 36.sp
            )

            Spacer(modifier = Modifier.width(56.dp))
        }

        DefaultTextInput(
            label = "CEP",
            placeholder = "01234-567",
            keyboardType = KeyboardType.Number,
            mask = CustomMaskTranformation(mask = "#####-###"),
            maxChar = 8,
            value = cep,
            changeValue = { newCep : String ->
                cep = newCep
            }
        )

        DefaultTextInput(
            label = "Logradouro",
            placeholder = "Rua Haddock Lobo",
            value = street,
            changeValue = { newStreet : String ->
                street = newStreet
            }
        )

        DefaultTextInput(
            label = "Número",
            placeholder = "12",
            keyboardType = KeyboardType.Number,
            maxChar = 6,
            value = number,
            changeValue = { newNumber : String ->
                number = newNumber
            }
        )

        DefaultTextInput(
            label = "Complemento",
            placeholder = "Bloco A",
            value = complement,
            changeValue = { newComplememt : String ->
                complement = newComplememt
            },
        )

        DefaultDropdownMenu(
            label = "Estado",
            placeholder = "Selecione um Estado",
            options = brazilStates,
            value = state,
            changeValue = { newState : String ->
                state = newState
            }
        )

        DefaultTextInput(
            label = "Cidade",
            placeholder = "São Paulo",
            value = city,
            changeValue = { newCity : String ->
                city = newCity
            },
        )

        DefaultTextInput(
            label = "Bairro",
            placeholder = "Consolação",
            value = district,
            changeValue = { newDistrict : String ->
                district = newDistrict
            },
        )

        Spacer(modifier = Modifier.weight(1f))

        NextButton(label = "Avançar")
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpStep2ScreenPreview() {
    MobileTheme {
        SignUpStep2Screen()
    }
}