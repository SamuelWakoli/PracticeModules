package com.samwrotethecode.practicemodules

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.samwrotethecode.practicemodules.core.ui.theme.PracticeModulesTheme

class FavoritesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PracticeModulesTheme {
                FavoritesScreen()
            }
        }
    }
}