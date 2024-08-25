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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.outlined.Settings
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource

import dev.antsummer.tooltelegram.R
import dev.antsummer.tooltelegram.ui.theme.AntSummerTheme
import dev.antsummer.tooltelegram.ui.components.ApplicationScreen
import dev.antsummer.tooltelegram.ui.components.appbars.TopBar
import dev.antsummer.tooltelegram.ui.components.cards.SimpleCard
import dev.antsummer.tooltelegram.ui.components.dialog.TDialog
import dev.antsummer.tooltelegram.ui.viewmodels.sendcommunity.SendCommunityMessageViewModel
import dev.antsummer.tooltelegram.ui.viewmodels.sendcommunity.SendCommunityMessageUIState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SendCommunityMessageScreen() {
    val viewModel: SendCommunityMessageViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()
    val chatId = uiState.chatId
    val token = uiState.token
    val message = uiState.message
    val isSuccess = uiState.isSuccess
    val topicId = uiState.topicId
    val imageUrl = uiState.imageUrl
    
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
                barTitle = stringResource(id = R.string.send_community_message),
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
                    stringResource(id = R.string.send_community_message),
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
                        
                        OutlinedTextField(
                            modifier = defaultModifier,
                            value = topicId,
                            onValueChange = {
                                viewModel.onTopicIdChange(it)
                            },
                            label = {
                                Text(
                                    text = stringResource(id = R.string.topic_id_label)
                                )
                            }
                        )
                        
                        OutlinedTextField(
                            modifier = defaultModifier,
                            value = imageUrl,
                            onValueChange = {
                                viewModel.onImageUrlChange(it)
                            },
                            label = {
                                Text(
                                    text = stringResource(id = R.string.image_url_label)
                                )
                            }
                        )
                        
                        Button(
                            modifier = defaultModifier,
                            onClick = {
                                viewModel.onClickToSend(chatId, token, message, imageUrl, context)
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
               viewModel.onIsSuccessChange(false)
            }
        } else {
            ed {
               isShowDialog.value = false
               viewModel.onIsSuccessChange(false)
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
