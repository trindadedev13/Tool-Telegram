package dev.trindadedev.tooltelegram.ui.components.dialog

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.vector.ImageVector

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    icon: ImageVector,
    iconDescription: String = "Icon",
) {
    AlertDialog(
        icon = { Icon(icon, contentDescription = iconDescription) },
        title = { Text(text = dialogTitle) },
        text = { Text(text = dialogText) },
        onDismissRequest = { onDismissRequest() },
        confirmButton = { TextButton(onClick = { onConfirmation() }) { Text("Confirm") } },
        dismissButton = { TextButton(onClick = { onDismissRequest() }) { Text("Dismiss") } },
    )
}
