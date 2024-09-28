package elder.ly.mobile.ui.screens.search

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
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import elder.ly.mobile.ui.theme.MobileTheme
import androidx.compose.ui.text.buildAnnotatedString
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
import androidx.compose.material3.Scaffold
import androidx.compose.ui.draw.clip
import elder.ly.mobile.ui.components.BottomBar
import elder.ly.mobile.ui.components.DefaultDropdownMenu
import elder.ly.mobile.ui.components.SpecialtyList
import elder.ly.mobile.ui.components.TopBar
import elder.ly.mobile.ui.screens.profiledetails.NextButton
import elder.ly.mobile.ui.theme.tertiaryContainerLight


@Composable
fun SearchScreen(showTopBar: Boolean = true, showBottomBar: Boolean = true) {
    var especialidades by remember { mutableStateOf("") }
    var selectedSpecialties by remember { mutableStateOf<List<String>>(emptyList()) }

    Scaffold(
        topBar = {
            if(showTopBar){
                TopBar(
                    title = "O Que Precisa?",
                    showBackButton = false,
                    modifier = Modifier.padding(top = 24.dp)
                )
            }
        },
        bottomBar = {
            if (showBottomBar){
                BottomBar()
            }
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
//        Text(
//            text = buildAnnotatedString {
//                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
//                    append("O Que Precisa?")
//                }
//            },
//            fontSize = 36.sp,
//            modifier = Modifier.padding(bottom = 12.dp)
//        )

                    Text(
                        text = buildAnnotatedString {
                            append("Pesquise o cuidador ideal para você")
                        },
                        fontSize = 18.sp,
                        color = Color.Gray
                    )

                    DataTextButton(modifier = Modifier.padding(top = 16.dp), labelData = "Data Início")
                    DataTextButton(labelData = "Data Fim")
                    HourTextButton(labelHora = "Hora Início")
                    HourTextButton(labelHora = "Hora Fim")


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
//        NavBar()
                }
            }
        }
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DataTextButton(modifier: Modifier = Modifier, labelData: String) {
    val calendarState = rememberSheetState()

    var dateEntered by remember { mutableStateOf("Selecione Data") }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = labelData,
        )
        TextButton(
            onClick = { calendarState.show() },
            modifier = Modifier
                .padding(bottom = 8.dp)
                .width(320.dp)
                .height(56.dp)
                .border(width = 1.dp, color = tertiaryLight, shape = RoundedCornerShape(10.dp)),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = if(dateEntered == "Selecione Data") tertiaryContainerLight else Color.Black
            ),
            shape = RoundedCornerShape(10.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = dateEntered, fontSize = 16.sp)
                Icon(
                    imageVector = Icons.Filled.DateRange,
                    contentDescription = "Ícone de Calendário",
                    tint = Color.Gray
                )
            }
        }
        CalendarDialog(
            state = calendarState,
            selection = CalendarSelection.Date { date ->
                dateEntered = date.toString()
                Log.d("Selected Date", "$date")
            },
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HourTextButton(modifier: Modifier = Modifier, labelHora: String) {
    val clockState = rememberSheetState()

    var hourEntered by remember { mutableStateOf("Selecione Hora") }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = labelHora
        )
        TextButton(
            onClick = { clockState.show() },
            modifier = Modifier
                .width(320.dp)
                .height(56.dp)
                .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(10.dp)),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = if(hourEntered == "Selecione Hora") tertiaryContainerLight else Color.Black
            ),
            shape = RoundedCornerShape(10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = hourEntered, fontSize = 16.sp)
                Icon(
                    imageVector = Icons.Filled.DateRange,
                    contentDescription = "Ícone de Relógio",
                    tint = Color.Gray
                )
            }
        }
        ClockDialog(
            state = clockState,
            config = ClockConfig(
                is24HourFormat = true
            ),
            selection = ClockSelection.HoursMinutes { hours, minutes ->
                hourEntered = String.format("%02d:%02d", hours, minutes) // Formatar a hora
                Log.d("Selected Time", "$hours:$minutes")
            },
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
    MobileTheme {
        SearchScreen()
    }
}