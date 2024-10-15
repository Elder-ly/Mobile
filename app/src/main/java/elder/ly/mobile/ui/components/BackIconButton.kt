package elder.ly.mobile.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun BackIconButton(modifier: Modifier = Modifier, onclick: () -> Unit) {
    IconButton(
        onClick = onclick,
        modifier = modifier
            .size(56.dp)
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowLeft,
            contentDescription = "Voltar",
            modifier = modifier
                .size(56.dp)
        )
    }
}