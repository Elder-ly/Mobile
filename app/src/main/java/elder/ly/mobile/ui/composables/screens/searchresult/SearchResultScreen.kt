package elder.ly.mobile.ui.composables.screens.searchresult

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.gson.Gson
import elder.ly.mobile.R.drawable.ic_pesquisar
import elder.ly.mobile.Search
import elder.ly.mobile.domain.service.GetDataSearchScreen
import elder.ly.mobile.domain.service.GetUsersCollaboratorOutput
import elder.ly.mobile.ui.composables.components.BottomBar
import elder.ly.mobile.ui.composables.components.CardCuidador
import elder.ly.mobile.ui.theme.tertiaryContainerLight
import elder.ly.mobile.ui.viewmodel.SearchResultViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SearchResultScreen(
    showBottomBar: Boolean = true,
    navController: NavController
) {
    val viewModel: SearchResultViewModel = koinViewModel()

    val gson = Gson()
    val savedStateHandle = navController.previousBackStackEntry?.savedStateHandle
    val sampleSearchResultInputJson = savedStateHandle?.get<String>("sampleSearchResultInputJson")
    val sampleSearchResultInput = sampleSearchResultInputJson?.let { gson.fromJson(it, GetDataSearchScreen::class.java) }

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                BottomBar(navController = navController, colorBlueSearch = true)
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 16.dp, bottom = 0.dp, start = 16.dp, end = 16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .clickable { navController.navigate(Search) }
                        .fillMaxWidth()
                        .border(
                            width = 1.dp,
                            color = tertiaryContainerLight,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        painter = painterResource(id = ic_pesquisar),
                        contentDescription = "Ícone de pesquisa",
                        modifier = Modifier
                            .size(44.dp)
                            .padding(start = 8.dp)
                    )
                    Column(
                        modifier = Modifier
                            .padding(start = 8.dp)
                    ) {

                        Text(
                            text = "${sampleSearchResultInput?.startDate} ${sampleSearchResultInput?.startTime} - ${sampleSearchResultInput?.endDate} ${sampleSearchResultInput?.endTime}",
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "${sampleSearchResultInput?.specialties}"
                        )

                    }
                }
                Spacer(modifier = Modifier.size(16.dp))
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    items(viewModel.cuidadores) { cuidador -> // Aqui, use a lista de cuidadores do ViewModel
                        CardCuidador(
                            navController = navController,
                            cuidador = cuidador // Passando o cuidador atual
                        )
                    }
                }
            }
        }
    }
}

//@Composable
//fun Search(
//    navController: NavController,
//) {
//
//}
//
//@Preview(showBackground = true)
//@Composable
//fun SearchResultScreenPreview() {
//    MobileTheme {
//        val navController = rememberNavController()
//        SearchResultScreen(navController = navController, criteria = SearchCriteria())
//    }
//}