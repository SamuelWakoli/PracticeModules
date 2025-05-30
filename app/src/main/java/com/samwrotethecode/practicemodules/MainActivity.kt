package com.samwrotethecode.practicemodules

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.samwrotethecode.practicemodules.core.ui.theme.PracticeModulesTheme
import kotlin.jvm.java

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PracticeModulesTheme {
                MainScreen(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    Scaffold(modifier = modifier, topBar = {
        CenterAlignedTopAppBar(
            title = {
                Text("App/Home Screen")
            }
        )
    }) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text("This is the MainActivity")
            Spacer(Modifier.size(16.dp))
            Button(onClick = {
                Intent(context, AuthenticationActivity::class.java).apply {
                    context.startActivity(this)
                }
            }) {
                Text("Open Auth Activity")
            }
            Spacer(Modifier.size(16.dp))
            Button(onClick = {
                Intent(context, SettingsActivity::class.java).apply {
                    context.startActivity(this)
                }
            }) {
                Text("Open Settings Activity")
            }
            Spacer(Modifier.size(16.dp))
        }
    }
}

@Preview
@Composable
private fun MainScreenPreview() {
    PracticeModulesTheme {
        MainScreen(modifier = Modifier.fillMaxSize())
    }
}