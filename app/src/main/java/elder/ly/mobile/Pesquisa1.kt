package elder.ly.mobile

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Search
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
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import elder.ly.mobile.ui.theme.MobileTheme
import elder.ly.mobile.ui.theme.onPrimaryLightHighContrast
import elder.ly.mobile.ui.theme.outlineLight
import elder.ly.mobile.ui.theme.primaryContainerLight
import elder.ly.mobile.ui.theme.tertiaryContainerLight
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.clock.ClockDialog
import com.maxkeppeler.sheets.clock.models.ClockConfig
import com.maxkeppeler.sheets.clock.models.ClockSelection
import elder.ly.mobile.ui.theme.tertiaryLight


@Composable
fun Pesquisa1() {

    var especialidades by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 44.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append("O Que Precisa?")
                }
            },
            fontSize = 36.sp,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        Text(
            text = buildAnnotatedString {
                append("Pesquise o cuidador ideal para você")
            },
            fontSize = 18.sp,
            color = tertiaryLight
        )

        ComposeDataTimePickerTheme()


        DefaultDropdownMenu(
            label = "Especialidades",
            placeholder = "Selecione Especialidade(s)",
            options = listOf("Fraldas", "Bingo", "Medicação"),
            value = especialidades,
            changeValue = { newEspecialidades : String ->
                especialidades = newEspecialidades
            }
        )

        Spacer(modifier = Modifier.weight(1f))

        NextButton(label = "Avançar")
        NavBar()
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
            containerColor = primaryContainerLight,
            contentColor = onPrimaryLightHighContrast
        ),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
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
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Seta para a direita",
                    modifier = Modifier
                        .size(36.dp)
                )
            }
        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComposeDataTimePickerTheme() {
    val calendarState = rememberSheetState()
    val clockState = rememberSheetState()

    // Cores e estilo
    val dialogBackgroundColor = Color.White
    val buttonColor = primaryContainerLight
    val buttonContentColor = Color.White

    Column(
        modifier = Modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Data de Início"
        )
        TextButton(
            onClick = { calendarState.show() },
            modifier = Modifier
                .padding(vertical = 8.dp)
                .border(width = 1.dp, color = tertiaryLight, shape = RoundedCornerShape(8.dp))
                .width(320.dp)
                .background(Color.Transparent),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = tertiaryContainerLight
            ),
            shape = RoundedCornerShape(10.dp),

        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Selecionar Data")
                Icon(
                    imageVector = Icons.Filled.DateRange,
                    contentDescription = "Ícone de Calendário",
                    tint = tertiaryContainerLight
                )
            }
        }

        Text(
            text = "Data de Fim",
        )
        TextButton(
            onClick = { clockState.show() },
            modifier = Modifier
                .padding(vertical = 8.dp)
                .border(width = 1.dp, color = tertiaryLight, shape = RoundedCornerShape(8.dp))
                .width(320.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = tertiaryContainerLight
            ),
            shape = RoundedCornerShape(10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Selecionar Data")
                Icon(
                    imageVector = Icons.Filled.DateRange,
                    contentDescription = "Ícone de Calendário",
                    tint = tertiaryContainerLight
                )
            }
        }

        CalendarDialog(
            state = calendarState,
            selection = CalendarSelection.Date { date ->
                Log.d("Selected Date", "$date")
            },
        )

        ClockDialog(
            state = clockState,
            config = ClockConfig(
                is24HourFormat = true
            ),
            selection = ClockSelection.HoursMinutes { hours, minutes ->
                Log.d("Selected Time", "$hours:$minutes")
            },
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MobileTheme {
        Pesquisa1()
    }
}