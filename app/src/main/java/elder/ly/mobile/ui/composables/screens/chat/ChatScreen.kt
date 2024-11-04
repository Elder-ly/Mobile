package elder.ly.mobile.ui.composables.screens.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.gson.Gson
import elder.ly.mobile.domain.service.UserConversationOutput
import elder.ly.mobile.ui.viewmodel.ChatViewModel
import elder.ly.mobile.utils.DateTimeUtils
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun ChatScreen(navController: NavController) {
    val viewModel = koinViewModel<ChatViewModel>()

    val composableScope = rememberCoroutineScope()

    val gson = Gson()
    val savedStateHandle = navController.previousBackStackEntry?.savedStateHandle
    val conversationJson = savedStateHandle?.get<String>("conversationJson")

    val conversation = conversationJson?.let { gson.fromJson(it, UserConversationOutput::class.java) }

    val context = LocalContext.current

    val listState = rememberLazyListState()

    if (conversation != null) {
        viewModel.recipientId = conversation.id
    }

    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm");

    LaunchedEffect(key1 = viewModel.recipientId) {
//        getUser(context).collect {
//            senderId = it.id
//        }
        viewModel.isLoading = true
        viewModel.senderId = 3
        while (true) {
            viewModel.loadMessages()
            kotlinx.coroutines.delay(2000)
        }
    }

    LaunchedEffect(Unit) {
        snapshotFlow { viewModel.messages.size }
            .collect { size ->
                if (size > 0) {
                    listState.animateScrollToItem(size - 1)
                }
            }
    }

    Scaffold { paddingValues ->
        Box(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                if (conversation != null) {
                    ChatTopBar(navController, conversation)
                }

                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = 1.dp,
                    color = Color.Gray
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    if (viewModel.isLoading) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            CircularProgressIndicator(color = Color.Gray)
                        }
                    } else {
                        LazyColumn(
                            state = listState,
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth(),
                            verticalArrangement = Arrangement.Bottom
                        ) {
                            items(viewModel.messages) {
                                ChatMessage(
                                    message = it.conteudo,
                                    dateTime = DateTimeUtils.parseToLocalDateTime(it.dataHora),
                                    isSender = it.remetente.id == viewModel.senderId
                                )
                            }
                        }
                        MessageInputField(onclick = { message -> viewModel.sendMessages(message) })
                    }
                }
            }

            val isScrolledToEnd by remember {
                derivedStateOf {
                    listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index == listState.layoutInfo.totalItemsCount - 1
                }
            }

            if (!isScrolledToEnd) {
                FloatingActionButton(
                    onClick = {
                        composableScope.launch {
                            listState.animateScrollToItem(viewModel.messages.size - 1)
                        }
                    },
                    containerColor = Color(0xFF2196F3),
                    modifier = Modifier
                        .align(BottomEnd)
                        .padding(16.dp)
                        .padding(bottom = 72.dp)
                ) {
                    Icon(
                        painter = painterResource(id = android.R.drawable.arrow_down_float),
                        contentDescription = "Rolar para baixo",
                        tint = Color.White
                    )
                }
            }
        }
    }
}

//@Composable
//fun MessageInputField(onMessageSent: (String) -> Unit) {
//    var message by remember { mutableStateOf("") }
//
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(8.dp),
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Button(
//            onClick = {},
//            modifier = Modifier.padding(start = 8.dp)
//        ) {
//            Icon(
//                painter = painterResource(id = android.R.drawable.ic_input_add),
//                contentDescription = "Enviar",
//                tint = Color.White
//            )
//        }
//
//        TextField(
//            value = message,
//            onValueChange = { message = it },
//            modifier = Modifier.weight(1f),
//            placeholder = { Text("Digite sua mensagem") }
//        )
//
//        Button(
//            onClick = {
//                if (message.isNotBlank()) {
//                    onMessageSent(message)
//                    message = ""
//                }
//            },
//            modifier = Modifier.padding(start = 8.dp)
//        ) {
//            Text("Enviar")
//        }
//    }
//}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessageInputField(onclick : (String) -> Unit) {
    var text by remember { mutableStateOf("")}

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .background(Color(0xFFDFDFDF), CircleShape),
        verticalAlignment = Alignment.CenterVertically,
    ) {
//        IconButton(
//            onClick = { /* Ação do botão */ },
//            modifier = Modifier
//                .padding(start = 10.dp)
//                .size(48.dp)
//                .clip(CircleShape)
//                .background(Color(0xFF2196F3))
//        ) {
//            Icon(
//                painter = painterResource(id = android.R.drawable.ic_input_add),
//                contentDescription = "Enviar",
//                tint = Color.White
//            )
//        }

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
            onClick = {
                if (text.isNotBlank()) {
                    onclick(text)
                    text = ""
                }
            },
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


@Composable
fun ChatMessage(
    modifier: Modifier = Modifier,
    message: String,
    dateTime: LocalDateTime,
    isSender: Boolean
){
    val backgroundColor = if (isSender) Color(0xFFD1F5FF) else Color(0xFFECECEC)
    val textColor = if (isSender) Color.Black else Color.DarkGray
    val alignment = if (isSender) Alignment.End else Alignment.Start

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        horizontalArrangement = if (isSender) Arrangement.End else Arrangement.Start
    ) {
        Column(
            horizontalAlignment = alignment
        ) {
            Box(
                modifier = Modifier
                    .background(
                        color = backgroundColor,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(12.dp)
                    .widthIn(max = 240.dp)
            ) {
                Text(
                    text = message,
                    color = textColor,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = if (isSender) TextAlign.End else TextAlign.Start
                )
            }
            Text(
                text = DateTimeUtils.formatLocalDateTime(dateTime),
                color = Color.Gray,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = if (isSender) TextAlign.End else TextAlign.Start
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ChatScreenPreview(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    ChatScreen(navController = navController)
}

