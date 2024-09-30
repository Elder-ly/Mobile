package elder.ly.mobile.ui.screens.profile

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
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
import elder.ly.mobile.ui.components.BottomBar
import elder.ly.mobile.ui.components.ImageCuidador
import elder.ly.mobile.ui.components.TopBar
import elder.ly.mobile.ui.theme.tertiaryLight

@Composable
fun ProfileScreen(showBottomBar: Boolean = true) {
    Scaffold (
        bottomBar = {
            if (showBottomBar){
                BottomBar()
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 30.dp, start = 16.dp, end = 16.dp)
            ) {
                ImageCuidador(modifier = Modifier.size(144.dp).align(Alignment.CenterHorizontally))
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 36.sp,
                    color = Color.Black,
                    text = "Maria Antonieta"
                )
                MenuButton(label = "Informações Pessoais", icon = Icons.Filled.AccountCircle)
                MenuButton(label = "Endereço", icon = Icons.Filled.Home)
                MenuButton(label = "Profissional", icon = Icons.AutoMirrored.Filled.List)
                MenuButton(label = "Sair", icon = Icons.Filled.ExitToApp)
            }
        }
    }
}

@Composable
fun MenuButton(label: String, icon: androidx.compose.ui.graphics.vector.ImageVector) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(32.dp),
            tint = tertiaryLight
        )
        Spacer(Modifier.width(16.dp))
        Text(
            text = label,
            fontSize = 16.sp,
            color = tertiaryLight,
            modifier = Modifier.weight(1f)
        )
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = "Seta para a direita",
            modifier = Modifier.size(30.dp),
            tint = tertiaryLight
        )
    }
    Divisor()
}


@Composable
fun Divisor() {
    HorizontalDivider(
        color = tertiaryLight,
        thickness = 1.2.dp,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun DrawCircle() {
    Canvas(modifier = Modifier
        .fillMaxWidth()
        .height(170.dp)) {
        drawCircle(
            color = tertiaryLight,
            radius = 180f,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview(modifier: Modifier = Modifier) {
    ProfileScreen()
}