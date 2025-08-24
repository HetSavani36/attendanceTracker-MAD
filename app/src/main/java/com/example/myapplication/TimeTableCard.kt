package com.example.myapplication

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.DataClasses.TimetableItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimeTableCard(
    timeTableItem: TimetableItem,
    timeTableViewModel: TimeTableViewModel
) {
    var showDialog by remember { mutableStateOf(false) }

    // Lifted state for attendance count
    var attendanceCount by remember { mutableStateOf(timeTableItem.attendanceCount) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.elevatedCardElevation(1.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 15.dp)
                .padding(bottom = 15.dp),
            verticalArrangement = Arrangement.spacedBy(-8.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(getTime(timeTableItem.slot), fontSize = 17.sp, fontWeight = FontWeight.SemiBold)
                if (timeTableItem.batch != "") Text(
                    "Batch: ${timeTableItem.batch}",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 17.sp
                )
                IconButton(
                    onClick = { showDialog = true }
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = null,
                        tint = Color.Blue,
                        modifier = Modifier.size(17.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.size(10.dp))

            Text(
                timeTableItem.subjectName,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.size(25.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(timeTableItem.facultyFullName, fontWeight = FontWeight.Medium, fontSize = 17.sp)
                Row {
                    Text("Present: ", fontSize = 16.sp)
                    Text(attendanceCount.toString(), fontSize = 16.sp, color = Color.Blue)
                }
            }
        }
    }

    if (showDialog) {
        DialogUi(
            onDismissalRequest = { showDialog = false },
            initialCount = attendanceCount,
            onSave = { newCount ->
                attendanceCount = newCount   // commit new value
                timeTableViewModel.mark(timeTableItem._id, newCount.toString())
            },
            timeTableItem = timeTableItem
        )
    }
}



@Composable
fun DialogUi(
    onDismissalRequest: () -> Unit,
    initialCount: Int,
    onSave: (Int) -> Unit,
    timeTableItem: TimetableItem
) {
    var tempCount by remember { mutableStateOf(initialCount.toString()) }
//    var countText by remember { mutableStateOf(attendanceCount.toString()) }

    AlertDialog(
        onDismissRequest = { onDismissalRequest() },
        title = { Text(timeTableItem.subjectName) },
        text = {
            OutlinedTextField(
                value = tempCount,
                onValueChange = { tempCount = it },
                label = { Text("Enter Count") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        },
        confirmButton = {
            Button(onClick = {
                val newCount = tempCount.toIntOrNull() ?: 0
                onSave(newCount)     // commit change to parent
                onDismissalRequest()
            }) {
                Text("Save")
            }
        },
        dismissButton = {
            Button(onClick = { onDismissalRequest() }) {
                Text("Cancel")
            }
        }
    )
}





fun getTime(id: Number): String {
    val idx = id.toString()
    if (id == 1) return "09:10 AM - 10:10 AM"
    if (id == 2) return "10:10 AM - 11:10 AM"
    if (id == 3) return "12:10 PM - 01:10 PM"
    if (id == 4) return "01:10 PM - 02:10 PM"
    if (id == 5) return "02:20 PM - 03:20 PM"
    if (id == 6) return "03:20 PM - 04:20 PM"

    if (idx[0] == '1') return "09:10 AM - 11:10 AM"
    if (idx[0] == '3') return "12:10 PM - 02:10 PM"
    if (idx[0] == '5') return "02:20 PM - 04:20 PM"

    return ""
}