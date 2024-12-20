package elder.ly.mobile.ui.composables.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import elder.ly.mobile.ui.theme.customBlueColor

@Composable
fun Feature(text: String, fontSize: TextUnit = 16.sp, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .background(customBlueColor, RoundedCornerShape(4.dp))
            .padding(horizontal = 8.dp)
    ) {
        Text(
            text = text,
            fontSize = fontSize,
            modifier = Modifier.align(Alignment.Center)
        )
    }
    Spacer(modifier = Modifier.width(8.dp))
}