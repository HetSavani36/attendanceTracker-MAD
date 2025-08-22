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
import com.example.myapplication.DataClasses.Count
import com.example.myapplication.DataClasses.TimeTableItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimeTableCard(timeTableItem: TimeTableItem,timeTableViewModel: TimeTableViewModel) {

    var showDialog by remember { mutableStateOf(false) }

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
                IconButton(
                    onClick = {
                        showDialog=true
                    }
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
                timeTableItem.lecture?.subjectName.toString(),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.size(25.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(timeTableItem.lecture?.facultyFullName.toString(), fontWeight = FontWeight.Medium ,fontSize = 17.sp)
                Row {
                    Text("Present: ", fontSize = 16.sp)
                    Text(timeTableItem.attendanceCount.total.toString(), fontSize = 16.sp, color = Color.Blue)
                }
            }
        }
    }
    if(showDialog){
        DailogUi({ showDialog = false },timeTableItem,timeTableViewModel)
    }
}

@Composable
fun TimeTableCard1(timeTableItem: TimeTableItem, batch: String,timeTableViewModel: TimeTableViewModel) {
    var showDialog by remember { mutableStateOf(false) }

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

                Text("Batch: " + batch, fontWeight = FontWeight.SemiBold, fontSize = 17.sp)

                IconButton(
                    onClick = {showDialog=true}
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
            if (batch == "A") Text(
                timeTableItem.lab?.A?.subjectName.toString(),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            if (batch == "B") Text(
                timeTableItem.lab?.B?.subjectName.toString(),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            if (batch == "C") Text(
                timeTableItem.lab?.C?.subjectName.toString(),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            if (batch == "D") Text(
                timeTableItem.lab?.D?.subjectName.toString(),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.size(25.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                if (batch == "A") Text(timeTableItem.lab?.A?.facultyFullName.toString(), fontSize = 17.sp, fontWeight = FontWeight.Medium)
                if (batch == "B") Text(timeTableItem.lab?.B?.facultyFullName.toString(), fontSize = 17.sp, fontWeight = FontWeight.Medium)
                if (batch == "C") Text(timeTableItem.lab?.C?.facultyFullName.toString(), fontSize = 17.sp, fontWeight = FontWeight.Medium)
                if (batch == "D") Text(timeTableItem.lab?.D?.facultyFullName.toString(), fontSize = 17.sp, fontWeight = FontWeight.Medium)

//                Text("Batch: " + batch, fontSize = 17.sp)
                Row {
                    Text("Present: ", fontSize = 16.sp)
                    val count = when (batch) {
                        "A" -> timeTableItem.attendanceCount.A
                        "B" -> timeTableItem.attendanceCount.B
                        "C" -> timeTableItem.attendanceCount.C
                        "D" -> timeTableItem.attendanceCount.D
                        else -> 0
                    }
                    Text(count.toString(), fontSize = 16.sp, color = Color.Blue)
                }
            }
        }
    }
    if(showDialog){
        DailogUi1({ showDialog = false },timeTableItem,timeTableViewModel,batch)
    }
}

//
@Composable
fun DailogUi(
    onDismissalRequest: () -> Unit,
    timeTableItem: TimeTableItem,
    timeTableViewModel: TimeTableViewModel
) {
    var count by remember { mutableStateOf("0") }
    AlertDialog(
        onDismissRequest = { onDismissalRequest() },
        title = {
            Text(getSubjectName(timeTableItem.lecture?.subjectName))
        },
        text = {
            OutlinedTextField(
                value = count,
                onValueChange = {count=it},
                label = { Text("Enter Count") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        },
        confirmButton = {
            Button(
                onClick = {
                    Log.d("id",timeTableItem._id)
                    timeTableViewModel.mark(timeTableItem.`class`,timeTableItem._id, Count(count = count.toInt()))
//                    timeTableViewModel.mark(timeTableItem.`class`,timeTableItem.slot, Count(count.toInt()))
                    onDismissalRequest()
                }
            ) {
                Text("Save")
            }
        },
        dismissButton = {
            Button(
                onClick = {onDismissalRequest()}
            ) {
                Text("Cancel")
            }
        }
    )
}



@Composable
fun DailogUi1(
    onDismissalRequest: () -> Unit,
    timeTableItem: TimeTableItem,
    timeTableViewModel: TimeTableViewModel,
    batch: String
) {
    var count by remember { mutableStateOf("0") }
    AlertDialog(
        onDismissRequest = { onDismissalRequest() },
        title = {
            if(batch=="A") Text(getSubjectName(timeTableItem.lab?.A?.subjectName))
            if(batch=="B") Text(getSubjectName(timeTableItem.lab?.B?.subjectName))
            if(batch=="C") Text(getSubjectName(timeTableItem.lab?.C?.subjectName))
            if(batch=="D") Text(getSubjectName(timeTableItem.lab?.D?.subjectName))
        },
        text = {
            OutlinedTextField(
                value = count,
                onValueChange = {count=it},
                label = { Text("Enter Count") },
                singleLine = true
            )
        },
        confirmButton = {
            Button(
                onClick = {
                    if(batch=="A") timeTableViewModel.mark(timeTableItem.`class`,timeTableItem._id, Count(countA = count.toInt()),batch)
                    if(batch=="B") timeTableViewModel.mark(timeTableItem.`class`,timeTableItem._id, Count(countB = count.toInt()),batch)
                    if(batch=="C") timeTableViewModel.mark(timeTableItem.`class`,timeTableItem._id, Count(countC = count.toInt()),batch)
                    if(batch=="D") timeTableViewModel.mark(timeTableItem.`class`,timeTableItem._id, Count(countD = count.toInt()),batch)
//                    timeTableViewModel.mark(timeTableItem.`class`,timeTableItem.slot, Count(count.toInt()))
                    onDismissalRequest()
                }
            ) {
                Text("Save")
            }
            // .add(date,class,index,countBatchwise)
        },
        dismissButton = {
            Button(
                onClick = {onDismissalRequest()}
            ) {
                Text("Cancel")
            }
        }
    )
}

fun getSubjectName(code: String?): String {
    if (code == "CE361") return "Operating System"
    if (code == "CE349") return "Theory of Computation"
    if (code == "CE362") return "Machine Learning"
    if (code == "CE363") return "Project-III"
    if (code == "CE384") return "Advanced Web"
    if (code == "HSS") return "Communication and Skills"
    if (code == "CE385") return "Mobile App"
    if (code == "CE387") return ".NET Programming"
    if (code == "CE390") return "Competitive Programming"
    if (code == "CE389") return "Research"
    return ""
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