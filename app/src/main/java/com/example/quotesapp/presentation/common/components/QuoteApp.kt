package com.example.quotesapp.presentation.common.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.quotesapp.presentation.common.components.navigation.BottomBar
import com.example.quotesapp.presentation.common.components.navigation.BottomNavGraph
import com.example.quotesapp.presentation.common.components.ui.theme.CharcoalBlue
import com.example.quotesapp.presentation.common.components.ui.theme.DeepNavyBlue
import com.example.quotesapp.presentation.common.components.ui.theme.MidnightBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuoteApp() {
    val navController = rememberNavController()

    val annotatedTitle = getAppTitle()

    Scaffold(
        containerColor = MidnightBlue,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(containerColor = DeepNavyBlue),
                title = { Text(annotatedTitle) }
            )
        },
        bottomBar = {
            BottomAppBar(containerColor = CharcoalBlue) {
                BottomBar(navController)
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            BottomNavGraph(navController)
        }
    }
}