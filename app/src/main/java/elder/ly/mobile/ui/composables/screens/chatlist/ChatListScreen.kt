package elder.ly.mobile.ui.composables.screens.chatlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import elder.ly.mobile.Chat
import elder.ly.mobile.R.drawable.ic_pesquisar
import elder.ly.mobile.ui.components.BottomBar
import elder.ly.mobile.ui.components.Contacts
import elder.ly.mobile.ui.theme.MobileTheme
import elder.ly.mobile.ui.theme.tertiaryContainerLight

@Composable
fun ChatListScreen(showBottomBar: Boolean = true, navController: NavController) {
    var searchText by remember { mutableStateOf("") }

    Scaffold(
        bottomBar = {
            if (showBottomBar){
                BottomBar(
                    //modifier = Modifier.padding(bottom = 44.dp)
                    navController = navController
                )
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
                    .padding(top = 16.dp, bottom = 0.dp, start = 16.dp, end = 16.dp)
            ) {
                SearchChat(
                    searchText = searchText,
                    onSearchTextChange = { newText ->
                        searchText = newText
                    }
                )
            }

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
            ) {
                items(10) {
                    Contacts(navController = navController)
                }
            }
        }
    }
}

@Composable
fun SearchChat(searchText: String, onSearchTextChange: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = tertiaryContainerLight,
                shape = RoundedCornerShape(8.dp)
            ),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Image(
            painter = painterResource(id = ic_pesquisar),
            contentDescription = "Ícone de pesquisa",
            modifier = Modifier
                .size(48.dp)
                .padding(start = 12.dp),
        )

        OutlinedTextField(
            value = searchText,
            onValueChange = { onSearchTextChange(it) },
            placeholder = { Text(text = "Pesquisar", color = Color.Gray) },
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = Color.Transparent,
                cursorColor = Color.Gray
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Search
            ),
            maxLines = 1
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ChatListScreenPreview() {
    MobileTheme {
        val navController = rememberNavController()
        ChatListScreen(navController = navController)
    }
}
