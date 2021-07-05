package com.merkost.composepractice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.merkost.composepractice.ui.theme.ComposePracticeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePracticeTheme {
                MyApp {
                    MyScreenContent()
                }
            }

//            MyApp { Greeting(name = "Ko") }

//            ComposePracticeTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(color = MaterialTheme.colors.background) {
//                    Greeting("Android")
//                }
//            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp {
        MyScreenContent()
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    MaterialTheme {
        Surface(color = Color.Yellow, modifier = Modifier.padding(5.dp)) {
            content()
        }
    }
}

//@Composable
//fun Greeting(name: String) {
//    Text(text = "Hello $name!", modifier = Modifier.padding(24.dp))
//}

@Composable
fun MyScreenContent(names: List<String> = listOf("Android", "there")) {
    val counterState = remember { mutableStateOf(0) }

    Column(modifier = Modifier.fillMaxHeight()) {
        NameList(
            names = List<String>(1000) { "Android #$it" },
            modifier = Modifier.weight(1f)
        )
        Counter(
            count = counterState.value,
            updateCount = { newCount ->
                counterState.value = newCount
            }
        )
    }
}

//@Composable
//fun MyScreenContent(names: List<String> = listOf("Android", "there")) {
//    val counterState = remember { mutableStateOf(0) }
//
//    Column(modifier = Modifier.fillMaxHeight()) {
//        Column(modifier = Modifier.weight(1f)) {
//            for (name in names) {
//                Greeting(name = name)
//                Divider(color = Color.Black)
//            }
//        }
//        Counter(
//            count = counterState.value,
//            updateCount = { newCount ->
//                counterState.value = newCount
//            }
//        )
//    }
//}

//@Composable
//fun MyScreenContent() {
//    Column {
//        Greeting("Android")
//        Divider(color = Color.Black)
//        Greeting("there")
//    }
//}

//@Composable
//fun MyScreenContent(names: List<String> = listOf("Android", "there", "world")) {
//    val counterState = remember { mutableStateOf(0) }
//
//    Column {
//        for (name in names) {
//            Greeting(name = name)
//            Divider(color = Color.Black)
//        }
//        Counter(
//            count = counterState.value,
//            updateCount = { newCount ->
//                counterState.value = newCount
//            }
//        )
//    }
//}

//@Composable
//fun Counter() {
//    val count = remember { mutableStateOf(0) }
//    Button(onClick = { count.value++ }, modifier = Modifier.padding(24.dp)) {
//        Text("I've been clicked ${count.value} times")
//    }
//}

@Composable
fun Counter(count: Int, updateCount: (Int) -> Unit) {
    OutlinedButton(
        onClick = { updateCount(count + 1) },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = when (count) {
                in 5..10 -> Color.Green
                in 10..15 -> Color.Cyan
                in 15..20 -> Color.Blue
                else -> Color.White
            })
    ) {
        Text("I've been clicked $count times")
    }
}

@Composable
fun NameList(names: List<String>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(items = names) { name ->
            Greeting(name = name)
            Divider(color = Color.Black)
        }
    }
}

@Composable
fun Greeting(name: String) {
    var isSelected by remember { mutableStateOf(false) }
    val backgroundColor by animateColorAsState(if (isSelected) Color.Red else Color.Transparent)

    Text(
        text = "Hello $name!",
        modifier = Modifier
            .padding(24.dp)
            .background(color = backgroundColor)
            .clickable(onClick = { isSelected = !isSelected }),
        style = MaterialTheme.typography.h3
    )
}
