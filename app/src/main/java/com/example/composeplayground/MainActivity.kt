package com.example.composeplayground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeplayground.ui.theme.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { Content() }
    }
}

@Composable
fun Content() {
    ThemedContent { changeTheme ->
        Surface(
            color = ComposePlaygroundTheme.colors.background,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Stuff()
                ThemeButtons(changeTheme)
            }
        }
    }
}

@Composable
fun Stuff() {
    Column {
        Text(
            text = "Some text",
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
fun ThemeButtons(
    changeTheme: (AppTheme) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Button(onClick = { changeTheme(AppTheme.Dark) }) {
            Text(text = "Dark")
        }

        Button(onClick = { changeTheme(AppTheme.Light) }) {
            Text(text = "Light")
        }

        Button(onClick = { changeTheme(AppTheme.Auto) }) {
            Text(text = "Auto")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ContentPreview() {
    Content()
}
