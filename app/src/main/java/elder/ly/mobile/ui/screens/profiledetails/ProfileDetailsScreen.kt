package elder.ly.mobile.ui.screens.profiledetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import elder.ly.mobile.Chat
import elder.ly.mobile.SignUpStep2
import elder.ly.mobile.ui.components.BottomBar
import elder.ly.mobile.ui.components.Feature
import elder.ly.mobile.ui.components.NextButton
import elder.ly.mobile.ui.screens.professionalinfo.Biografia
import elder.ly.mobile.ui.screens.profile.DrawCircle
import elder.ly.mobile.ui.theme.onPrimaryLightHighContrast
import elder.ly.mobile.ui.theme.primaryContainerLight
import elder.ly.mobile.ui.theme.primaryLight

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProfileDetailsScreen(
    modifier: Modifier = Modifier
        .fillMaxSize(),
    showBottomBar: Boolean = true,
    navController: NavController
) {
    var biografia by remember {
        mutableStateOf("")
    }


    Scaffold (
        bottomBar = {
            if (showBottomBar){
                BottomBar(navController = navController)
            }
        }
    ){ paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 30.dp, start = 16.dp, end = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                DrawCircle()
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 36.sp,
                    color = Color.Black,
                    text = "Maria Antonieta"
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    color = primaryLight,
                    text = "Vila Matilde"
                )

                FlowRow(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Feature(text = "Fraldas")
                    Feature(text = "Medicação")
                    Feature(text = "Acompanhamento")
                }

                Biografia(valorCampo = biografia){
                    novaBiografia -> biografia = novaBiografia
                }
            }

            Text(
                modifier = Modifier.fillMaxWidth(),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                text = "R$150/hora"
            )

            Spacer(modifier = Modifier.size(8.dp))

            NextButton(
                label = "Conversar",
                onclick = {
                    navController.navigate(Chat)
                }
            )
        }
    }


}


@Preview(showBackground = true)
@Composable
fun ProfileDetailsScreenPreview(
    modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    ProfileDetailsScreen(navController = navController)
}