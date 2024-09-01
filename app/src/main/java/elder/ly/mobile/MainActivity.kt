package elder.ly.mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import elder.ly.mobile.ui.theme.MobileTheme
import elder.ly.mobile.ui.theme.outlineLight
import elder.ly.mobile.ui.theme.outlineVariantLight
import elder.ly.mobile.ui.theme.primaryContainerLight
import elder.ly.mobile.ui.theme.primaryLight
import elder.ly.mobile.ui.theme.tertiaryContainerLight
import elder.ly.mobile.ui.theme.tertiaryLight

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            App()
        }
    }
}

@Composable
fun App() {
    Login2()
}

@Composable
fun Login2() {
    var fullName by remember {
        mutableStateOf("")
    }

    var email by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append("Cadastro")
                }
            },
            fontSize = 36.sp,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        DefaultTextInput(
            label = "Nome Completo",
            value = fullName,
            changeValue = { newFullName : String ->
                fullName = newFullName
            }
        )

        DefaultTextInput(
            label = "Email",
            value = email,
            changeValue = { newEmail : String ->
                email = newEmail
            }
        )
    }
}

@Composable
fun DefaultTextInput(
    label: String,
    placeholder: String = label,
    value: String,
    changeValue: (String) -> Unit
) {
    var isFocused by remember { mutableStateOf(false) }

    // Definindo as cores para estado focado e não focado
    val unfocusedBorderColor = outlineLight
    val focusedBorderColor = primaryContainerLight

    // Animação da cor da borda
    val borderColor by animateColorAsState(
        targetValue = if (isFocused) focusedBorderColor else unfocusedBorderColor,
        animationSpec = tween(durationMillis = 300)
    )

    Column(
        modifier = Modifier
            .padding(vertical = 8.dp)
    ) {
        Text(text = label)

        OutlinedTextField(
            modifier = Modifier
                .width(320.dp)
                .onFocusChanged { focusState ->
                    isFocused = focusState.isFocused
                },
            placeholder = { Text(text = placeholder, color = tertiaryContainerLight) },
            value = value,
            onValueChange = { newValue: String ->
                changeValue(newValue)
            },
            maxLines = 1,
            shape = RoundedCornerShape(10.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = borderColor, // Usando a cor animada
                focusedBorderColor = borderColor,   // Usando a cor animada
                cursorColor = focusedBorderColor
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MobileTheme {
        App()
    }
}