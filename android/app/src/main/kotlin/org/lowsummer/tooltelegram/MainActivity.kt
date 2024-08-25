package org.lowsummer.tooltelegram

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

import soup.compose.material.motion.animation.materialSharedAxisXIn
import soup.compose.material.motion.animation.materialSharedAxisXOut

import org.lowsummer.tooltelegram.R
import org.lowsummer.tooltelegram.ui.theme.AntSummerTheme
import org.lowsummer.tooltelegram.ui.components.ApplicationScreen
import org.lowsummer.tooltelegram.ui.components.appbars.TopBar
import org.lowsummer.tooltelegram.ui.components.cards.SimpleCard
import org.lowsummer.tooltelegram.ui.screens.sendprivate.SendPrivateMessageScreen
import org.lowsummer.tooltelegram.ui.screens.sendcommunity.SendCommunityMessageScreen

class MainActivity : ComponentActivity() {

    companion object {
         const val MSAX_SLIDE_DISTANCE: Int = 100
         const val MSAX_DURATION: Int = 700
    }

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AntSummerTheme(highContrastDarkTheme = true) {
                 val navController = rememberNavController()
                 NavHost(
                     navController = navController,
                     startDestination = "main",
                     enterTransition = { 
                          materialSharedAxisXIn(
                              forward = true, 
                              slideDistance = MSAX_SLIDE_DISTANCE,
                              durationMillis = MSAX_DURATION
                          ) 
                     },
                     exitTransition = { 
                          materialSharedAxisXOut(
                              forward = true, 
                              slideDistance = MSAX_SLIDE_DISTANCE,
                              durationMillis = MSAX_DURATION
                          ) 
                     },
                     popEnterTransition = { 
                          materialSharedAxisXIn(
                              forward = false, 
                              slideDistance = MSAX_SLIDE_DISTANCE,
                              durationMillis = MSAX_DURATION
                          ) 
                     },
                     popExitTransition = { 
                          materialSharedAxisXOut(
                              forward = false,
                              slideDistance = MSAX_SLIDE_DISTANCE,
                              durationMillis = MSAX_DURATION
                          ) 
                     }
                 ) {
                      composable("main") {
                           MainScreen(
                                onSendCommunityMessageClicked = {
                                       navController.navigate("sendChannel")
                                },
                                onSendPrivateMessageClicked = {
                                      navController.navigate("sendPrivate")
                                }
                           )
                      }
                      composable("sendPrivate") {
                           SendPrivateMessageScreen(navController)
                      }
                      composable("sendChannel") {
                           SendCommunityMessageScreen(navController)
                      }
                 }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    onSendCommunityMessageClicked: () -> Unit,
    onSendPrivateMessageClicked: () -> Unit
) {
    ApplicationScreen(
          modifier = Modifier.padding(start = 10.dp, end = 10.dp),
          verticalArrangement = Arrangement.spacedBy(8.dp),
          topBar = {
               TopBar(
                    barTitle = stringResource(id = R.string.app_name),
                    scrollBehavior = it
               )
          },
          content = { 
               Column(
                    modifier = Modifier
                         .fillMaxSize()
               ) {
                   SimpleCard(
                       modifier = Modifier,
                       stringResource(id = R.string.app_name),
                       content = {
                             Column {
                                  Spacer(modifier = Modifier.weight(1F))
                                  Button(
                                       onClick = onSendPrivateMessageClicked
                                  ) {
                                       Text(
                                           text = stringResource(id = R.string.send_private_message)
                                       )
                                  }
                                  Spacer(modifier = Modifier.weight(1F))
                                  Button(
                                       onClick = onSendCommunityMessageClicked
                                  ) {
                                       Text(
                                           text = stringResource(id = R.string.send_community_message)
                                       )
                                  }
                             }
                       }
                   )
               }
          }
    )
}
