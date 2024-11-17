package elder.ly.mobile.ui.composables.screens.addressinfo

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import elder.ly.mobile.Profile
import elder.ly.mobile.domain.model.enums.GenderEnum
import elder.ly.mobile.domain.service.UpdateAddressInput
import elder.ly.mobile.ui.composables.components.BottomBar
import elder.ly.mobile.ui.composables.components.TopBar
import elder.ly.mobile.ui.composables.components.DefaultDropdownMenu
import elder.ly.mobile.ui.composables.components.DefaultTextInput
import elder.ly.mobile.ui.composables.components.NextButton
import elder.ly.mobile.ui.theme.MobileTheme
import elder.ly.mobile.ui.viewmodel.AddressInfoViewModel
import elder.ly.mobile.utils.ConvertDate
import elder.ly.mobile.utils.CustomMaskTranformation
import elder.ly.mobile.utils.getBrazilStates
import elder.ly.mobile.utils.getUser
import org.koin.compose.viewmodel.koinViewModel

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
                BottomBar(navController = navController, colorBlueProfile = true)
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
    val context = LocalContext.current
    val viewModel: AddressInfoViewModel = koinViewModel()
    val address by viewModel.address.collectAsState()

    LaunchedEffect(key1 = Unit) {
        getUser(context).collect { userId ->
            viewModel.userId = userId.id ?: -1
            viewModel.getAddress()
        }
    }

    var cep by remember { mutableStateOf("") }
    var street by remember { mutableStateOf("") }
    var number by remember { mutableStateOf("") }
    var complement by remember { mutableStateOf("") }
    var state by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var district by remember { mutableStateOf("") }

    LaunchedEffect(address) {
        address?.let {
            cep = it.cep
            street = it.logradouro
            number = it.numero ?: ""
            complement = it.complemento ?: ""
            state = it.uf
            city = it.cidade
            district = it.bairro ?: ""
        }
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
        options = getBrazilStates(),
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
            val updateAddressInput = UpdateAddressInput(
                cep = cep,
                logradouro = street,
                complemento = complement,
                bairro = district,
                numero = number,
                cidade = city,
                uf = state
            )

            viewModel.updateAddress(viewModel.userId, updateAddressInput)
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