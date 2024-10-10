package elder.ly.mobile.ui.screens.search

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
import elder.ly.mobile.SearchResult
import elder.ly.mobile.SignUpStep2
import elder.ly.mobile.ui.components.BottomBar
import elder.ly.mobile.ui.components.DataTextButton
import elder.ly.mobile.ui.components.DefaultDropdownMenu
import elder.ly.mobile.ui.components.HourTextButton
import elder.ly.mobile.ui.components.NextButton
import elder.ly.mobile.ui.components.SpecialtyList
import elder.ly.mobile.ui.components.TopBar
import elder.ly.mobile.ui.theme.MobileTheme


@Composable
fun SearchScreen(showTopBar: Boolean = true, showBottomBar: Boolean = true, navController: NavController) {
    var especialidades by remember { mutableStateOf("") }
    var selectedSpecialties by remember { mutableStateOf<List<String>>(emptyList()) }

    Scaffold(
        topBar = {
            if(showTopBar){
                TopBar(
                    title = "O Que Precisa?",
                    showBackButton = false,
                    modifier = Modifier.padding(top = 44.dp),
                    navController = navController
                )
            }
        },
        bottomBar = {
            if (showBottomBar){
                BottomBar(
                    //modifier = Modifier.padding(bottom = 44.dp)
                    navController = navController
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

                DataTextButton(modifier = Modifier.padding(top = 16.dp), labelData = "Data Início")
                HourTextButton(modifier = Modifier.padding(top = 8.dp), labelHora = "Hora Início")
                DataTextButton(modifier = Modifier.padding(top = 8.dp), labelData = "Data Fim")
                HourTextButton(modifier = Modifier.padding(top = 8.dp), labelHora = "Hora Fim")


                DefaultDropdownMenu(
                    label = "Especialidades",
                    placeholder = "Selecione Especialidade(s)",
                    options = listOf("Fraldas", "Bingo", "Medicação"),
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

                NextButton(
                    label = "Pesquisar",
                    icon = Icons.Filled.Search,
                    onclick = {
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