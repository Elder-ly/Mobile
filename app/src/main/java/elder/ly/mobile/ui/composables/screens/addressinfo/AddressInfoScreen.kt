package elder.ly.mobile.ui.composables.screens.addressinfo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import elder.ly.mobile.Profile
import elder.ly.mobile.ui.components.BottomBar
import elder.ly.mobile.ui.components.DefaultDropdownMenu
import elder.ly.mobile.ui.components.DefaultTextInput
import elder.ly.mobile.ui.components.NextButton
import elder.ly.mobile.ui.components.TopBar
import elder.ly.mobile.ui.theme.MobileTheme
import elder.ly.mobile.utils.CustomMaskTranformation

@Composable
fun AddressInfoScreen(showTopBar: Boolean = true, showBottomBar: Boolean = true, navController: NavController) {
    Scaffold (
        topBar = {
            if(showTopBar){
                TopBar(
                    title = "Endereço",
                    modifier = Modifier.padding(top = 44.dp),
                    navController = navController
                )
            }
        },
        bottomBar = {
            if(showBottomBar){
                BottomBar(navController = navController)
            }
        }
    ){ paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn (
                modifier = Modifier.weight(1f)
            ){
                items(1){
                    InputsButton(navController = navController)
                }
            }
        }
    }
}

@Composable
fun InputsButton(navController: NavController) {
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
    NextButton(
        label = "Salvar",
        modifier = Modifier.padding(top = 12.dp),
        onclick = {
            navController.navigate(Profile)
        }
    )
}

@Preview(showBackground = true)
@Composable
fun AddressInfoScreenPreview() {
    MobileTheme {
        val navController = rememberNavController()
        AddressInfoScreen(navController = navController)
    }
}