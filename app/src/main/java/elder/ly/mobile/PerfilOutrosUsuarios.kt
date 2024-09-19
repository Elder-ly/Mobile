package elder.ly.mobile

import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import elder.ly.mobile.ui.theme.onPrimaryLightHighContrast
import elder.ly.mobile.ui.theme.primaryContainerLight
import elder.ly.mobile.ui.theme.primaryLight

@Composable
fun PerfilOutrosUsuarios(
    modifier: Modifier = Modifier
        .fillMaxSize(),
) {
    val lista = List(3) { "Medicação" }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally// Ajusta o conteúdo para ocupar o espaço disponível
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(top = 30.dp, start = 16.dp, end = 16.dp),
        ) {
            DrawCircle()
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 36.sp,
                color = Color.Black,
                text = "Maria Antonieta"
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                color = primaryLight,
                text = "Vila Matilde"
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier
                    .padding(vertical = 8.dp)
            ) {
                itemsIndexed(lista) { index, itemDaLista ->
                    Text(
                        fontSize = 12.sp,
                        text = "$itemDaLista",
                        modifier = Modifier
                            .padding(14.dp)
                            .background(primaryContainerLight, shape = RoundedCornerShape(8.dp)),
                        textAlign = TextAlign.Center
                    )
                }
            }
            Text(
                fontSize = 12.sp,
                text = "Sou uma pessoa dedicada e experiente, " +
                        "comprometida em proporcionar cuidados compassivos " +
                        "e de qualidade para seus entes queridos. Com habilidades " +
                        "abrangentes em assistência diária, incluindo higiene pessoal," +
                        "alimentação e administração de medicamentos, garanto um ambiente " +
                        "seguro e acolhedor, promovendo o bem-estar físico e emocional dos " +
                        "idosos sob meus cuidados. Estou pronta para oferecer suporte " +
                        "personalizado e atenção dedicada, adaptando-me às necessidades " +
                        "individuais de cada cliente para garantir o melhor cuidado " +
                        "possível.\n" +
                        "Estou aqui para proporcionar tranquilidade e conforto para você " +
                        "e sua família."
            )
        }

        Text(
            modifier = Modifier.fillMaxWidth(),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            text = "R$150/hora"
        )

        NextButton(label = "Conversar")

        Spacer(modifier = Modifier.height(16.dp))
        NavBar()
    }
}

@Composable
fun NextButton(
    label: String,
    onclick: (Any) -> Any = {}
) {
    var isLoading by remember { mutableStateOf(false) }

    Button(
        onClick = {
            isLoading = true
            onclick({})
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = primaryContainerLight,
            contentColor = onPrimaryLightHighContrast
        ),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(bottom = 16.dp)
            .width(320.dp)
            .height(56.dp)
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                color = onPrimaryLightHighContrast,
                modifier = Modifier.size(24.dp)
            )
        } else {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = label,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "Seta para a direita",
                    modifier = Modifier
                        .size(36.dp)
                )
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun PerfilOutrosUsuariosPreview(
    modifier: Modifier = Modifier) {
    PerfilOutrosUsuarios()
}