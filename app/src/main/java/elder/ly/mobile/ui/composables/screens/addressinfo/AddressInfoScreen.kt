package elder.ly.mobile.ui.composables.screens.addressinfo

import android.widget.Toast
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
import com.google.gson.Gson
import elder.ly.mobile.Profile
import elder.ly.mobile.domain.service.AddressOutput
import elder.ly.mobile.domain.service.CreateClientInput
import elder.ly.mobile.domain.service.GetUsersOutput
import elder.ly.mobile.domain.service.UpdateAddressInput
import elder.ly.mobile.domain.service.UpdateClientInput
import elder.ly.mobile.ui.composables.components.BottomBar
import elder.ly.mobile.ui.composables.components.TopBar
import elder.ly.mobile.ui.composables.components.DefaultDropdownMenu
import elder.ly.mobile.ui.composables.components.DefaultTextInput
import elder.ly.mobile.ui.composables.components.NextButton
import elder.ly.mobile.ui.composables.stateholders.CreateStateHolder
import elder.ly.mobile.ui.theme.MobileTheme
import elder.ly.mobile.ui.viewmodel.PersonalInfoViewModel
import elder.ly.mobile.utils.CustomMaskTranformation
import elder.ly.mobile.utils.getBrazilStates
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AddressInfoScreen(
    showTopBar: Boolean = true,
    showBottomBar: Boolean = true,
    navController: NavController
) {
    val context = LocalContext.current
    val personalInfoViewModel: PersonalInfoViewModel = koinViewModel()

    // Observa o estado do usuário e do status de criação
    val user by personalInfoViewModel.user.collectAsState()
    val userCreationStatus by personalInfoViewModel.userCreationStatus.collectAsState()

    val gson = Gson()
    val savedStateHandle = navController.previousBackStackEntry?.savedStateHandle
    val updateClientInputJson = savedStateHandle?.get<String>("updateClientInputJson")

    // Converte de JSON para `CreateClientInput`
    var updateClientInput = updateClientInputJson?.let { gson.fromJson(it, UpdateClientInput::class.java) }

    // Monitora o estado de criação/atualização do usuário
    LaunchedEffect(userCreationStatus) {
        when (userCreationStatus) {
            is CreateStateHolder.Content -> {
                Toast.makeText(context, "Dados atualizados com sucesso!", Toast.LENGTH_SHORT).show()
                navController.popBackStack()
            }
            is CreateStateHolder.Error -> {
                val error = (userCreationStatus as CreateStateHolder.Error).message
                Toast.makeText(context, error, Toast.LENGTH_LONG).show()
            }
            CreateStateHolder.Loading -> {
                // Mostrar um indicador de carregamento, se necessário
            }
        }
    }

    Scaffold(
        topBar = {
            if (showTopBar) {
                TopBar(
                    title = "Endereço",
                    modifier = Modifier.padding(top = 44.dp),
                    navController = navController
                )
            }
        },
        bottomBar = {
            if (showBottomBar) {
                BottomBar(navController = navController, colorBlueProfile = true)
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(1) {
                    user?.endereco?.let { address ->
                        AddressForm(
                            navController = navController,
                            address = address,
                            onSaveClick = { updatedAddress ->
                                updateClientInput = user?.toUpdateClientInput(updatedAddress)
                                updateClientInput?.let {
                                    personalInfoViewModel.updateUser(
                                        id = user!!.id,
                                        updateClientInput = it,
                                        updateAddressInput = updatedAddress
                                    )
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

// Função de extensão para criar `UpdateClientInput` usando os dados de `user` e o endereço atualizado
fun GetUsersOutput.toUpdateClientInput(updatedAddress: UpdateAddressInput): UpdateClientInput {
    return UpdateClientInput(
        nome = this.nome,
        email = this.email,
        documento = this.documento,
        dataNascimento = this.dataNascimento,
        biografia = this.biografia,
        genero = this.genero,
        endereco = updatedAddress, // Passa o endereço atualizado
        especialidades = emptyList()
    )
}

@Composable
private fun AddressForm(
    navController: NavController,
    address: AddressOutput,
    onSaveClick: (UpdateAddressInput) -> Unit
) {
    var cep by remember { mutableStateOf(address.cep ?: "") }
    var street by remember { mutableStateOf(address.logradouro ?: "") }
    var number by remember { mutableStateOf(address.numero ?: "") }
    var complement by remember { mutableStateOf(address.complemento ?: "") }
    var state by remember { mutableStateOf(address.uf ?: "") }
    var city by remember { mutableStateOf(address.cidade ?: "") }
    var district by remember { mutableStateOf(address.bairro ?: "") }

    DefaultTextInput(
        label = "CEP",
        placeholder = "01234-567",
        keyboardType = KeyboardType.Number,
        mask = CustomMaskTranformation(mask = "#####-###"),
        maxChar = 8,
        value = cep,
        changeValue = { cep = it }
    )

    DefaultTextInput(
        label = "Logradouro",
        placeholder = "Rua Haddock Lobo",
        value = street,
        changeValue = { street = it }
    )

    DefaultTextInput(
        label = "Número",
        placeholder = "12",
        keyboardType = KeyboardType.Number,
        maxChar = 6,
        value = number,
        changeValue = { number = it }
    )

    DefaultTextInput(
        label = "Complemento",
        placeholder = "Bloco A",
        value = complement,
        changeValue = { complement = it }
    )

    DefaultDropdownMenu(
        label = "Estado",
        placeholder = "Selecione um Estado",
        options = getBrazilStates(),
        value = state,
        changeValue = { state = it }
    )

    DefaultTextInput(
        label = "Cidade",
        placeholder = "São Paulo",
        value = city,
        changeValue = { city = it }
    )

    DefaultTextInput(
        label = "Bairro",
        placeholder = "Consolação",
        value = district,
        changeValue = { district = it }
    )

    NextButton(
        label = "Salvar",
        modifier = Modifier.padding(top = 12.dp),
        onclick = {
            // Cria o objeto `UpdateAddressInput` usando os valores atuais
            val updatedAddress = UpdateAddressInput(
                cep = cep,
                logradouro = street,
                complemento = complement,
                bairro = district,
                numero = number,
                cidade = city,
                uf = state
            )
            onSaveClick(updatedAddress)
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