package elder.ly.mobile.ui.composables.screens.profile

import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.auth.api.Auth
import elder.ly.mobile.AddressInfo
import elder.ly.mobile.PersonalInfo
import elder.ly.mobile.ProfessionalInfo
import elder.ly.mobile.Welcome
import elder.ly.mobile.domain.model.enums.TypeUserEnum
import elder.ly.mobile.ui.composables.components.BottomBar
import elder.ly.mobile.ui.composables.components.ImageCuidador
import elder.ly.mobile.ui.theme.tertiaryLight
import elder.ly.mobile.ui.viewmodel.AuthViewModel
import elder.ly.mobile.ui.viewmodel.ProfileViewModel
import elder.ly.mobile.utils.getUser
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ProfileScreen(showBottomBar: Boolean = true, navController: NavController) {
    val viewModel = koinViewModel<ProfileViewModel>()
    val user by viewModel.user.collectAsState()
    val context = LocalContext.current

    val authViewModel = koinViewModel<AuthViewModel>()
    LaunchedEffect(key1 = Unit) {
        getUser(context).collect { userId ->
            viewModel.userId = userId.id ?: -1
            viewModel.getUserProfile()
        }
    }

    Scaffold (
        bottomBar = {
            if (showBottomBar){
                BottomBar(navController = navController, colorBlueProfile = true)
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 30.dp, start = 16.dp, end = 16.dp)
            ) {
                ImageCuidador(modifier = Modifier
                    .size(160.dp)
                    .align(Alignment.CenterHorizontally),
                    url = user?.fotoPerfil ?: ""
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 36.sp,
                    color = Color.Black,
                    text = user?.nome ?: "",
                    lineHeight = 40.sp
                )
                MenuButton(label = "Informações Pessoais", icon = Icons.Filled.AccountCircle,
                    onclick = { navController.navigate(PersonalInfo) })

                MenuButton(label = "Endereço", icon = Icons.Filled.Home,
                    onclick = { navController.navigate(AddressInfo) })

                if (user?.tipoUsuario == TypeUserEnum.COLLABORATOR.id) {
                    MenuButton(label = "Profissional", icon = Icons.AutoMirrored.Filled.List,
                        onclick = { navController.navigate(ProfessionalInfo) })
                }

                MenuButton(label = "Sair", icon = Icons.AutoMirrored.Filled.ExitToApp,
                    onclick = {
                        authViewModel.signOut(
                            context = context,
                            onSuccess = {
                                navController.navigate(Welcome)
                            },
                            onError = { exception ->
                                println("exception:" + exception.message)
                                Toast.makeText(context, "Error signing out: ${exception.message}", Toast.LENGTH_LONG).show()
                            }
                        )
                    })
            }
        }
    }
}


@Composable
fun MenuButton(label: String, icon: ImageVector, onclick: () -> Any) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { }
            .padding(16.dp)
            .clickable {
                onclick()
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(32.dp),
            tint = tertiaryLight
        )
        Spacer(Modifier.width(16.dp))
        Text(
            text = label,
            fontSize = 16.sp,
            color = tertiaryLight,
            modifier = Modifier.weight(1f)
        )
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = "Seta para a direita",
            modifier = Modifier.size(30.dp),
            tint = tertiaryLight
        )
    }
    Divisor()
}


@Composable
fun Divisor() {
    HorizontalDivider(
        color = tertiaryLight,
        thickness = 1.2.dp,
        modifier = Modifier.fillMaxWidth()
    )
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    ProfileScreen(navController = navController)
}