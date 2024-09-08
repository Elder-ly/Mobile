package elder.ly.mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import elder.ly.mobile.ui.screens.InformacoesPessoaisEndereco
import elder.ly.mobile.ui.screens.Login3
import elder.ly.mobile.ui.theme.MobileTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                App()
            }
        }
    }
}

@Composable
fun App() {
    Login3()
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MobileTheme {
        App()
    }
}