package elder.ly.mobile.ui.screens.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun ChatScreen(navController: NavController) {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            ChatTopBar()
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                reverseLayout = true
            ) {

            }
            MessageInputField()
        }
    }
}
@Composable
fun MessageInputField(onMessageSent: (String) -> Unit) {
    var message by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = {},
            modifier = Modifier.padding(start = 8.dp)
        ) {
            Icon(
                painter = painterResource(id = android.R.drawable.ic_input_add),
                contentDescription = "Enviar",
                tint = Color.White
            )
        }

        TextField(
            value = message,
            onValueChange = { message = it },
            modifier = Modifier.weight(1f),
            placeholder = { Text("Digite sua mensagem") }
        )

        Button(
            onClick = {
                if (message.isNotBlank()) {
                    onMessageSent(message)
                    message = ""
                }
            },
            modifier = Modifier.padding(start = 8.dp)
        ) {
            Text("Enviar")
        }
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
        IconButton(
            onClick = { /* Ação do botão */ },
            modifier = Modifier
                .padding(start = 10.dp)
                .size(48.dp)
                .clip(CircleShape)
                .background(Color(0xFF2196F3))
        ) {
            Icon(
                painter = painterResource(id = android.R.drawable.ic_input_add),
                contentDescription = "Enviar",
                tint = Color.White
            )
        }

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
fun ChatScreenPreview(modifier: Modifier = Modifier) {
    ChatScreen()
}

