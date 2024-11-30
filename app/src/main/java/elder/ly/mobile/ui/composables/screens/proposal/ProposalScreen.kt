package elder.ly.mobile.ui.composables.screens.proposal

import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.gson.Gson
import elder.ly.mobile.Chat
import elder.ly.mobile.domain.service.GetDataProposalScreen
import elder.ly.mobile.domain.service.ProposalInput
import elder.ly.mobile.ui.composables.components.BottomBar
import elder.ly.mobile.ui.composables.components.DataTextButton
import elder.ly.mobile.ui.composables.components.DefaultTextInput
import elder.ly.mobile.ui.composables.components.HourTextButton
import elder.ly.mobile.ui.composables.components.NextButton
import elder.ly.mobile.ui.composables.components.TopBar
import elder.ly.mobile.ui.composables.screens.chat.ProposalDataJson
import elder.ly.mobile.ui.theme.MobileTheme
import elder.ly.mobile.ui.theme.customBlueColor
import elder.ly.mobile.ui.theme.outlineLight
import elder.ly.mobile.ui.theme.primaryContainerLight
import elder.ly.mobile.ui.theme.tertiaryContainerLight
import elder.ly.mobile.ui.viewmodel.ProposalViewModel
import org.koin.compose.viewmodel.koinViewModel
import java.math.BigDecimal

@Composable
fun ProposalScreen(
    showTopBar: Boolean = true,
    showBottomBar: Boolean = true,
    navController: NavController
) {
    println("ok 2")

    var startDate by remember { mutableStateOf("") }
    var endDate by remember { mutableStateOf("") }
    var startTime by remember { mutableStateOf("") }
    var endTime by remember { mutableStateOf("") }

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var price by remember { mutableStateOf(BigDecimal.ZERO) }

    val gson = Gson()
    val savedStateHandle = navController.previousBackStackEntry?.savedStateHandle
    val proposalJson = savedStateHandle?.get<String>("ProposalDataJson")

    val proposalData = proposalJson?.let { gson.fromJson(it, ProposalDataJson::class.java) }
    val viewModel = koinViewModel<ProposalViewModel>()
    viewModel.senderId = proposalData?.senderId ?: -1
    viewModel.recipientId = proposalData?.recipientId ?: -1

    val scrollState = rememberScrollState()

    val isFormComplete = title.isNotBlank() &&
            description.isNotBlank() &&
            price > BigDecimal.ZERO &&
            startDate.isNotBlank() &&
            startTime.isNotBlank() &&
            endDate.isNotBlank() &&
            endTime.isNotBlank()

    Scaffold(
        topBar = {
            if (showTopBar) {
                TopBar(
                    title = "Proposta",
                    showBackButton = true,
                    modifier = Modifier.padding(top = 44.dp),
                    navController = navController
                )
            }
        },
        bottomBar = {
            if (showBottomBar) {
                BottomBar(
                    navController = navController,
                    colorBlueSearch = true
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = buildAnnotatedString {
                        append("Construa sua proposta")
                    },
                    fontSize = 18.sp,
                    color = Color.Gray
                )

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(scrollState)
                ) {
                    DefaultTextInput(
                        label = "Título",
                        value = title,
                        changeValue = { newTitle: String ->
                            title = newTitle
                        }
                    )

                    Description(valorCampo = description) { newDescription ->
                        description = newDescription
                    }

                    DefaultTextInput(
                        label = "Preço",
                        value = price.toPlainString(),
                        changeValue = { newPrice: String ->
                            price = newPrice.toBigDecimalOrNull() ?: BigDecimal.ZERO
                        }
                    )

                    DataTextButton(
                        modifier = Modifier.padding(top = 16.dp),
                        labelData = "Data Início",
                        onDateSelected = { date -> startDate = date } // Atualiza a data de início
                    )
                    HourTextButton(
                        modifier = Modifier.padding(top = 8.dp),
                        labelHora = "Hora Início",
                        onHourSelected = { time -> startTime = time } // Atualiza a hora de início
                    )
                    DataTextButton(
                        modifier = Modifier.padding(top = 8.dp),
                        labelData = "Data Fim",
                        onDateSelected = { date -> endDate = date } // Atualiza a data de fim
                    )
                    HourTextButton(
                        modifier = Modifier.padding(top = 8.dp),
                        labelHora = "Hora Fim",
                        onHourSelected = { time -> endTime = time } // Atualiza a hora de fim
                    )
                }

                Spacer(modifier = Modifier.size(16.dp))


                NextButton(
                    label = "Criar Proposta",
                    icon = Icons.AutoMirrored.Filled.Send,
                    enabled = isFormComplete,
                    onclick = {
                        viewModel.sendProposal(
                            GetDataProposalScreen(
                                titulo = title,
                                descricao = description,
                                startDate = startDate,
                                endDate = endDate,
                                startTime = startTime,
                                endTime = endTime,
                                preco = price
                            )
                        )
                        navController.popBackStack()
                    },
                )
            }
        }
    }
}

@Composable
fun Description(
    valorCampo: String,
    mudaValorCampo: (String) -> Unit
) {
    val unfocusedBorderColor = outlineLight
    val focusedBorderColor = primaryContainerLight
    Column(
        modifier = Modifier
            .width(320.dp)
            .padding(top = 8.dp)
    ) {
        Text(text = "Descrição")
        OutlinedTextField(
            placeholder = {
                Text(
                    text = "Descreva sua proposta...",
                    color = tertiaryContainerLight
                )
            },
            value = valorCampo,
            onValueChange = mudaValorCampo,
            modifier = Modifier
                .fillMaxWidth()
                .height(132.dp)
                .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(12.dp)),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = unfocusedBorderColor, // Usando a cor animada
                focusedBorderColor = focusedBorderColor,   // Usando a cor animada
                cursorColor = focusedBorderColor
            ),
            shape = RoundedCornerShape(12.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProposalScreenPreview() {
    MobileTheme {
        val navController = rememberNavController()
        ProposalScreen(navController = navController)
    }
}