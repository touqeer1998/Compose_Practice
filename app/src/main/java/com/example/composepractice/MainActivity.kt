package com.example.composepractice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MessageCard(Message("Touqeer", "Assalam o Alaikum"))
        }
    }
}

data class Message(val name: String, val text: String)

@Composable
fun MessageCard(msg: Message) {
    Row(modifier = Modifier.padding(10.dp, 20.dp)) {
        Image(
            painter = painterResource(id = R.drawable.touqeer),
            contentDescription = "sender Image",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column {
            Text(text = msg.name)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = msg.text)
        }
    }
}

@Preview
@Composable
fun ShowGreetings() {
    MessageCard(Message("Touqeer", "Assalam o Alaikum"))
}