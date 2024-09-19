package elder.ly.mobile

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import elder.ly.mobile.ui.theme.primaryLight

@Composable
fun Chat2(
    modifier: Modifier = Modifier
        .fillMaxSize()) {

    var text by remember {
        mutableStateOf(
            TextFieldValue("")
        )
    }


  Column () {
      Column(
          modifier = Modifier
              .weight(1f)
              .padding(top = 30.dp, start = 16.dp, end = 16.dp)
      ) {
          Row(
              modifier = Modifier
                  .padding(15.dp)
                  .fillMaxWidth()
                  .align(Alignment.CenterHorizontally),
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.SpaceBetween
          ) {
              Icon(
                  imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                  contentDescription = "Seta para a esquerda",
                  modifier = Modifier.size(50.dp),
                  tint = Color.Black
              )
              Row(
                  modifier = Modifier
                      .align(Alignment.CenterVertically),
                  verticalAlignment = Alignment.CenterVertically,
                  horizontalArrangement = Arrangement.spacedBy(35.dp)
              ) {
                  DrawMiniCircle()
                  Column {
                      Text(
                          fontSize = 18.sp,
                          fontWeight = FontWeight.Bold,
                          text = "Maria Antonieta")
                      Text(
                          color = primaryLight,
                          text = "Online")
                  }
              }
              Icon(
                  imageVector = Icons.Filled.Info,
                  contentDescription = "Seta para a esquerda",
                  modifier = Modifier.size(30.dp),
                  tint = Color.Black
              )
          }
      }
      MessageInputField()
  }
}


@Composable
fun DrawMiniCircle() {
    Canvas(modifier = Modifier
        .height(1.dp)) {
        drawCircle(
            color = primaryLight,
            radius = 60f,
        )
    }
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessageInputField() {
    var text by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .background(Color(0xFFDFDFDF), CircleShape),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TextField(
            value = text,
            onValueChange = { newText -> text = newText },
            placeholder = { Text("Envie sua Mensagem", fontSize = 16.sp) },
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp, top = 4.dp, bottom = 4.dp)
                .background(Color.Transparent),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )

        IconButton(
            onClick = { /* Ação do botão */ },
            modifier = Modifier
                .padding(end = 10.dp)
                .size(48.dp)
                .clip(CircleShape)
                .background(Color(0xFF2196F3))
        ) {
            Icon(
                painter = painterResource(id = android.R.drawable.ic_menu_send),
                contentDescription = "Enviar",
                tint = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Chat2Preview(modifier: Modifier = Modifier) {
    Chat2()
}