package elder.ly.mobile.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import elder.ly.mobile.ui.theme.secondaryContainerLight
import elder.ly.mobile.ui.theme.tertiaryContainerLight

@Composable
fun Contacts() {
        Row(
            modifier = Modifier
                .padding(start = 12.dp, end = 12.dp, bottom = 8.dp, top = 8.dp)
                .height(88.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ImageCuidador(modifier = Modifier.size(56.dp))

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
                KeyboardArrowRight()
            }
        }
        HorizontalDivider(modifier = Modifier.fillMaxWidth(), thickness = 1.dp, color = tertiaryContainerLight)
}

@Composable
fun KeyboardArrowRight() {
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