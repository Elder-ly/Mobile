package elder.ly.mobile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import elder.ly.mobile.ui.theme.customBlueColor

@Composable
fun Feature(text: String, modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .padding(top = 8.dp, end = 8.dp)
            .background(customBlueColor, RoundedCornerShape(4.dp))
            .padding(horizontal = 8.dp)
    ) {
        Text(
            text = text,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}