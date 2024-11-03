package elder.ly.mobile.ui.composables.screens.searchresult

import android.util.Log
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
import elder.ly.mobile.R.drawable.ic_pesquisar
import elder.ly.mobile.Search
import elder.ly.mobile.SearchCriteria
import elder.ly.mobile.ui.composables.components.BottomBar
import elder.ly.mobile.ui.composables.components.CardCuidador
import elder.ly.mobile.ui.theme.tertiaryContainerLight

@Composable
fun SearchResultScreen(showBottomBar: Boolean = true, navController: NavController, criteria: SearchCriteria?) {
    // val viewModel = koinViewModel<SearchResultViewModel>()

    Scaffold (
        bottomBar = {
            if (showBottomBar){
                BottomBar(navController = navController, colorBlueSearch = true)
            }
        }
    ){ paddingValues ->
        Column (
            modifier = Modifier
                .padding(paddingValues)
        ){
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 16.dp, bottom = 0.dp, start = 16.dp, end = 16.dp)
            ) {
                Search(navController, criteria)
                Spacer(modifier = Modifier.size(16.dp))
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    items(10) {
                        CardCuidador(navController = navController)
                    }
                }
            }
        }
    }
}

@Composable
fun Search(navController: NavController, criteria: SearchCriteria?) {
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
            criteria?.let {
                Log.d("SearchResultScreen", "Criteria não é nulo: $it")
                Text(
                    text = "${it.startDate} ${it.startTime} - ${it.endDate} ${it.endTime}",
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = it.specialties.joinToString(", ") // Presumindo que specialties é uma lista
                )
            } ?: run {
                Log.d("SearchResultScreen", "Criteria é nulo.")
                Text(text = "Data e horário indisponíveis", fontWeight = FontWeight.Bold)
                Text(text = "Nenhuma especialidade selecionada")
            }
        }
    }
}
//
//@Preview(showBackground = true)
//@Composable
//fun SearchResultScreenPreview() {
//    MobileTheme {
//        val navController = rememberNavController()
//        SearchResultScreen(navController = navController, criteria = SearchCriteria())
//    }
//}
