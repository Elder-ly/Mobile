package elder.ly.mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import elder.ly.mobile.ui.screens.addressinfo.AddressInfoScreen
import elder.ly.mobile.ui.screens.chatlist.ChatListScreen
import elder.ly.mobile.ui.screens.professionalinfo.ProfessionalInfoScreen
import elder.ly.mobile.ui.screens.professionalinfo.ProfessionalInfoScreenPreview
import elder.ly.mobile.ui.screens.search.SearchScreen
import elder.ly.mobile.ui.screens.searchresult.SearchResultScreen
import elder.ly.mobile.ui.screens.signupstep1.SignUpStep1Screen
import elder.ly.mobile.ui.screens.signupstep2.SignUpStep2Screen
import elder.ly.mobile.ui.screens.welcome.WelcomeScreen
import elder.ly.mobile.ui.theme.MobileTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Scaffold(modifier = Modifier.fillMaxSize().navigationBarsPadding()) { innerPadding ->
                    App(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun App(modifier: Modifier = Modifier) {
    WelcomeScreen()
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    MobileTheme {
        App()
    }
}