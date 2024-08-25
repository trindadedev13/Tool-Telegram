package dev.antsummer.tooltelegram.ui.screens.sendcommunity

import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import dev.antsummer.tooltelegram.R
import dev.antsummer.tooltelegram.ui.theme.AntSummerTheme
import dev.antsummer.tooltelegram.ui.components.ApplicationScreen
import dev.antsummer.tooltelegram.ui.components.appbars.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SendCommunityMessageScreen() {
    ApplicationScreen(
          modifier = Modifier.padding(start = 10.dp, end = 10.dp),
          verticalArrangement = Arrangement.spacedBy(8.dp),
          topBar = {
               TopBar(
                    barTitle = stringResource(id = R.string.send_channel_message),
                    scrollBehavior = it
               )
          },
          content = { 
               Column(
                    modifier = Modifier
                         .fillMaxSize()
               ) {
                   
               }
          }
    )
}