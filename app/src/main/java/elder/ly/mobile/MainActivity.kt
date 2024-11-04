package elder.ly.mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import elder.ly.mobile.di.appModule
import elder.ly.mobile.ui.composables.screens.addressinfo.AddressInfoScreen
import elder.ly.mobile.ui.composables.screens.chat.ChatScreen
import elder.ly.mobile.ui.composables.screens.chatlist.ChatListScreen
import elder.ly.mobile.ui.composables.screens.personalinfo.PersonalInfoScreen
import elder.ly.mobile.ui.composables.screens.professionalinfo.ProfessionalInfoScreen
import elder.ly.mobile.ui.composables.screens.profile.ProfileScreen
import elder.ly.mobile.ui.composables.screens.profiledetails.ProfileDetailsScreen
import elder.ly.mobile.ui.composables.screens.search.SearchScreen
import elder.ly.mobile.ui.composables.screens.searchresult.SearchResultScreen
import elder.ly.mobile.ui.composables.screens.signupstep1.SignUpStep1Screen
import elder.ly.mobile.ui.composables.screens.signupstep2.SignUpStep2Screen
import elder.ly.mobile.ui.composables.screens.welcome.WelcomeScreen
import elder.ly.mobile.ui.theme.MobileTheme
import kotlinx.serialization.Serializable
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        startKoin {
//            androidLogger()
            androidContext(this@MainActivity) // this@MainActivity
//            androidContext(applicationContext) // serve para a aplicação que o module vai servir pra
            modules(appModule)
        }
        setContent {
            MaterialTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .navigationBarsPadding()
                ) { innerPadding ->
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