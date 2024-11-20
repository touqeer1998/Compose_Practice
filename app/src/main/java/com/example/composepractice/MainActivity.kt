package com.example.composepractice

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composepractice.ui.theme.ComposePracticeTheme
import kotlin.math.pow
import kotlin.math.roundToLong

class MainActivity : ComponentActivity() {
    val messages = listOf(
        Message("Carl", "Hello"),
        Message("Sandy", "Hi"),
        Message("Carl", "Whats up"),
        Message("Sandy", "Iam fine, What about you?"),
        Message("Carl", "I am fine too"),
        Message("Sandy", "Great"),
        Message("Sandy", "Great"),
        Message("Sandy", "Great"),
        Message("Sandy", "Great"),
        Message("Sandy", "Great"),
        Message("Sandy", "Great"),
    )

    @OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var progress by remember {
                mutableStateOf(0f)
            }
            Scaffold(topBar = {
                TopAppBar(
                    title = { Text(text = "Home") },
                    colors = TopAppBarDefaults.mediumTopAppBarColors()
                )
            }, bottomBar = {
                BottomAppBar(containerColor = colorResource(id = R.color.teal_700)) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_person),
                        contentDescription = null
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_person),
                        contentDescription = null
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_person),
                        contentDescription = null
                    )
                }
            }, floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        Toast.makeText(
                            this, "Clicking of FAB", Toast.LENGTH_SHORT
                        ).show()
                    }, containerColor = colorResource(id = R.color.teal_700)
                ) {

                }
            }) {
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(150.dp),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            top = it.calculateTopPadding(), bottom = it.calculateBottomPadding()
                        )
                ) {
//                    ShowLinearProgress(progress = progress)
//                    IncrementProgress(progress = progress) {
//                        if (progress < 1f) {
//                            progress += 0.1f
//                        } else {
//                            progress = 0f
//                        }
//                    }
//                    ProgressValue(progress = progress)
//                    stickyHeader {
//                        Text(
//                            text = "This is Sticky Header",
//                            fontSize = 18.sp,
//                            fontWeight = FontWeight.Bold
//                        )
//                    }
                    items(messages.size) { index ->
                        MessageCard(msg = messages[index])
                    }
//                    Counter()
//                    CheckBoxSample()
//                    SwitchBoxSample()
//                    RadioButtonSample()
//                    ProgressBarsSample()
                }
            }
        }
    }
}

@Composable
fun ShowLinearProgress(progress: Float) {
    LinearProgressIndicator(
        progress = progress, modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    )
}

@Composable
fun IncrementProgress(progress: Float, onClick: () -> Unit) {
    Button(onClick = { onClick() }) {
        ButtonText(progress = progress)
    }
}

@Composable
fun ButtonText(progress: Float) {
    Text(text = if (progress == 1f) "Reset" else "Increment")
}

@Composable
fun ProgressValue(progress: Float) {
    Text(text = "Current Progress  is ${(progress * 100.0).round()} %", fontSize = 20.sp)
}

data class Message(val name: String, val text: String)

@Composable
fun ProgressBarsSample() {
    Column {
        CircularProgressIndicator()
        Spacer(modifier = Modifier.height(10.dp))
        LinearProgressIndicator()
        Spacer(modifier = Modifier.height(10.dp))
    }
}

fun Double.round(upto: Int = 2): Double {
    return (this * 10.0.pow(upto)).roundToLong() / 10.0.pow(upto)
}

@Composable
fun RadioButtonSample() {
    var selectedOption by remember {
        mutableStateOf(1)
    }

    Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
        RadioButtonWithText(text = "Option 1",
            selected = selectedOption == 1,
            onClick = { selectedOption = 1 })
        RadioButtonWithText(text = "Option 2",
            selected = selectedOption == 2,
            onClick = { selectedOption = 2 })
        RadioButtonWithText(text = "Option 3",
            selected = selectedOption == 3,
            onClick = { selectedOption = 3 })
    }
}

@Composable
fun SwitchBoxSample() {
    var checked by remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current
    Switch(checked = checked, onCheckedChange = { isChecked ->
        checked = isChecked
        Toast.makeText(context, "Checked: $isChecked", Toast.LENGTH_SHORT).show()
    })
}

@Composable
fun CheckBoxSample() {
    var checked by remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current
    Checkbox(checked = checked, onCheckedChange = { isChecked ->
        checked = isChecked
        Toast.makeText(context, "Checked: $isChecked", Toast.LENGTH_SHORT).show()
    })
}

@Composable
fun Counter() {
    var count by remember {
        mutableStateOf(0)
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Current Count -> $count", fontSize = 20.sp)
        Button(onClick = { count++ }) {
            Text(text = "Increment", fontSize = 20.sp)
        }
        Button(onClick = { count-- }) {
            Text(text = "Decrement", fontSize = 20.sp)
        }
        Button(onClick = { count = 0 }) {
            Text(text = "Reset", fontSize = 20.sp)
        }
    }
}

@Composable
fun MessageCard(msg: Message, context: Context = LocalContext.current) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .height(80.dp)
            .fillMaxWidth()
            .clickable {
                Toast
                    .makeText(context, msg.text, Toast.LENGTH_SHORT)
                    .show()
            },
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.white), contentColor = colorResource(
                id = R.color.teal_700
            )
        ),
        elevation = CardDefaults.cardElevation(10.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(start = 10.dp)
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_person),
                contentDescription = "sender Image",
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(
                        color = colorResource(id = R.color.teal_700),
                        shape = MaterialTheme.shapes.medium
                    )
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = msg.name, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(4.dp))
                Text(modifier = Modifier.width(intrinsicSize = IntrinsicSize.Max), text = msg.text, fontSize = 14.sp, maxLines = 1)
            }
        }
    }
}

@Composable
fun RadioButtonWithText(text: String, selected: Boolean, onClick: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        RadioButton(selected = selected, onClick = { onClick() })
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = text, modifier = Modifier.clickable { onClick() })
    }
}

@Preview(showBackground = true)
@Composable
fun ShowGreetings() {
    ComposePracticeTheme {
        Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
            Column(modifier = Modifier.fillMaxSize()) {
                MessageCard(Message("Android", "How are you doing?"))
                Counter()
                CheckBoxSample()
                SwitchBoxSample()
                RadioButtonSample()
            }
        }
    }
}