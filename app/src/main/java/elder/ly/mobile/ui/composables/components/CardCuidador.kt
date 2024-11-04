package elder.ly.mobile.ui.composables.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import elder.ly.mobile.ProfileDetails
import elder.ly.mobile.domain.service.GetUsersCollaboratorOutput
import elder.ly.mobile.ui.theme.secondaryContainerLight
import elder.ly.mobile.ui.theme.tertiaryContainerLight
import elder.ly.mobile.ui.viewmodel.SearchResultViewModel
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CardCuidador(modifier: Modifier = Modifier, navController: NavController, cuidador: GetUsersCollaboratorOutput) {

    val viewModel: SearchResultViewModel = koinViewModel()

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
            ImageCuidador(modifier = modifier.size(48.dp), cuidador.fotoPerfil ?: "")

            Spacer(modifier = modifier.size(8.dp))

            Column (
                modifier = modifier
                    .weight(1f)
            ){
                Text(text = cuidador.endereco.bairro ?: "", color = secondaryContainerLight)
                Text(text = cuidador.nome, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            }
//            Column {
//                Text(text = "R$${cuidador.}/hora")
//            }
        }

        Text(text = cuidador.biografia ?: "")

        Spacer(modifier = modifier.size(8.dp))

        FlowRow(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            cuidador.especialidades.forEach { especialidade ->
                Feature(text = especialidade.name)
            }
        }
    }
    Spacer(modifier = Modifier.padding(bottom = 8.dp))
}