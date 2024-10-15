package dev.trindadedev.tooltelegram.ui.screens.preferences

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dev.trindadedev.tooltelegram.R
import dev.trindadedev.tooltelegram.ui.components.ApplicationScreen
import dev.trindadedev.tooltelegram.ui.components.Title
import dev.trindadedev.tooltelegram.ui.components.appbars.TopBar
import dev.trindadedev.tooltelegram.ui.components.preferences.PreferenceItem
import dev.trindadedev.tooltelegram.ui.viewmodels.preferences.d.AppPreferencesViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(navController: NavController) {
    val appPrefsViewModel = koinViewModel<AppPreferencesViewModel>()
    val isUseMonet by appPrefsViewModel.isUseMonet.collectAsState(initial = true)
    val isUseHighContrast by appPrefsViewModel.isUseHighContrast.collectAsState(initial = false)

    val context = LocalContext.current

    val defaultModifier = Modifier.fillMaxWidth()

    ApplicationScreen(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        topBar = {
            TopBar(
                barTitle = stringResource(id = R.string.settings_label),
                scrollBehavior = it,
                onClickBackButton = { navController.popBackStack() },
            )
        },
        content = {
            Title(title = stringResource(id = R.string.appearance_label))
            PreferenceItem(
                title = stringResource(id = R.string.use_monet_label),
                description = stringResource(id = R.string.use_monet_description),
                showToggle = true,
                isChecked = isUseMonet,
                onClick = { appPrefsViewModel.enableMonet(!it) },
            )
            PreferenceItem(
                title = stringResource(id = R.string.use_high_contrast_label),
                description = stringResource(id = R.string.use_high_contrast_description),
                showToggle = true,
                isChecked = isUseHighContrast,
                onClick = { appPrefsViewModel.enableHighContrast(!it) },
            )
            Title(title = stringResource(id = R.string.about_label))
            PreferenceItem(
                title = stringResource(id = R.string.libraries_label),
                description = stringResource(id = R.string.libraries_description),
                onClick = { navController.navigate("settings/libraries") },
            )
        },
    )
}
