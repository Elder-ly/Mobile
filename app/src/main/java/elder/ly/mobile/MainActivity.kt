package elder.ly.mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import elder.ly.mobile.ui.theme.MobileTheme
import elder.ly.mobile.ui.theme.onPrimaryLightHighContrast
import elder.ly.mobile.ui.theme.primaryContainerLight
import elder.ly.mobile.ui.theme.primaryDark
import elder.ly.mobile.ui.theme.tertiaryContainerLight
import elder.ly.mobile.ui.theme.tertiaryContainerLightMediumContrast
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
    Perfil()
}

@Composable
fun Perfil() {

    var fullName by remember {
        mutableStateOf("José Pereira das Neves")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 30.dp,
                start = 16.dp,
                end = 16.dp
            ),
    ) {
        DrawCircle()
        Text(
            modifier = Modifier
                .fillMaxWidth().height(80.dp),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 36.sp,
            color = Color.Black,
            text = "Maria Antonieta"
        )
        InformationButton(label = "Informações Pessoais")
        AddressButton(label = "Endereço")
        ProfessionalButton(label = "Profissional")
        ExitButton(label = "Sair")
    }
}

@Composable
fun InformationButton(
    label: String,
    onclick: (Any) -> Any = {}
) {
    var isLoading by remember {
        mutableStateOf(false)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { },
        verticalAlignment = Alignment.CenterVertically,
        )  {
        Icon(
            imageVector = Icons.Filled.AccountCircle,
            contentDescription = "Icone de Usuário",
            modifier = Modifier
                .size(30.dp),
            tint = tertiaryLight
        )

       Column(
           modifier = Modifier
               .fillMaxWidth()
               .padding(16.dp)
           ) {
           Row (
               modifier = Modifier
                   .fillMaxWidth(),
               verticalAlignment = Alignment.CenterVertically,
               horizontalArrangement = Arrangement.SpaceBetween,
           ) {
               Text(
                   text = label,
                   fontSize = 16.sp,
                   color = tertiaryLight
               )
               Icon(
                   imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                   contentDescription = "Seta para a direita",
                   modifier = Modifier
                       .size(30.dp),
                   tint = tertiaryLight
               )
           }
           Divisor()
       }
    }
}

@Composable
fun AddressButton(
    label: String,
    onclick: (Any) -> Any = {}
) {
    var isLoading by remember {
        mutableStateOf(false)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { },
        verticalAlignment = Alignment.CenterVertically,
    )  {
        Icon(
            imageVector = Icons.Filled.Home,
            contentDescription = "Icone de Casa",
            modifier = Modifier
                .size(30.dp),
            tint = tertiaryLight
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = label,
                    fontSize = 16.sp,
                    color = tertiaryLight
                )
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "Seta para a direita",
                    modifier = Modifier
                        .size(30.dp),
                    tint = tertiaryLight
                )
            }
            Divisor()
        }
    }
}

@Composable
fun ProfessionalButton(
    label: String,
    onclick: (Any) -> Any = {}
) {
    var isLoading by remember {
        mutableStateOf(false)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(1.dp)
            .clickable { },
        verticalAlignment = Alignment.CenterVertically,
    )  {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.List,
            contentDescription = "Seta para a direita",
            modifier = Modifier
                .size(30.dp),
            tint = tertiaryLight
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = label,
                    fontSize = 16.sp,
                    color = tertiaryLight
                )
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "Seta para a direita",
                    modifier = Modifier
                        .size(30.dp),
                    tint = tertiaryLight
                )
            }
            Divisor()
        }
    }
}

@Composable
fun ExitButton(
    label: String,
    onclick: (Any) -> Any = {}
) {
    var isLoading by remember {
        mutableStateOf(false)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(1.dp)
            .clickable { },
        verticalAlignment = Alignment.CenterVertically,
    )  {
        Icon(
            imageVector = Icons.Filled.ExitToApp,
            contentDescription = "Icone de Usuário",
            modifier = Modifier
                .size(30.dp),
            tint = tertiaryLight
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = label,
                    fontSize = 16.sp,
                    color = tertiaryLight
                )
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "Seta para a direita",
                    modifier = Modifier
                        .size(30.dp),
                    tint = tertiaryLight
                )
            }
            Divisor()
        }
    }
}

@Composable
fun Divisor(){
    HorizontalDivider(
        color = tertiaryLight,
        thickness = 1.2.dp,
        modifier = Modifier
            .fillMaxWidth()
    )
}

@Composable
fun DrawCircle() {
    Canvas(modifier = Modifier
        .fillMaxWidth()
        .height(200.dp)) {
        drawCircle(
            color = tertiaryLight,
            radius = 210f,
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