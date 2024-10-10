package elder.ly.mobile.ui.screens.chat

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import elder.ly.mobile.ui.theme.primaryLight


@Composable
fun ChatTopBar() {
    Column(
        modifier = Modifier
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier

                .align(Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                contentDescription = "Seta para a esquerda",
                modifier = Modifier.size(50.dp),
                tint = Color.Black
            )
            Spacer(modifier = Modifier.weight(2f))
            Row(
                modifier = Modifier
                    .align(Alignment.CenterVertically),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(35.dp)
            ) {
                DrawMiniCircle()
                Column {
                    Text(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        text = "Maria Antonieta")
                    Text(
                        color = primaryLight,
                        text = "Online")
                }
            }
            Spacer(modifier = Modifier.weight(10f))
            Icon(
                imageVector = Icons.Filled.Info,
                contentDescription = "Seta para a esquerda",
                modifier = Modifier.size(30.dp),
                tint = Color.Black
            )
        }
    }
}

@Composable
fun DrawMiniCircle() {
    Canvas(modifier = Modifier
        .height(1.dp)) {
        drawCircle(
            color = Color.Gray,
            radius = 60f,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ChatTopBarPreview(modifier: Modifier = Modifier) {
    ChatTopBar()
}


