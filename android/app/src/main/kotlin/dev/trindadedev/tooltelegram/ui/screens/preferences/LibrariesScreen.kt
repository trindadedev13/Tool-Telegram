package dev.trindadedev.tooltelegram.ui.screens.preferences

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mikepenz.aboutlibraries.Libs
import com.mikepenz.aboutlibraries.ui.compose.LibrariesContainer
import com.mikepenz.aboutlibraries.ui.compose.LibraryDefaults
import com.mikepenz.aboutlibraries.util.withContext
import dev.trindadedev.tooltelegram.R
import dev.trindadedev.tooltelegram.ui.components.ApplicationScreen
import dev.trindadedev.tooltelegram.ui.components.appbars.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibrariesScreen(navController: NavController) {
    val context = LocalContext.current
    val uriHandler = LocalUriHandler.current

    val libs = remember { mutableStateOf<Libs?>(null) }
    libs.value = Libs.Builder().withContext(context).build()
    val libraries = libs.value!!.libraries

    val appBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(appBarState)

    val defaultModifier = Modifier.fillMaxWidth()

    ApplicationScreen(
        enableDefaultScrollBehavior = false,
        columnContent = false,
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopBar(
                barTitle = stringResource(id = R.string.libraries_label),
                scrollBehavior = scrollBehavior,
                onClickBackButton = { navController.popBackStack() },
            )
        },
        content = {
            Column(modifier = Modifier.fillMaxSize()) {
                LibrariesContainer(
                    modifier =
                        Modifier.fillMaxSize()
                            .windowInsetsPadding(
                                WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal)
                            ),
                    colors =
                        LibraryDefaults.libraryColors(
                            backgroundColor = MaterialTheme.colorScheme.background,
                            contentColor = MaterialTheme.colorScheme.onBackground,
                            badgeBackgroundColor = MaterialTheme.colorScheme.secondaryContainer,
                            badgeContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                        ),
                    padding =
                        LibraryDefaults.libraryPadding(
                            namePadding = PaddingValues(bottom = 4.dp),
                            badgeContentPadding = PaddingValues(4.dp),
                        ),
                    onLibraryClick = { library ->
                        library.website?.let {
                            if (it.isNotEmpty()) {
                                uriHandler.openUri(it)
                            }
                        }
                    },
                )
            }
        },
    )
}
