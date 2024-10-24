package elder.ly.mobile.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import elder.ly.mobile.ChatList
import elder.ly.mobile.Profile
import elder.ly.mobile.R
import elder.ly.mobile.Search
import elder.ly.mobile.ui.theme.tertiaryContainerLight


@Composable
fun TopBar(modifier: Modifier = Modifier, title: String, showBackButton: Boolean = true, navController: NavController) {
    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row (
            modifier = Modifier.align(Alignment.CenterStart)
        ){
            if (showBackButton){
                BackIconButton(onclick = { navController.popBackStack() })
            }
        }

        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append(title)
                }
            },
            fontSize = 36.sp,
            textAlign = TextAlign.Center,
            lineHeight = 40.sp,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}


@Composable
fun BottomBar(modifier: Modifier = Modifier, navController: NavController) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(72.dp)
            .drawBehind {
                drawLine(
                    color = tertiaryContainerLight,
                    start = Offset(0f, 0f),
                    end = Offset(size.width, 0f),
                    strokeWidth = 1.dp.toPx()
                )
            }
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
                .clickable { navController.navigate(Search) },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_pesquisar),
                contentDescription = "Pesquisar",
                modifier = Modifier.size(28.dp)
            )
            Text(
                text = "Pesquisar",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
                .clickable { navController.navigate(ChatList) },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_chat),
                contentDescription = "Chat",
                modifier = Modifier.size(28.dp)
            )
            Text(
                text = "Chat",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
                .clickable { navController.navigate(Profile) },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_perfil),
                contentDescription = "Perfil",
                modifier = Modifier.size(28.dp)
            )
            Text(
                text = "Perfil",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}
