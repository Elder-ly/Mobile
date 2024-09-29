package elder.ly.mobile.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import elder.ly.mobile.R
import elder.ly.mobile.R.drawable.ic_launcher_background
import elder.ly.mobile.ui.theme.secondaryContainerLight
import elder.ly.mobile.ui.theme.tertiaryContainerLight

@Composable
fun CardCuidador(){
    Column (
        modifier = Modifier
            .height(232.dp)
            .padding(top = 16.dp)
    ){
        Column (
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = tertiaryContainerLight,
                    shape = RoundedCornerShape(4.dp)
                )
                .padding(16.dp)
        ){
            Row(
                modifier = Modifier
                    .padding(bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                ImageCuidador(modifier = Modifier.size(48.dp))

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