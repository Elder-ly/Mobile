package elder.ly.mobile.ui.screens.searchresult

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
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
import elder.ly.mobile.R.drawable.ic_pesquisar
import elder.ly.mobile.ui.components.CardCuidador
import elder.ly.mobile.ui.components.NavBar
import elder.ly.mobile.ui.theme.MobileTheme
import elder.ly.mobile.ui.theme.tertiaryContainerLight

@Composable
fun SearchResultScreen() {
    Column (
        modifier = Modifier
            .background(Color.White)
    ){
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(top = 16.dp, bottom = 0.dp, start = 16.dp, end = 16.dp)
        ) {
            Search()

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
            ) {
                items(10) {
                    CardCuidador()
                }
            }

        }
        NavBar()
    }
}

@Composable
fun Search(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = tertiaryContainerLight,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
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
        SearchResultScreen()
    }
}