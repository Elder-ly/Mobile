package elder.ly.mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import elder.ly.mobile.ui.theme.MobileTheme
import elder.ly.mobile.ui.theme.tertiaryContainerLight
import elder.ly.mobile.R.drawable.ic_pesquisar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            App()
        }
    }
}

@Composable
fun App() {
    Search()
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MobileTheme {
        App()
    }
}