package elder.ly.mobile.ui.composables.screens.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
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
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.gson.Gson
import elder.ly.mobile.SearchResult
import elder.ly.mobile.domain.service.GetDataSearchScreen
import elder.ly.mobile.ui.composables.components.BottomBar
import elder.ly.mobile.ui.composables.components.DataTextButton
import elder.ly.mobile.ui.composables.components.DefaultDropdownMenu
import elder.ly.mobile.ui.composables.components.HourTextButton
import elder.ly.mobile.ui.composables.components.NextButton
import elder.ly.mobile.ui.composables.components.SpecialtyList
import elder.ly.mobile.ui.composables.components.TopBar
import elder.ly.mobile.ui.theme.MobileTheme
import elder.ly.mobile.ui.viewmodel.SearchViewModel
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun SearchScreen(
    showTopBar: Boolean = true,
    showBottomBar: Boolean = true,
    navController: NavController
) {
    var startDate by remember { mutableStateOf("") }
    var endDate by remember { mutableStateOf("") }
    var startTime by remember { mutableStateOf("") }
    var endTime by remember { mutableStateOf("") }

    var especialidades by remember { mutableStateOf("") }
    var selectedSpecialties by remember { mutableStateOf<List<String>>(emptyList()) }

    val viewModel = koinViewModel<SearchViewModel>()

    Scaffold(
        topBar = {
            if (showTopBar) {
                TopBar(
                    title = "O Que Precisa?",
                    showBackButton = false,
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
                        append("Pesquise o cuidador ideal para você")
                    },
                    fontSize = 18.sp,
                    color = Color.Gray
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

                DefaultDropdownMenu(
                    label = "Especialidades",
                    placeholder = "Selecione Especialidade(s)",
                    options = viewModel.specialties.value ?: emptyList(),
                    value = especialidades,
                    changeValue = { newEspecialidades ->
                        especialidades = newEspecialidades
                        if (newEspecialidades.isNotEmpty() && !selectedSpecialties.contains(
                                newEspecialidades
                            )
                        ) {
                            selectedSpecialties = selectedSpecialties + newEspecialidades
                        }
                    }
                )

                SpecialtyList(specialties = selectedSpecialties, onRemove = { specialty ->
                    selectedSpecialties = selectedSpecialties - specialty
                })

                Spacer(modifier = Modifier.weight(1f))

                val sampleSearchResult = GetDataSearchScreen(
                    startDate = startDate,
                    startTime = startTime,
                    endDate = endDate,
                    endTime = endTime,
                    specialties = selectedSpecialties
                )

                val gson = Gson()
                val sampleSearchResultInputJson = gson.toJson(sampleSearchResult)


                NextButton(
                    label = "Pesquisar",
                    icon = Icons.Filled.Search,
                    onclick = {
                        navController.currentBackStackEntry?.savedStateHandle?.set("sampleSearchResultInputJson", sampleSearchResultInputJson)
                        navController.navigate(SearchResult)
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
    MobileTheme {
        val navController = rememberNavController()
        SearchScreen(navController = navController)
    }
}