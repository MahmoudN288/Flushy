package com.elTohamy.flushy.presentation.activities.team

import android.app.Activity
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.LayoutDirection
import androidx.core.text.layoutDirection
import androidx.core.view.WindowCompat
import com.elTohamy.flushy.presentation.activities.team.tabRow.TeamTabScreen
import com.elTohamy.flushy.presentation.ui.theme.DarkSystemBars
import com.elTohamy.flushy.presentation.ui.theme.FlushyTheme
import com.elTohamy.flushy.presentation.uiMode.AppTheme
import com.elTohamy.flushy.presentation.uiMode.UserSettings
import com.elTohamy.flushy.utils.DarkTheme
import com.elTohamy.flushy.utils.LocalTheme
import com.elTohamy.flushy.utils.LockScreenOrientation
import com.elTohamy.flushy.utils.getLocale
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TeamActivity : ComponentActivity() {

    @Inject
    lateinit var userSettings: UserSettings

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val extras = intent.extras
        val teamId = extras?.getInt("teamId")
        val currentSeason = extras?.getInt("currentSeason")
        val teamName = extras?.getString("teamName")

        setContent {
            val dark = LocalTheme.current.isDark
            val view = LocalView.current
            val statusBarLight = Color.White.toArgb()
            val statusBarDark = DarkSystemBars.toArgb()
            val navigationBarLight = Color.White.toArgb()
            val navigationBarDark = DarkSystemBars.toArgb()
            DisposableEffect(dark) {
                val activity = view.context as Activity
                activity.window.statusBarColor =
                    if(dark){statusBarDark} else {statusBarLight}
                activity.window.navigationBarColor =
                    if(dark){navigationBarDark} else {navigationBarLight}

                WindowCompat.getInsetsController(activity.window, activity.window.decorView).apply {
                    isAppearanceLightStatusBars = !dark
                    isAppearanceLightNavigationBars = !dark
                }

                onDispose { }
            }
            LockScreenOrientation(orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
            val local = if (getLocale()!!.layoutDirection == LayoutDirection.Rtl.ordinal)
                LayoutDirection.Rtl
            else LayoutDirection.Ltr
            val theme = userSettings.themeStream.collectAsState()
            val useDarkColors = when (theme.value) {
                AppTheme.MODE_AUTO -> DarkTheme(isSystemInDarkTheme())
                AppTheme.MODE_DAY -> DarkTheme(false)
                AppTheme.MODE_NIGHT -> DarkTheme(true)
            }
            CompositionLocalProvider(LocalTheme provides useDarkColors, LocalLayoutDirection provides local) {
                FlushyTheme(darkTheme = LocalTheme.current.isDark) {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        TeamTabScreen(
                            id = teamId!!,
                            season = currentSeason!!,
                            locale = getLocale()!!,
                            country = "World",
                            teamName = teamName!!
                        )
                    }
                }
            }
        }
    }
}