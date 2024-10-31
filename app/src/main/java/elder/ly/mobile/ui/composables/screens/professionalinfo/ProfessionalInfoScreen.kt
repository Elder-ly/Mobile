package elder.ly.mobile.ui.composables.screens.professionalinfo

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import elder.ly.mobile.ui.theme.MobileTheme
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import elder.ly.mobile.Profile
import elder.ly.mobile.ui.composables.components.BottomBar
import elder.ly.mobile.ui.composables.components.TopBar
import elder.ly.mobile.ui.composables.components.DefaultDropdownMenu
import elder.ly.mobile.ui.composables.components.NextButton
import elder.ly.mobile.ui.composables.components.SpecialtyList
import elder.ly.mobile.ui.theme.customBlueColor


@Composable
fun ProfessionalInfoScreen(showTopBar: Boolean = true, showBottomBar: Boolean = true, navController: NavController) {
    var especialidades by remember { mutableStateOf("") }
    var selectedSpecialties by remember { mutableStateOf<List<String>>(emptyList()) }


    Scaffold (
        topBar = {
            if (showTopBar){
                TopBar(
                    title = "Profissional",
                    modifier = Modifier.padding(top = 44.dp),
                    navController = navController
                )
            }
        },
        bottomBar = {
            if (showBottomBar){
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

            var biografia by remember {
                mutableStateOf("")
            }

            Biografia(valorCampo = biografia){
                    novaBiografia -> biografia = novaBiografia
            }


            DefaultDropdownMenu(
                label = "Especialidades",
                placeholder = "Selecione Especialidade(s)",
                options = listOf("Fraldas", "Bingo", "Medicação"),
                value = especialidades,
                changeValue = { newEspecialidades ->
                    especialidades = newEspecialidades
                    if (newEspecialidades.isNotEmpty() && !selectedSpecialties.contains(newEspecialidades)) {
                        selectedSpecialties = selectedSpecialties + newEspecialidades
                    }
                }
            )

            SpecialtyList(specialties = selectedSpecialties, onRemove = { specialty ->
                selectedSpecialties = selectedSpecialties - specialty
            })

            Spacer(modifier = Modifier.weight(1f))

            NextButton(
                label = "Salvar",
                onclick = {
                    navController.navigate(Profile)
                }
            )
        }
    }
}

@Composable
fun Biografia(
    valorCampo: String,
    mudaValorCampo: (String) -> Unit
){
    Column (
        modifier = Modifier
            .width(320.dp)
            .padding(top = 8.dp)
    ){
        Text(text = "Biografia")
        OutlinedTextField(
            placeholder = { Text(text = "Digite sua biografia...", color = Color.Gray) },
            value = valorCampo,
            onValueChange = mudaValorCampo,
            modifier = Modifier
                .fillMaxWidth()
                .height(320.dp)
                .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(12.dp)),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = customBlueColor,
                disabledContainerColor = customBlueColor
            ),
            shape = RoundedCornerShape(12.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfessionalInfoScreenPreview() {
    val navController = rememberNavController()
    MobileTheme {
        ProfessionalInfoScreen(navController = navController)
    }
}