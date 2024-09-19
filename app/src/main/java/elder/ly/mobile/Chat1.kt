package elder.ly.mobile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import elder.ly.mobile.R.drawable.ic_pesquisar
import elder.ly.mobile.ui.components.Feature
import elder.ly.mobile.ui.components.NavBar
import elder.ly.mobile.ui.theme.MobileTheme
import elder.ly.mobile.ui.theme.secondaryContainerLight
import elder.ly.mobile.ui.theme.tertiaryContainerLight

@Composable
fun Chat1() {
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(top = 16.dp, bottom = 0.dp, start = 16.dp, end = 16.dp)
        ) {
            SearchChat()
        }

        LazyColumn(
            modifier = Modifier
                .weight(1f)
        ) {
            items(10) {
                Contacts()
            }
        }

        NavBar()
    }
}

@Composable
fun SearchChat() {
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
                shape = RoundedCornerShape(8.dp)
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
    }
}

@Composable
fun Contacts() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 8.dp, top = 8.dp)
                .height(88.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "Foto de perfil do cuidador",
                modifier = Modifier
                    .size(48.dp)
                    .align(Alignment.CenterVertically),
                Alignment.CenterStart
            )

            Column(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .align(Alignment.CenterVertically)

            ) {
                Text(text = "Vila Matilde", color = secondaryContainerLight)
                Text(text = "Maria Antonieta", fontSize = 18.sp)
                LazyRow {
                    items(listOf("Fraldas", "Bingo", "Medicação")) { featureText ->
                        Feature(text = featureText)
                    }
                }
            }
            Column (
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically),
                horizontalAlignment = Alignment.End
            ){
                Arrow()
            }
        }
        HorizontalDivider(modifier = Modifier.fillMaxWidth(), thickness = 1.dp, color = Color.Gray)
    }
}

@Composable
fun Arrow() {
    Column{
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = "Arrow Forward",
            tint = Color(0xFF343643),
            modifier = Modifier
                .size(48.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Chat1Preview() {
    MobileTheme {
        Chat1()
    }
}
