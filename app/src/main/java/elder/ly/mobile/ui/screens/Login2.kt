package elder.ly.mobile.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import elder.ly.mobile.CustomMaskTranformation
import elder.ly.mobile.ui.theme.onPrimaryLightHighContrast
import elder.ly.mobile.ui.theme.outlineLight
import elder.ly.mobile.ui.theme.primaryContainerLight
import elder.ly.mobile.ui.theme.tertiaryContainerLight

@Composable
fun Login2() {
    var fullName by remember {
        mutableStateOf("")
    }

    var email by remember {
        mutableStateOf("")
    }

    var document by remember {
        mutableStateOf("")
    }

    var birthDate by remember {
        mutableStateOf("")
    }

    var gender by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 44.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append("Cadastro")
                }
            },
            fontSize = 36.sp,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        DefaultTextInput(
            label = "Nome Completo",
            value = fullName,
            changeValue = { newFullName : String ->
                fullName = newFullName
            }
        )

        DefaultTextInput(
            label = "Email",
            placeholder = "seuemail@gmail.com",
            keyboardType = KeyboardType.Email,
            value = email,
            changeValue = { newEmail : String ->
                email = newEmail
            }
        )

        DefaultTextInput(
            label = "Documento (CPF/CNPJ)",
            placeholder = "12345678910",
            keyboardType = KeyboardType.Number,
            maxChar = 11,
            value = document,
            changeValue = { newDocument : String ->
                document = newDocument
            }
        )

        DefaultTextInput(
            label = "Data de Nascimento",
            placeholder = "23/06/1991",
            keyboardType = KeyboardType.Number,
            mask = CustomMaskTranformation(mask = "##/##/####"),
            maxChar = 8,
            value = birthDate,
            changeValue = { newBirthDate : String ->
                birthDate = newBirthDate
            },
        )

        DefaultDropdownMenu(
            label = "Gênero",
            placeholder = "Selecione um Gênero",
            options = listOf("Masculino", "Feminino", "Prefiro não Informar"),
            value = gender,
            changeValue = { newGender : String ->
                gender = newGender
            }
        )

        Spacer(modifier = Modifier.weight(1f))

        NextButton(label = "Avançar")
    }
}

@Composable
fun DefaultTextInput(
    label: String,
    placeholder: String = label,
    value: String,
    changeValue: (String) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Text,
    mask: VisualTransformation = VisualTransformation.None,
    maxChar: Int = 255
) {
    val unfocusedBorderColor = outlineLight
    val focusedBorderColor = primaryContainerLight

    Column(
        modifier = Modifier
            .padding(vertical = 8.dp)
    ) {
        Text(text = label)

        OutlinedTextField(
            modifier = Modifier
                .width(320.dp),
            placeholder = { Text(text = placeholder, color = tertiaryContainerLight) },
            value = value,
            onValueChange = { newValue: String ->
                if(newValue.length <= maxChar) changeValue(newValue)
            },
            visualTransformation = mask,
            maxLines = 1,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            shape = RoundedCornerShape(10.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = unfocusedBorderColor, // Usando a cor animada
                focusedBorderColor = focusedBorderColor,   // Usando a cor animada
                cursorColor = focusedBorderColor
            )
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultDropdownMenu(
    label: String,
    placeholder: String = label,
    options: List<String>,
    value: String,
    changeValue: (String) -> Unit
) {
    val unfocusedBorderColor = outlineLight
    val focusedBorderColor = primaryContainerLight

    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        Text(text = label)

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            OutlinedTextField(
                placeholder = { Text(text = placeholder, color = tertiaryContainerLight) },
                value = selectedOption,
                onValueChange = {},
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = unfocusedBorderColor, // Usando a cor animada
                    focusedBorderColor = focusedBorderColor,   // Usando a cor animada
                    cursorColor = focusedBorderColor
                ),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .menuAnchor()
                    .width(320.dp)
            )

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .width(320.dp)
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            selectedOption = option
                            expanded = false
                            changeValue(option)
                        }
                    )
                }
            }
        }
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
            containerColor = primaryContainerLight, // Cor de fundo azul
            contentColor = onPrimaryLightHighContrast // Cor do texto e ícone
        ),
        shape = RoundedCornerShape(8.dp), // Borda arredondada
        modifier = Modifier
            .width(320.dp)
            .height(56.dp)
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                color = onPrimaryLightHighContrast,
                modifier = Modifier.size(24.dp) // Tamanho do spinner
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