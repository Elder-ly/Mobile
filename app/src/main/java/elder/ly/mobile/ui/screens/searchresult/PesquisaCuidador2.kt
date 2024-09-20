package elder.ly.mobile.ui.screens.searchresult

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.unit.sp
import elder.ly.mobile.R.drawable.ic_launcher_background
import elder.ly.mobile.R.drawable.ic_pesquisar
import elder.ly.mobile.ui.components.Feature
import elder.ly.mobile.ui.components.NavBar
import elder.ly.mobile.ui.theme.MobileTheme
import elder.ly.mobile.ui.theme.secondaryContainerLight
import elder.ly.mobile.ui.theme.tertiaryContainerLight

@Composable
fun PesquisaCuidador2() {
    Column (
        modifier = Modifier
            .background(Color.White)
    ){
        Column(
            modifier = Modifier
                .weight(1f)
                .background(Color.White)
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
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(16.dp),
                clip = false
            )
            .background(
                color = Color.White,
                shape = RoundedCornerShape(8.dp)
            )
            .border(
                width = 1.dp,
                color = tertiaryContainerLight,
                shape = RoundedCornerShape(4.dp)
            )
            .padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = ic_pesquisar),
            contentDescription = "Ícone de pesquisa",
            modifier = Modifier
                .size(48.dp)
                .align(alignment = Alignment.CenterStart)
                .padding(start = 8.dp)
        )
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(start = 32.dp)
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

@Composable
fun CardCuidador(){
    Column (
        modifier = Modifier
            .height(232.dp)
            .padding(top = 16.dp)
    ){
        Column (
            modifier = Modifier
                .shadow(
                    elevation = 4.dp,
                    shape = RoundedCornerShape(16.dp),
                    clip = false
                )
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(8.dp)
                )
                .border(
                    width = 1.dp,
                    color = tertiaryContainerLight,
                    shape = RoundedCornerShape(4.dp)
                )
                .padding(16.dp)
        ){
            Row(
                modifier = Modifier
                    .padding(bottom = 8.dp)
            ){
                Image(
                    painter = painterResource(ic_launcher_background),
                    contentDescription = "Foto do Cuidador de Idoso",
                    modifier = Modifier
                        .size(48.dp)
                )
                Column (
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp)
                ){
                    Text(text = "Vila Matilde", color = secondaryContainerLight)
                    Text(text = "Maria Antonieta", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                }
                Column {
                    Text(text = "R$150/hora")
                }
            }
            Row (
                modifier = Modifier
                    .weight(1f)
            ){
                Text(text = "Sou uma pessoa dedicada e experiente, comprometida em proporcionar cuidados compassivos e de qualidade para seus entes queridos. Com habilidades abrangentes em assistência diária, incluindo higiene")
            }

            Row (
                modifier = Modifier
            ){
                Feature(text = "Fraldas")
                Feature(text = "Bingo")
                Feature(text = "Medicação")

            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun PesquisaCuidadorPreview() {
    MobileTheme {
        PesquisaCuidador2()
    }
}