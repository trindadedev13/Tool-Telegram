package org.lowsummer.tooltelegram.ui.screens.sendprivate

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
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.outlined.Settings
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource

import org.lowsummer.tooltelegram.R
import org.lowsummer.tooltelegram.ui.theme.AntSummerTheme
import org.lowsummer.tooltelegram.ui.components.ApplicationScreen
import org.lowsummer.tooltelegram.ui.components.appbars.TopBar
import org.lowsummer.tooltelegram.ui.components.cards.SimpleCard
import org.lowsummer.tooltelegram.ui.components.dialog.TDialog
import org.lowsummer.tooltelegram.ui.viewmodels.sendprivate.SendPrivateMessageViewModel
import org.lowsummer.tooltelegram.ui.viewmodels.sendprivate.SendPrivateMessageUIState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SendPrivateMessageScreen(
    navController: NavController
) {
    val viewModel: SendPrivateMessageViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()
    val chatId = uiState.chatId
    val token = uiState.token
    val message = uiState.message
    val isSuccess = uiState.isSuccess
    val context = LocalContext.current
    val defaultModifier = Modifier.fillMaxWidth()

    val isShowDialog = remember { mutableStateOf(false) }

    LaunchedEffect(isSuccess) {
        isSuccess?.let {
            isShowDialog.value = true
        }
    }

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
                                viewModel.onChatIdChange(it)
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
                                viewModel.onTokenChange(it)
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
                                viewModel.onMessageChange(it)
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
                                viewModel.onClickToSend(chatId, token, message, context)
                            }
                        ) {
                            Text(text = stringResource(id = R.string.send_label))
                        }
                    }
                )
            }
        }
    )

    if (isShowDialog.value) {
        if (isSuccess == true) {
            sd {
               isShowDialog.value = false
               // viewModel.onIsSuccessChange(null)
            }
        } else {
            ed {
               isShowDialog.value = false
               // viewModel.onIsSuccessChange(null)
            }
        }
    }
}

@Composable
fun sd(
    onDismiss: () -> Unit
) {
    TDialog(
        onDismissRequest = onDismiss,
        onConfirmation = onDismiss,
        dialogTitle = stringResource(id = R.string.dialog_success_title),
        dialogText = stringResource(id = R.string.dialog_success_text),
        icon = Icons.Filled.CheckCircle,
        iconDescription = stringResource(id = R.string.dialog_success_title)
    )
}

@Composable
fun ed(
    onDismiss: () -> Unit
) {
    TDialog(
        onDismissRequest = onDismiss,
        onConfirmation = onDismiss,
        dialogTitle = stringResource(id = R.string.dialog_error_title),
        dialogText = stringResource(id = R.string.dialog_error_text),
        icon = Icons.Outlined.Settings, 
        iconDescription = stringResource(id = R.string.dialog_error_title)
    )
}
