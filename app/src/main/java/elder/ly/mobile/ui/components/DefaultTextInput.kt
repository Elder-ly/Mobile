package elder.ly.mobile.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import elder.ly.mobile.ui.theme.outlineLight
import elder.ly.mobile.ui.theme.primaryContainerLight
import elder.ly.mobile.ui.theme.tertiaryContainerLight

@Composable
fun DefaultTextInput(
    label: String,
    placeholder: String = label,
    value: String,
    changeValue: (String) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Text,
    mask: VisualTransformation = VisualTransformation.None,
    maxChar: Int = 255
) {
    val unfocusedBorderColor = outlineLight
    val focusedBorderColor = primaryContainerLight

    Column(
        modifier = Modifier
            .padding(vertical = 5.dp)
    ) {
        Text(text = label)

        OutlinedTextField(
            modifier = Modifier
                .width(320.dp),
            placeholder = { Text(text = placeholder, color = tertiaryContainerLight) },
            value = value,
            onValueChange = { newValue: String ->
                if(newValue.length <= maxChar) changeValue(newValue)
            },
            visualTransformation = mask,
            maxLines = 1,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            shape = RoundedCornerShape(10.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = unfocusedBorderColor, // Usando a cor animada
                focusedBorderColor = focusedBorderColor,   // Usando a cor animada
                cursorColor = focusedBorderColor
            )
        )
    }
}