package elder.ly.mobile.ui.composables.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import elder.ly.mobile.ui.viewmodel.ProfileViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ImageCuidador(modifier: Modifier = Modifier, url: String = "") {
    IconButton(
        onClick = { /*TODO*/ },
        modifier = modifier.size(40.dp)
    ) {
        if (url == "") {
            Icon(
                imageVector = Icons.Filled.AccountCircle,
                contentDescription = "Foto do Cuidador",
                modifier = modifier.size(40.dp),
                tint = Color.Gray
            )
        } else{
            AsyncImage(
                model = url,
                contentDescription = "Foto do Cuidador",
                modifier = modifier.size(40.dp)
            )
        }
    }
}