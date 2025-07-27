package com.example.myapplication

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.pullToRefresh
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.DataClasses.TimeTable
import com.example.myapplication.DataClasses.TimeTableItem
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(timeTableViewModel: TimeTableViewModel) {

    val timeTable by timeTableViewModel.timeTable.collectAsState()
    val loading by timeTableViewModel.isTimeTableLoading.collectAsState()

    var branchExpanded by remember { mutableStateOf(false) }
    var yearExpanded by remember { mutableStateOf(false) }
    var branch by remember { mutableStateOf("branch") }
    var year by remember { mutableStateOf("class") }

    Scaffold(
        topBar = { TopAppBar("Smart Attendance Tracker") }
    ) { it ->
        Column(
            modifier = Modifier
                .padding(it)
                .padding(horizontal = 20.dp)
        ) {
            val date = LocalDate.now().dayOfMonth
            val month = LocalDate.now().month
            val day = LocalDate.now().dayOfWeek.name
            val context = LocalContext.current

            Spacer(modifier = Modifier.size(20.dp))
            Text(
                day.lowercase().capitalize() + ", " + month.toString().lowercase()
                    .capitalize() + " " + date, fontSize = 27.sp, fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.size(20.dp))
            Box(modifier = Modifier) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    OutlinedButton(
                        onClick = { branchExpanded = !branchExpanded },
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .height(50.dp),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(branch)
                            Icon(
                                Icons.Default.KeyboardArrowDown,
                                contentDescription = "More options"
                            )
                        }
                    }

                    OutlinedButton(
                        onClick = {
                            if (branch == "branch") {
                                Toast.makeText(
                                    context,
                                    "Please Select Branch First! ",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else yearExpanded = !yearExpanded
                        },
                        modifier = Modifier
                            .fillMaxWidth(1f)
                            .height(50.dp),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(year)
                            Icon(
                                Icons.Default.KeyboardArrowDown,
                                contentDescription = "More options"
                            )
                        }
                    }
                }
                DropdownMenu(
                    expanded = branchExpanded,
                    onDismissRequest = { branchExpanded = false },
                ) {
                    DropdownMenuItem(
                        text = { Text("CSE") },
                        onClick = {
                            branch = "CSE"
                            branchExpanded = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("CE") },
                        onClick = {
                            branch = "CE"
                            branchExpanded = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("IT") },
                        onClick = {
                            branch = "IT"
                            branchExpanded = false
                        }
                    )
                }

                DropdownMenu(
                    expanded = yearExpanded,
                    onDismissRequest = { yearExpanded = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("1" + branch + "1") },
                        onClick = {
                            year="1" + branch + "1"
                            timeTableViewModel.getTimeTable("1" + branch + "1")
                            year = "1" + branch + "1"
                            yearExpanded = false
                        }
                    )

                    DropdownMenuItem(
                        text = { Text("1" + branch + "2") },
                        onClick = {
                            year="1" + branch + "2"
                            timeTableViewModel.getTimeTable("1" + branch + "2")
                            year = "1" + branch + "2"
                            yearExpanded = false
                        }
                    )

                    DropdownMenuItem(
                        text = { Text("3" + branch + "1") },
                        onClick = {
                            year="3" + branch + "1"
                            timeTableViewModel.getTimeTable("3" + branch + "1")
                            year = "3" + branch + "1"
                            yearExpanded = false
                        }
                    )

                    DropdownMenuItem(
                        text = { Text("3" + branch + "2") },
                        onClick = {
                            year="3" + branch + "2"
                            timeTableViewModel.getTimeTable("3" + branch + "2")
                            year = "3" + branch + "2"
                            yearExpanded = false
                        }
                    )

                    DropdownMenuItem(
                        text = { Text("5" + branch + "1") },
                        onClick = {
                            year="5" + branch + "1"
                            timeTableViewModel.getTimeTable("5" + branch + "1")
                            year = "5" + branch + "1"
                            yearExpanded = false
                        }
                    )

                    DropdownMenuItem(
                        text = { Text("5" + branch + "2") },
                        onClick = {
                            year="5" + branch + "2"
                            timeTableViewModel.getTimeTable("5" + branch + "2")
                            year = "5" + branch + "2"
                            yearExpanded = false
                        }
                    )

                    DropdownMenuItem(
                        text = { Text("7" + branch + "1") },
                        onClick = {
                            year="7" + branch + "1"
                            timeTableViewModel.getTimeTable("7" + branch + "1")
                            year = "7" + branch + "1"
                            yearExpanded = false
                        }
                    )

                    DropdownMenuItem(
                        text = { Text("7" + branch + "2") },
                        onClick = {
                            year="7" + branch + "2"
                            timeTableViewModel.getTimeTable("7" + branch + "2")
                            year = "7" + branch + "2"
                            yearExpanded = false
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.size(15.dp))

            if (loading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(modifier = Modifier.size(30.dp))
                }
            } else {

                val pullRefreshState = rememberPullToRefreshState()

                PullToRefreshBox(
                    isRefreshing = loading,
                    onRefresh = {
                        if (year != "class") {
                            timeTableViewModel.getTimeTable(year)
                        }
                    },
                    state = pullRefreshState,
                    indicator = {
                        Indicator(
                            modifier = Modifier.align(Alignment.TopCenter),
                            isRefreshing = loading,
                            color = Color.Blue,
                            state = pullRefreshState
                        )
                    },
                ) {
                    if (timeTable.isEmpty()) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxSize(0.5f),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("No Time Table Found!!", fontSize = 20.sp, color = Color.Red)
                        }
                    } else {
                        LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            items(timeTable) { timeTableItem ->
                                if (timeTableItem.lecture == null) {
                                    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                                        for (i in 0..3) {
                                            TimeTableCard1(
                                                timeTableItem,
                                                (i + 65).toChar().toString(),
                                                timeTableViewModel
                                            )
                                        }
                                    }
                                } else TimeTableCard(timeTableItem, timeTableViewModel)
                            }
                        }
                    }
                }


            }
            Spacer(Modifier.size(200.dp))
        }
    }
}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun PullToRefreshBasicSample(
//    timeTable: TimeTable,
//    isRefreshing: Boolean,
//    onRefresh: () -> Unit,
//    modifier: Modifier = Modifier,
//    timeTableViewModel: TimeTableViewModel
//) {
//    val state = rememberPullToRefreshState()
//    PullToRefreshBox(
//        isRefreshing = isRefreshing,
//        onRefresh = onRefresh,
//        modifier = modifier,
//        state = state,
//        indicator = {
//            Indicator(
//                modifier = Modifier.align(Alignment.TopCenter),
//                isRefreshing = isRefreshing,
//                containerColor = MaterialTheme.colorScheme.primaryContainer,
//                color = MaterialTheme.colorScheme.onPrimaryContainer,
//                state = state
//            )
//        },
//    ) {
//        LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
//            items(timeTable) { timeTableItem ->
//                if (timeTableItem.lecture==null) {
//                    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
//                        for (i in 0..3) {
//                            TimeTableCard1(timeTableItem, (i + 65).toChar().toString(),timeTableViewModel)
//                        }
//                    }
//                } else TimeTableCard(timeTableItem,timeTableViewModel)
//            }
//        }
//    }
//}