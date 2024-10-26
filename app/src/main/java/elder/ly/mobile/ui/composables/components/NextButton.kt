package elder.ly.mobile.ui.composables.components

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import elder.ly.mobile.ui.theme.onPrimaryLightHighContrast
import elder.ly.mobile.ui.theme.primaryContainerLight

@Composable
fun NextButton(
    label: String,
    onclick: () -> Any,
    icon: ImageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
    modifier: Modifier = Modifier
) {
    var isLoading by remember { mutableStateOf(false) }

    Button(
        onClick = {
            isLoading = true
            onclick()
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = primaryContainerLight, // Cor de fundo azul
            contentColor = onPrimaryLightHighContrast // Cor do texto e ícone
        ),
        shape = RoundedCornerShape(8.dp), // Borda arredondada
        modifier = modifier
            .padding(bottom = 16.dp)
            .width(320.dp)
            .height(56.dp)
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                color = onPrimaryLightHighContrast,
                modifier = Modifier.size(24.dp) // Tamanho do spinner
            )
        } else {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Spacer(modifier = Modifier.size(36.dp))
                Text(
                    text = label,
                    fontSize = 16.sp,
                )
                Icon(
                    imageVector = icon,
                    contentDescription = "Seta para a direita",
                    modifier = Modifier
                        .size(36.dp)
                )
            }
        }
    }
}