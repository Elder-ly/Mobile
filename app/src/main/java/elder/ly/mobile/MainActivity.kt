package elder.ly.mobile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import elder.ly.mobile.ui.theme.MobileTheme
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import elder.ly.mobile.R.drawable.ic_chat
import elder.ly.mobile.R.drawable.ic_launcher_background
import elder.ly.mobile.R.drawable.ic_perfil
import elder.ly.mobile.R.drawable.ic_pesquisar
import elder.ly.mobile.ui.theme.backgroundCustomBlue
import elder.ly.mobile.ui.theme.secondaryContainerLight
import elder.ly.mobile.ui.theme.tertiaryContainerLight


@Composable
fun App() {
        Column (
            modifier = Modifier
                .background(Color.White)
        ){
            Column(
                modifier = Modifier
                    .weight(1f)
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                Search()

                CardCuidador()
                CardCuidador()
                CardCuidador()

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
            .height(208.dp)
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
                .padding(12.dp)
        ){
            Row(
                modifier = Modifier
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
                Tag(text = "Fraldas")
                Tag(text = "Bingo")
                Tag(text = "Medicação")

            }

        }
    }
}

@Composable
fun NavBar() {
    Column(
        modifier = Modifier
            .height(80.dp)
            .fillMaxWidth()
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(8.dp),
                clip = false
            )
            .background(
                color = Color.White
            )
            .border(
                width = 1.dp,
                color = tertiaryContainerLight
            )
            .padding(top = 12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            ) {
                Image(
                    painter = painterResource(id = ic_pesquisar),
                    contentDescription = "ícone de pesquisa",
                    modifier = Modifier
                        .size(32.dp)
                        .align(Alignment.CenterHorizontally)
                )
                Text(
                    text = "Pesquisar",
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            ) {
                Image(
                    painter = painterResource(id = ic_chat),
                    contentDescription = "ícone de chat",
                    modifier = Modifier
                        .size(32.dp)
                        .align(Alignment.CenterHorizontally)
                )
                Text(
                    text = "Chat",
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            ) {
                Image(
                    painter = painterResource(id = ic_perfil),
                    contentDescription = "ícone de perfil",
                    modifier = Modifier
                        .size(32.dp)
                        .align(Alignment.CenterHorizontally)
                )
                Text(
                    text = "Perfil",
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}

@Composable
fun Tag(text: String) {
    Box(
        modifier = Modifier
            .padding(end = 8.dp)
            .background(backgroundCustomBlue, RoundedCornerShape(4.dp))
            .padding(horizontal = 8.dp)
    ) {
        Text(
            text = text,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MobileTheme {
        App()
    }
}
