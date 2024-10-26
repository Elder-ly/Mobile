package elder.ly.mobile.ui.composables.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.navigation.NavController
import elder.ly.mobile.ProfileDetails
import elder.ly.mobile.R
import elder.ly.mobile.R.drawable.ic_launcher_background
import elder.ly.mobile.Search
import elder.ly.mobile.ui.theme.secondaryContainerLight
import elder.ly.mobile.ui.theme.tertiaryContainerLight

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CardCuidador(modifier: Modifier = Modifier, navController: NavController){
    Column (
        modifier = modifier
            .border(
                width = 1.dp,
                color = tertiaryContainerLight,
                shape = RoundedCornerShape(4.dp)
            )
            .clickable { navController.navigate(ProfileDetails) }
            .padding(16.dp)
    ){
        Row(
            modifier = modifier
                .padding(bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            ImageCuidador(modifier = modifier.size(48.dp))

            Spacer(modifier = modifier.size(8.dp))

            Column (
                modifier = modifier
                    .weight(1f)
            ){
                Text(text = "Vila Matilde", color = secondaryContainerLight)
                Text(text = "Maria Antonieta", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            }
            Column {
                Text(text = "R$150/hora")
            }
        }

        Text(text = "Sou uma pessoa dedicada e experiente, comprometida em proporcionar cuidados compassivos e de qualidade para seus entes queridos. Com habilidades abrangentes em assistência diária, incluindo higiene.")

        Spacer(modifier = modifier.size(8.dp))

        FlowRow(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Feature(text = "Fraldas")
            Feature(text = "Bingo")
            Feature(text = "Medicação")
            Feature(text = "Medicação")
            Feature(text = "Medicação")
        }
    }
    Spacer(modifier = Modifier.padding(bottom = 8.dp))
}