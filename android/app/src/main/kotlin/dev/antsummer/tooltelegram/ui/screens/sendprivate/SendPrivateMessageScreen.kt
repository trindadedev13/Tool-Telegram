package dev.antsummer.tooltelegram.ui.screens.sendprivate

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.Error
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel

import dev.antsummer.tooltelegram.R
import dev.antsummer.tooltelegram.ui.theme.AntSummerTheme
import dev.antsummer.tooltelegram.ui.components.ApplicationScreen
import dev.antsummer.tooltelegram.ui.components.appbars.TopBar
import dev.antsummer.tooltelegram.ui.components.cards.SimpleCard
import dev.antsummer.tooltelegram.ui.components.dialog.TDialog
import dev.antsummer.tooltelegram.ui.viewmodels.sendprivate.SendPrivateMessageViewModel
import dev.antsummer.tooltelegram.ui.viewmodels.sendprivate.SendPrivateMessageUIState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SendPrivateMessageScreen() {
    val viewModel: SendPrivateMessageViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()
    val chatId = uiState.chatId
    val token = uiState.token
    val message = uiState.message
    val context = LocalContext.current
    val defaultModifier = Modifier.fillMaxWidth()
    

    ApplicationScreen(
        modifier = Modifier.padding(start = 10.dp, end = 10.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        topBar = {
            TopBar(
                barTitle = stringResource(id = R.string.send_private_message),
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
                    stringResource(id = R.string.send_private_message),
                    content = {
                        OutlinedTextField(
                            modifier = defaultModifier,
                            value = chatId,
                            onValueChange = {
                                uiState.onChatIdChange(it)
                            },
                            label = {
                                Text(
                                    text = stringResource(id = R.string.chat_id_label)
                                )
                            }
                        )

                        OutlinedTextField(
                            modifier = defaultModifier,
                            value = token,
                            onValueChange = {
                                uiState.onTokenChange(it)
                            },
                            label = {
                                Text(
                                    text = stringResource(id = R.string.token_label)
                                )
                            }
                        )

                        OutlinedTextField(
                            modifier = defaultModifier,
                            value = message,
                            onValueChange = {
                                uiState.onMessageChange(it)
                            },
                            label = {
                                Text(
                                    text = stringResource(id = R.string.message_label)
                                )
                            }
                        )
                        Button(
                            modifier = defaultModifier,
                            onClick = {
                                uiState.onClickToSend(chatId, token, message, context)
                                var isShowDialog by remember { mutableStateOf(true) }
                                if (uiState.isSuccess) {
                                   TDialog(
                                       onDismissRequest = { isShowDialog = false },
                                       onConfirmation = { isShowDialog = false },
                                       dialogTitle = stringResource(id = R.string.dialog_success_title)
                                       dialogText = stringResource(id = R.string.dialog_success_text)
                                       icon = Icons.Filled.CheckCircle,
                                       iconDescription = stringResource(id = R.string.dialog_success_title)
                                   )
                                } else {
                                   TDialog(
                                       onDismissRequest = { isShowDialog = false },
                                       onConfirmation = { isShowDialog = false },
                                       dialogTitle = stringResource(id = R.string.dialog_error_title),
                                       dialogText = stringResource(id = R.string.dialog_error_text),
                                       icon = Icons.Filled.Error,
                                       iconDescription = stringResource(id = R.string.dialog_error_title)
                                   )
                                }
                            }
                        ){
                            Text(text = stringResource(id= R.string.send_label))
                        }
                    }
                )
            }
        }
    )
}
