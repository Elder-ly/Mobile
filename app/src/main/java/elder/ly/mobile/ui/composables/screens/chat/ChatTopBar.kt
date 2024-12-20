package elder.ly.mobile.ui.composables.screens.chat

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import elder.ly.mobile.Profile
import elder.ly.mobile.ProfileDetails
import elder.ly.mobile.domain.service.UserConversationOutput
import elder.ly.mobile.ui.composables.components.BackIconButton
import elder.ly.mobile.ui.composables.components.ImageCuidador
import elder.ly.mobile.ui.theme.primaryLight


@Composable
fun ChatTopBar(navController: NavController, conversation: UserConversationOutput) {
    Column(
        modifier = Modifier
            .padding(12.dp)
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            BackIconButton (modifier = Modifier.size(50.dp), onclick = {navController.popBackStack()})
            Row (
                modifier = Modifier
                    .clickable { navController.navigate(Profile) }
            ){
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    ImageCuidador(Modifier.size(48.dp).clickable { navController.navigate(ProfileDetails) }, url = conversation.fotoPerfil ?: "")
                    Column {
                        Text(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            text = conversation.nome)
                        Text(
                            color = primaryLight,
                            text = "Cuidador(a)")
                    }
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

//@Preview(showBackground = true)
//@Composable
//fun ChatTopBarPreview(modifier: Modifier = Modifier) {
//    val navController = rememberNavController()
//    ChatTopBar(navController = navController)
//}