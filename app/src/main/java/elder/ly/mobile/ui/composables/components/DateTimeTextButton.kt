package elder.ly.mobile.ui.composables.components

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.clock.ClockDialog
import com.maxkeppeler.sheets.clock.models.ClockConfig
import com.maxkeppeler.sheets.clock.models.ClockSelection
import elder.ly.mobile.R
import elder.ly.mobile.ui.theme.tertiaryContainerLight
import elder.ly.mobile.ui.theme.tertiaryLight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DataTextButton(modifier: Modifier = Modifier, labelData: String, onDateSelected: (String) -> Unit) {
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
                onDateSelected(dateEntered)
                Log.d("Selected Date", "$date")
            },
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HourTextButton(modifier: Modifier = Modifier, labelHora: String, onHourSelected: (String) -> Unit) {
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
                    painter = painterResource(id = R.drawable.ic_hour),
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
                onHourSelected(hourEntered)
                Log.d("Selected Time", "$hours:$minutes")
            },
        )
    }
}