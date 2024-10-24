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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import elder.ly.mobile.ui.screens.addressinfo.AddressInfoScreen
import elder.ly.mobile.ui.screens.chat.ChatScreen
import elder.ly.mobile.ui.screens.chatlist.ChatListScreen
import elder.ly.mobile.ui.screens.personalinfo.PersonalInfoScreen
import elder.ly.mobile.ui.screens.professionalinfo.ProfessionalInfoScreen
import elder.ly.mobile.ui.screens.professionalinfo.ProfessionalInfoScreenPreview
import elder.ly.mobile.ui.screens.profile.ProfileScreen
import elder.ly.mobile.ui.screens.profiledetails.ProfileDetailsScreen
import elder.ly.mobile.ui.screens.search.SearchScreen
import elder.ly.mobile.ui.screens.searchresult.SearchResultScreen
import elder.ly.mobile.ui.screens.signupstep1.SignUpStep1Screen
import elder.ly.mobile.ui.screens.signupstep2.SignUpStep2Screen
import elder.ly.mobile.ui.screens.welcome.WelcomeScreen
import elder.ly.mobile.ui.theme.MobileTheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Scaffold(modifier = Modifier
                    .fillMaxSize()
                    .navigationBarsPadding()) { innerPadding ->
                    App(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Serializable
object Welcome

@Serializable
object SignUpStep1

@Serializable
object SignUpStep2

@Serializable
object Search

@Serializable
object SearchResult

@Serializable
object ProfileDetails

@Serializable
object Profile

@Serializable
object ProfessionalInfo

@Serializable
object PersonalInfo

@Serializable
object ChatList

@Serializable
object Chat

@Serializable
object AddressInfo

@Composable
fun App(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Welcome) {
        composable<Welcome> {
            WelcomeScreen(navController = navController)
        }

        composable<SignUpStep1> {
            SignUpStep1Screen(navController = navController)
        }

        composable<SignUpStep2> {
            SignUpStep2Screen(navController = navController)
        }

        composable<Search> {
            SearchScreen(navController = navController)
        }

        composable<SearchResult> {
            SearchResultScreen(navController = navController)
        }

        composable<Profile> {
            ProfileScreen(navController = navController)
        }

        composable<ProfileDetails> {
            ProfileDetailsScreen(navController = navController)
        }

        composable<ProfessionalInfo> {
            ProfessionalInfoScreen(navController = navController)
        }

        composable<PersonalInfo> {
            PersonalInfoScreen(navController = navController)
        }

        composable<ChatList> {
            ChatListScreen(navController = navController)
        }

        composable<Chat> {
            ChatScreen(navController = navController)
        }

        composable<AddressInfo> {
            AddressInfoScreen(navController = navController)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    MobileTheme {
        App()
    }
}