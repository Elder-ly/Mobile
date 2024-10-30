package elder.ly.mobile.ui.composables.screens.searchresult

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import elder.ly.mobile.ProfileDetails
import elder.ly.mobile.R.drawable.ic_pesquisar
import elder.ly.mobile.Search
import elder.ly.mobile.ui.components.BottomBar
import elder.ly.mobile.ui.composables.components.CardCuidador
import elder.ly.mobile.ui.theme.MobileTheme
import elder.ly.mobile.ui.theme.tertiaryContainerLight

@Composable
fun SearchResultScreen(showBottomBar: Boolean = true, navController: NavController) {
    Scaffold (
        bottomBar = {
            if (showBottomBar){
                BottomBar(navController = navController)
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
                Search(navController)
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
fun Search(navController: NavController){
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
                text = "20/08 9:00 - 20/08 20:00",
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Troca de Fralda, Medicação, Bingo"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchResultScreenPreview() {
    MobileTheme {
        val navController = rememberNavController()
        SearchResultScreen(navController = navController)
    }
}