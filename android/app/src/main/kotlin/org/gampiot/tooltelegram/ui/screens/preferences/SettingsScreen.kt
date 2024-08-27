package org.gampiot.tooltelegram.ui.screens.preferences

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
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource

import org.gampiot.tooltelegram.R
import org.gampiot.tooltelegram.ui.components.ApplicationScreen
import org.gampiot.tooltelegram.ui.components.appbars.TopBar
import org.gampiot.tooltelegram.ui.components.Title
import org.gampiot.tooltelegram.ui.components.preferences.PreferenceItem
import org.gampiot.tooltelegram.ui.viewmodels.preferences.SettingsUIState
import org.gampiot.tooltelegram.ui.viewmodels.preferences.SettingsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navController: NavController,
    viewModel: SettingsViewModel,
    uiState: SettingsUIState
) {
    val context = LocalContext.current
    
    val defaultModifier = Modifier.fillMaxWidth()
    
    ApplicationScreen(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        topBar = {
            TopBar(
                barTitle = stringResource(id = R.string.settings_label),
                scrollBehavior = it,
                onClickBackButton = {
                    navController.popBackStack()
                }
            )
        },
        content = {
                Title(title = stringResource(id = R.string.appearance_label))
                PreferenceItem (
                     title = stringResource(id = R.string.use_monet_label),
                     description = stringResource(id = R.string.use_monet_description),
                     showToggle = true,
                     isChecked = uiState.isUseMonet,
                     onClick = {
                         viewModel.onIsUseMonetChange(!it)
                     }
                )
                PreferenceItem (
                     title = stringResource(id = R.string.use_high_contrast_label),
                     description = stringResource(id = R.string.use_high_contrast_description),
                     showToggle = true,
                     isChecked = uiState.isUseHighContrast,
                     onClick = {
                         viewModel.onIsUseHighContrastChange(!it)
                     }
                )
                Title(title = stringResource(id = R.string.about_label))
                PreferenceItem (
                     title = stringResource(id = R.string.libraries_label),
                     description = stringResource(id = R.string.libraries_description),
                     onClick = {
                         navController.navigate("settings/libraries")
                     }
                )
        }
    )
}