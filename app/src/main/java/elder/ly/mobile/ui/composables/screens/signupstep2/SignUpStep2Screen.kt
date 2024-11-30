package elder.ly.mobile.ui.composables.screens.signupstep2

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import elder.ly.mobile.Search
import elder.ly.mobile.SignUpStep1
import elder.ly.mobile.domain.service.CreateAddressInput
import elder.ly.mobile.domain.service.CreateClientInput
import elder.ly.mobile.ui.composables.components.DefaultDropdownMenu
import elder.ly.mobile.ui.composables.components.DefaultTextInput
import elder.ly.mobile.ui.composables.components.NextButton
import elder.ly.mobile.ui.composables.components.TopBar
import elder.ly.mobile.ui.composables.stateholders.UserStateHolder
import elder.ly.mobile.ui.viewmodel.SignUpStepViewModel
import elder.ly.mobile.ui.viewmodel.ViaCepViewModel
import elder.ly.mobile.utils.CustomMaskTranformation
import elder.ly.mobile.utils.getBrazilStates
import elder.ly.mobile.utils.getUser
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SignUpStep2Screen(showTopBar: Boolean = true, navController: NavController) {
    val context = LocalContext.current
    val signUpStepViewModel = koinViewModel<SignUpStepViewModel>()
    val userCreationStatus by signUpStepViewModel.userCreationStatus.collectAsState()

    val viaCepViewModel = koinViewModel<ViaCepViewModel>()
    val address by viaCepViewModel.address.collectAsState()

    val gson = Gson()
    val savedStateHandle = navController.previousBackStackEntry?.savedStateHandle
    val createClientInputJson = savedStateHandle?.get<String>("createClientInputJson")
    val createClientInput = createClientInputJson?.let { gson.fromJson(it, CreateClientInput::class.java) }

    var cep by remember { mutableStateOf("") }
    var street by remember { mutableStateOf("") }
    var number by remember { mutableStateOf("") }
    var complement by remember { mutableStateOf("") }
    var state by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var district by remember { mutableStateOf("") }

    LaunchedEffect(key1 = Unit) {
        getUser(context).collect { user ->
            user.googleToken
        }
    }

    LaunchedEffect(address) {
        address?.let {
            street = it.street ?: ""
            city = it.city ?: ""
            state = it.state ?: ""
            district = it.district ?: ""
        }
    }

    Scaffold (
        topBar = {
            if (showTopBar){
                TopBar(
                    title = "Cadastro",
                    modifier = Modifier.padding(top = 44.dp, bottom = 12.dp),
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
                label = "CEP",
                placeholder = "01234-567",
                keyboardType = KeyboardType.Number,
                mask = CustomMaskTranformation(mask = "#####-###"),
                maxChar = 8,
                value = cep,
                changeValue = { newCep: String ->
                    cep = newCep

                    if (newCep.length == 8) {
                        viaCepViewModel.fetchAddress(newCep)
                    }
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

            Spacer(modifier = Modifier.weight(1f))

            NextButton(
                label = "Avançar",
                onclick = {
                    if (createClientInput != null) {
                        val createAddressInput = CreateAddressInput(
                            cep = cep,
                            logradouro = street,
                            complemento = complement,
                            bairro = district,
                            numero = number,
                            cidade = city,
                            uf = state
                        )

                        signUpStepViewModel.createUser(context, createClientInput, createAddressInput)
                    } else {
                        Toast.makeText(context, "Preencher os dados corretamente", Toast.LENGTH_LONG).show()
                        navController.navigate(SignUpStep1)
                    }
                }
            )

            LaunchedEffect(userCreationStatus) {
                when (userCreationStatus) {
                    is UserStateHolder.Loading -> {
                        // Exibir indicador de carregamento
//                        CircularProgressIndicator()
                    }

                    is UserStateHolder.Content -> {
                        // Exibir Toast e navegar apenas uma vez
                        Toast.makeText(context, "Usuário cadastrado com sucesso!", Toast.LENGTH_LONG).show()
                        navController.navigate(Search)
                    }

                    is UserStateHolder.Error -> {
                        // Exibir Toast de erro
                        Toast.makeText(context, "Erro ao cadastrar usuário.", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}

/*
@Preview(showBackground = true)
@Composable
fun SignUpStep2ScreenPreview() {
    val navController = rememberNavController()
    MobileTheme {
        SignUpStep2Screen(navController = navController)
    }
}*/
