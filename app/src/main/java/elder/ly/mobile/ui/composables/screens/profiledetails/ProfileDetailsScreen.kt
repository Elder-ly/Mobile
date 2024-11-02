package elder.ly.mobile.ui.composables.screens.profiledetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import elder.ly.mobile.ui.composables.components.BottomBar
import elder.ly.mobile.ui.composables.components.Feature
import elder.ly.mobile.ui.composables.components.ImageCuidador
import elder.ly.mobile.ui.composables.components.NextButton
import elder.ly.mobile.ui.theme.primaryLight
import elder.ly.mobile.ui.viewmodel.ProfileDetailsViewModel
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProfileDetailsScreen(
    modifier: Modifier = Modifier
        .fillMaxSize(),
    showBottomBar: Boolean = true,
    navController: NavController
) {

    val viewModel = koinViewModel<ProfileDetailsViewModel>()


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
                ImageCuidador(modifier = Modifier.size(160.dp), viewModel.url)
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 36.sp,
                    color = Color.Black,
                    text = viewModel.nome
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    color = primaryLight,
                    text = viewModel.bairro
                )

                Spacer(modifier = Modifier.size(8.dp))

                FlowRow(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    viewModel.especialidades.forEach { especialidade ->
                        Feature(text = especialidade.name, fontSize = 14.sp)
                    }
                }

                val scrollState = rememberScrollState()
                Column (
                    modifier = Modifier.verticalScroll(scrollState)
                ){
                    Text(
                        text = viewModel.biografia,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        textAlign = TextAlign.Start
                    )
                }
            }

            Text(
                modifier = Modifier.fillMaxWidth(),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                text = "R$${viewModel.preco}/hora"
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