package elder.ly.mobile.ui.screens.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import elder.ly.mobile.R

@Composable
fun WelcomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 44.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(56.dp))

        Image(
            painter = painterResource(id = R.drawable.logo_elder),
            contentDescription = "Logo"
        )

        Spacer(modifier = Modifier.height(86.dp))

        Image(
            painter = painterResource(id = R.drawable.titulo),
            contentDescription = "Titulo"
        )

        Spacer(modifier = Modifier.height(46.dp))

        Text(
            text = """Nossos profissionais são treinados e qualificados
               | para atender o que mais importa para você com
               |  excelência e sabedoria, quando, onde e como quiser!""".trimMargin(),
            fontSize = 15.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 12.dp)
        )

        Spacer(modifier = Modifier.height(46.dp))

        Button(
            modifier = Modifier.padding(horizontal = 52.dp),
            shape = RoundedCornerShape(10.dp),
            border = ButtonDefaults.outlinedButtonBorder,
            onClick = {
                //aguardando função de login com google
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo_google),
                    contentDescription = "Logo Google",
                    modifier = Modifier
                        .size(24.dp)
                        .weight(1f)

                )
                Text(
                    text = "Login com Google",
                    modifier = Modifier
                        .padding(horizontal = 0.dp)
                        .align(Alignment.CenterVertically)
                        .weight(2f),
                    fontSize = 17.sp,

                    color = Color.Black
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    MaterialTheme {
        WelcomeScreen()
    }
}