package dev.trindadedev.tooltelegram.ui.screens.sendcommunity

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import dev.trindadedev.tooltelegram.R
import dev.trindadedev.tooltelegram.ui.components.ApplicationScreen
import dev.trindadedev.tooltelegram.ui.components.appbars.TopBar
import dev.trindadedev.tooltelegram.ui.components.cards.SimpleCard
import dev.trindadedev.tooltelegram.ui.components.dialog.TDialog
import dev.trindadedev.tooltelegram.ui.viewmodels.sendcommunity.SendCommunityMessageViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SendCommunityMessageScreen(navController: NavController) {
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

    ApplicationScreen(
        modifier = Modifier.padding(start = 10.dp, end = 10.dp).fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        topBar = {
            TopBar(
                barTitle = stringResource(id = R.string.send_community_message),
                scrollBehavior = it,
                onClickBackButton = { navController.popBackStack() },
            )
        },
        content = {
            Column(modifier = Modifier.fillMaxSize()) {
                SimpleCard(
                    modifier = Modifier,
                    stringResource(id = R.string.send_community_message),
                    content = {
                        OutlinedTextField(
                            modifier = defaultModifier,
                            value = chatId,
                            onValueChange = { viewModel.onChatIdChange(it) },
                            label = { Text(text = stringResource(id = R.string.chat_id_label)) },
                        )

                        OutlinedTextField(
                            modifier = defaultModifier,
                            value = token,
                            onValueChange = { viewModel.onTokenChange(it) },
                            label = { Text(text = stringResource(id = R.string.token_label)) },
                        )

                        OutlinedTextField(
                            modifier = defaultModifier,
                            value = message,
                            onValueChange = { viewModel.onMessageChange(it) },
                            label = { Text(text = stringResource(id = R.string.message_label)) },
                        )

                        OutlinedTextField(
                            modifier = defaultModifier,
                            value = topicId,
                            onValueChange = { viewModel.onTopicIdChange(it) },
                            label = { Text(text = stringResource(id = R.string.topic_id_label)) },
                        )

                        OutlinedTextField(
                            modifier = defaultModifier,
                            value = imageUrl,
                            onValueChange = { viewModel.onImageUrlChange(it) },
                            label = { Text(text = stringResource(id = R.string.image_url_label)) },
                        )

                        Button(
                            modifier = defaultModifier,
                            onClick = {
                                viewModel.onClickToSend(chatId, token, message, imageUrl, context)
                            },
                        ) {
                            Text(text = stringResource(id = R.string.send_label))
                        }
                    },
                )
            }
        },
    )

    if (isSuccess != null) {
        if (isSuccess) {
            sd { viewModel.onIsSuccessChange(null) }
        } else {
            ed { viewModel.onIsSuccessChange(null) }
        }
    }
}

@Composable
fun sd(onDismiss: () -> Unit) {
    TDialog(
        onDismissRequest = onDismiss,
        onConfirmation = onDismiss,
        dialogTitle = stringResource(id = R.string.dialog_success_title),
        dialogText = stringResource(id = R.string.dialog_success_text),
        icon = Icons.Filled.CheckCircle,
        iconDescription = stringResource(id = R.string.dialog_success_title),
    )
}

@Composable
fun ed(onDismiss: () -> Unit) {
    TDialog(
        onDismissRequest = onDismiss,
        onConfirmation = onDismiss,
        dialogTitle = stringResource(id = R.string.dialog_error_title),
        dialogText = stringResource(id = R.string.dialog_error_text),
        icon = Icons.Outlined.Settings,
        iconDescription = stringResource(id = R.string.dialog_error_title),
    )
}
