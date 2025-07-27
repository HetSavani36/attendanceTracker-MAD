package com.example.myapplication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                Text(getTime(timeTableItem.slot), fontSize = 17.sp, fontWeight = FontWeight.Medium)
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
            timeTableItem.lecture?.subjectName?.let {
                Text(
                    it,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium
                )
            }
            Spacer(modifier = Modifier.size(25.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                timeTableItem.lecture?.facultyFullName?.let { Text(it, fontSize = 17.sp) }
                Row {
                    Text("Present: ", fontSize = 16.sp)
                    Text(timeTableItem.attendanceCount.total.toString(), fontSize = 16.sp, color = Color.Blue)
                }
            }
        }
    }
    if(showDialog){
//        DailogUi({ showDialog = false },timeTableItem,timeTableViewModel)
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
                Text(getTime(timeTableItem.slot), fontSize = 17.sp, fontWeight = FontWeight.Medium)
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

            if (batch == "A") Text(
                timeTableItem.lab?.A?.subjectName.toString(),
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            )
            if (batch == "B") Text(
                timeTableItem.lab?.B?.subjectName.toString(),
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            )
            if (batch == "C") Text(
                timeTableItem.lab?.C?.subjectName.toString(),
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            )
            if (batch == "D") Text(
                timeTableItem.lab?.D?.subjectName.toString(),
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.size(25.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                if (batch == "A") timeTableItem.lab?.A?.facultyName?.let { Text(it, fontSize = 17.sp) }
                if (batch == "B") timeTableItem.lab?.B?.facultyName?.let { Text(it, fontSize = 17.sp) }
                if (batch == "C") timeTableItem.lab?.C?.facultyName?.let { Text(it, fontSize = 17.sp) }
                if (batch == "D") timeTableItem.lab?.D?.facultyName?.let { Text(it, fontSize = 17.sp) }

                Text("Batch: " + batch, fontSize = 17.sp)
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
//        DailogUi1({showDialog=false},timeTableItem,batch,timeTableViewModel)
    }
}

//
//@Composable
//fun DailogUi(
//    onDismissalRequest: () -> Unit,
//    lecture: TimeTableItem,
//    timeTableViewModel: TimeTableViewModel
//) {
//    var count by remember { mutableStateOf("0") }
//    AlertDialog(
//        onDismissRequest = { onDismissalRequest() },
//        title = {
//            Text(getSubjectName(lecture.batches.A.subject))
//        },
//        text = {
//            OutlinedTextField(
//                value = count,
//                onValueChange = {count=it},
//                label = { Text("Enter Count") },
//                singleLine = true
//            )
//        },
//        confirmButton = {
//            Button(
//                onClick = {
//                    timeTableViewModel.mark(
//                        Mark(
//                            lecture.`class`,
//                            lecture.index,
//                            lecture.isLab,
//                            count=count.toInt()
//                        )
//                    )
//                    onDismissalRequest()
//                }
//            ) {
//                Text("Save")
//            }
//            // .add(date,class,index,count)
//        },
//        dismissButton = {
//            Button(
//                onClick = {onDismissalRequest()}
//            ) {
//                Text("Cancel")
//            }
//        }
//    )
//}
//
//
//
//@Composable
//fun DailogUi1(
//    onDismissalRequest: () -> Unit,
//    lecture: TimeTableItem,
//    batch: String,
//    timeTableViewModel: TimeTableViewModel
//) {
//    var count by remember { mutableStateOf("0") }
//    AlertDialog(
//        onDismissRequest = { onDismissalRequest() },
//        title = {
//            if(batch=="A") Text(getSubjectName(lecture.batches.A.subject))
//            if(batch=="B") Text(getSubjectName(lecture.batches.B.subject))
//            if(batch=="C") Text(getSubjectName(lecture.batches.C.subject))
//            if(batch=="D") Text(getSubjectName(lecture.batches.D.subject))
//        },
//        text = {
//            OutlinedTextField(
//                value = "0",
//                onValueChange = {count=it},
//                label = { Text("Enter Count") },
//                singleLine = true
//            )
//        },
//        confirmButton = {
//            Button(
//                onClick = {}
//            ) {
//                Text("Save")
//            }
//            // .add(date,class,index,countBatchwise)
//        },
//        dismissButton = {
//            Button(
//                onClick = {onDismissalRequest()}
//            ) {
//                Text("Cancel")
//            }
//        }
//    )
//}

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