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
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.IconButton
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.draw.clip


@Composable
fun Pesquisa1() {
    var especialidades by remember { mutableStateOf("") }
    var selectedSpecialties by remember { mutableStateOf<List<String>>(emptyList()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 44.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
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
            color = Color.Gray
        )

        ComposeDataTimePickerTheme()

        DefaultDropdownMenu(
            label = "Especialidades",
            placeholder = "Selecione Especialidade(s)",
            options = listOf("Fraldas", "Bingo", "Medicação"),
            value = especialidades,
            changeValue = { newEspecialidades ->
                especialidades = newEspecialidades
                if (newEspecialidades.isNotEmpty() && !selectedSpecialties.contains(newEspecialidades)) {
                    selectedSpecialties = selectedSpecialties + newEspecialidades
                }
            }
        )

        SpecialtyList(specialties = selectedSpecialties, onRemove = { specialty ->
            selectedSpecialties = selectedSpecialties - specialty
        })

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
    val unfocusedBorderColor = Color.Gray
    val focusedBorderColor = Color.Blue

    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        Text(text = label)

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                placeholder = { Text(text = placeholder, color = Color.Gray) },
                value = selectedOption,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = unfocusedBorderColor,
                    focusedBorderColor = focusedBorderColor
                ),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .menuAnchor()
                    .width(320.dp)
            )

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.width(320.dp)
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            selectedOption = option
                            changeValue(option)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun SpecialtyList(specialties: List<String>, onRemove: (String) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(specialties) { specialty ->
            SpecialtyItem(
                text = specialty,
                onRemove = { onRemove(specialty) }
            )
        }
    }
}

@Composable
fun SpecialtyItem(text: String, onRemove: () -> Unit) {
    Box(
        modifier = Modifier
            .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(8.dp))
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .width(132.dp)
            .height(40.dp)
            .padding(horizontal = 8.dp)
            .clip(RoundedCornerShape(8.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = text,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp),
                color = Color.Black
            )
            IconButton(
                onClick = onRemove,
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Clear,
                    contentDescription = "Delete",
                    tint = Color.Black
                )
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
    
    Column(
        modifier = Modifier
            .padding(top = 16.dp),
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