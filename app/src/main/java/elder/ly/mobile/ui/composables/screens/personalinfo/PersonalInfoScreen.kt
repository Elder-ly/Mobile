package elder.ly.mobile.ui.composables.screens.personalinfo

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import elder.ly.mobile.AddressInfo
import elder.ly.mobile.domain.model.enums.GenderEnum
import elder.ly.mobile.domain.service.UpdateAddressInput
import elder.ly.mobile.domain.service.UpdateClientInput
import elder.ly.mobile.ui.composables.components.BottomBar
import androidx.navigation.compose.rememberNavController
import elder.ly.mobile.Profile
import elder.ly.mobile.ui.composables.components.BottomBar
import elder.ly.mobile.ui.composables.components.TopBar
import elder.ly.mobile.ui.composables.components.DefaultDropdownMenu
import elder.ly.mobile.ui.composables.components.DefaultTextInput
import elder.ly.mobile.ui.composables.components.NextButton
import elder.ly.mobile.ui.composables.components.TopBar
import elder.ly.mobile.ui.viewmodel.PersonalInfoViewModel
import elder.ly.mobile.utils.ConvertDate
import elder.ly.mobile.utils.CustomMaskTranformation
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun PersonalInfoScreen(
    showTopBar: Boolean = true,
    showBottomBar: Boolean = true,
    navController: NavController
) {
    val context = LocalContext.current
    val personalInfoViewModel: PersonalInfoViewModel = koinViewModel()

    val user by personalInfoViewModel.user.collectAsState()
    val isLoading by personalInfoViewModel.isLoading.collectAsState()
    val error by personalInfoViewModel.error.collectAsState()

    var hasFetchedUser by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        if (!hasFetchedUser) {
            personalInfoViewModel.getUser()
            hasFetchedUser = true
        }
    }

    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var document by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }

    var cep by remember { mutableStateOf("") }
    var street by remember { mutableStateOf("") }
    var number by remember { mutableStateOf("") }
    var complement by remember { mutableStateOf("") }
    var state by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var district by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            if (showTopBar) {
                TopBar(
                    title = "Informações Adicionais",
                    modifier = Modifier.padding(top = 44.dp, bottom = 16.dp),
                    navController = navController
                )
            }
        },
        bottomBar = {
            if (showBottomBar) {
                BottomBar(
                    navController = navController,
                    colorBlueProfile = true
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween // Garante que o botão ficará no fim da tela
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                when {
                    isLoading -> {
                        // Exibir um indicador de carregamento, se necessário
                    }

                    error != null -> {
                        Log.e("UserInfoScreen", "Erro ao carregar dados: $error")
                        Toast.makeText(context, "Erro ao carregar dados: $error", Toast.LENGTH_LONG)
                            .show()
                    }

                    user != null -> {
                        LaunchedEffect(user) {
                            user?.let {
                                fullName = it.nome ?: ""
                                email = it.email ?: ""
                                document = it.documento ?: ""
                                birthDate = ConvertDate.formatDateToDisplay(it.dataNascimento ?: "")
                                gender = when (it.genero) {
                                    GenderEnum.MALE.id -> GenderEnum.MALE.description
                                    GenderEnum.FEMALE.id -> GenderEnum.FEMALE.description
                                    else -> GenderEnum.PREFER_NOT_TO_SAY.description
                                }

                                // Verifica se o endereço não é nulo antes de acessar os campos
                                it.endereco.let { address ->
                                    cep = address.cep ?: ""
                                    street = address.logradouro ?: ""
                                    number = address.numero ?: ""
                                    complement = address.complemento ?: ""
                                    state = address.uf ?: ""
                                    city = address.cidade ?: ""
                                    district = address.bairro ?: ""
                                }
                            }
                        }
                    }
                }
            }

            DefaultTextInput(
                label = "Nome Completo",
                value = fullName,
                changeValue = { newFullName: String -> fullName = newFullName }
            )

            DefaultTextInput(
                label = "Email",
                placeholder = "seuemail@gmail.com",
                keyboardType = KeyboardType.Email,
                value = email,
                changeValue = { newEmail: String -> email = newEmail }
            )

            DefaultTextInput(
                label = "Documento (CPF/CNPJ)",
                placeholder = "12345678910",
                keyboardType = KeyboardType.Number,
                maxChar = 11,
                value = document,
                changeValue = { newDocument: String -> document = newDocument }
            )

            DefaultTextInput(
                label = "Data de Nascimento",
                placeholder = "23/06/1991",
                keyboardType = KeyboardType.Number,
                mask = CustomMaskTranformation(mask = "##/##/####"),
                maxChar = 8,
                value = birthDate,
                changeValue = { newBirthDate: String -> birthDate = newBirthDate }
            )

            DefaultDropdownMenu(
                label = "Gênero",
                placeholder = "Selecione um Gênero",
                options = listOf("Masculino", "Feminino", "Prefiro não Informar"),
                value = gender,
                changeValue = { newGender: String -> gender = newGender }
            )

            // Botão de Avançar posicionado no final da coluna
            NextButton(
                label = "Avançar",
                onclick = {
                    if (fullName.isBlank() || email.isBlank() || document.isBlank() || gender.isBlank()) {
                        Toast.makeText(
                            context,
                            "Por favor, preencha todos os campos obrigatórios.",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                    val formattedDate = ConvertDate.convertDate(birthDate)
                    if (formattedDate.isBlank()) {
                        Toast.makeText(context, "Data está como: $birthDate.", Toast.LENGTH_LONG).show()
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
                        endereco = UpdateAddressInput(
                            cep = cep,
                            logradouro = street,
                            complemento = complement,
                            bairro = district,
                            numero = number,
                            cidade = city,
                            uf = state
                        ),
                        especialidades = listOf()
                    )

                    val gson = Gson()
                    val updateClientInputJson = gson.toJson(updateClientInput)

                    // Navegar para AddressInfoScreen e passar o JSON com os dados
                    navController.currentBackStackEntry?.savedStateHandle?.set("updateClientInputJson", updateClientInputJson)
                    navController.navigate(AddressInfo)
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
