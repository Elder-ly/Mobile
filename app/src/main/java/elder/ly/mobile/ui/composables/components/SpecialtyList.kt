package elder.ly.mobile.ui.composables.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SpecialtyList(specialties: List<String>, onRemove: (String) -> Unit) {
    FlowRow(
        modifier = Modifier
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        specialties.forEach { specialty ->
            SpecialtyItem(
                text = specialty,
                onRemove = { onRemove(specialty) }
            )
        }
    }
}

@Composable
fun SpecialtyItem(text: String, onRemove: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(bottom = 8.dp)
            .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(8.dp))
            .background(Color.Transparent, shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 2.dp)
            .clip(RoundedCornerShape(8.dp)),

        ) {
        Row(
            modifier = Modifier
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = text,
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(end = 8.dp),
                color = Color.Black
            )
            IconButton(
                onClick = onRemove,
                modifier = Modifier.size(20.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Clear,
                    contentDescription = "Delete",
                    tint = Color.Black
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewSpecialtyList() {
    var specialties by remember { mutableStateOf(listOf("Cardiology", "Dermatology", "Neurology")) }

    SpecialtyList(specialties = specialties) { removedSpecialty ->
        specialties = specialties.filter { it != removedSpecialty }
    }
}