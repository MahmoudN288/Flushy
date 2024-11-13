package com.elTohamy.flushy

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.core.content.ContextCompat
import androidx.core.os.ConfigurationCompat
import androidx.core.text.layoutDirection
import androidx.core.view.WindowCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.elTohamy.flushy.data.repos.firebase.AuthUiClient
import com.elTohamy.flushy.data.repos.firebase.UserData
import com.elTohamy.flushy.presentation.auth.SignInViewModel
import com.elTohamy.flushy.presentation.navigation.mainNavigation.navGraph.Graph
import com.elTohamy.flushy.presentation.navigation.mainNavigation.navGraph.RootNavigationGraph
import com.elTohamy.flushy.presentation.ui.theme.DarkSystemBars
import com.elTohamy.flushy.presentation.ui.theme.FlushyTheme
import com.elTohamy.flushy.presentation.uiMode.AppTheme
import com.elTohamy.flushy.presentation.uiMode.UserSettings
import com.elTohamy.flushy.utils.DarkTheme
import com.elTohamy.flushy.utils.LocalTheme
import com.elTohamy.flushy.utils.getLocale
import com.google.android.gms.auth.api.identity.Identity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

private const val TAG = "MainActivity"
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    // Declare the launcher at the top of your Activity/Fragment:
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission(),
    ) { isGranted: Boolean ->
        if (isGranted) {
            // FCM SDK (and your app) can post notifications.
        } else {
            // TODO: Inform user that that your app will not show notifications.
        }
    }

    private fun askNotificationPermission() {
        // This is only necessary for API level >= 33 (TIRAMISU)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (
                ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.POST_NOTIFICATIONS) ==
                PackageManager.PERMISSION_GRANTED
            ) {
                // FCM SDK (and your app) can post notifications.
            } else if (shouldShowRequestPermissionRationale(
                    android.Manifest.permission.POST_NOTIFICATIONS
            )) {
                // TODO: display an educational UI explaining to the user the features that will be enabled
                //       by them granting the POST_NOTIFICATION permission. This UI should provide the user
                //       "OK" and "No thanks" buttons. If the user selects "OK," directly request the permission.
                //       If the user selects "No thanks," allow the user to continue without notifications.
            } else {
                // Directly ask for the permission
                requestPermissionLauncher.launch(
                    android.Manifest.permission.POST_NOTIFICATIONS
                )
            }
        }
    }

    @Inject
    lateinit var userSettings: UserSettings

    private val flushyAuthUiClient by lazy {
        AuthUiClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            //LockScreenOrientation(orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
            val context = this
            val local = if (getLocale()!!.layoutDirection == LayoutDirection.Rtl.ordinal)
                LayoutDirection.Rtl
            else LayoutDirection.Ltr
            val theme = userSettings.themeStream.collectAsState()
            val useDarkColors = when (theme.value) {
                AppTheme.MODE_AUTO -> DarkTheme(isSystemInDarkTheme())
                AppTheme.MODE_DAY -> DarkTheme(false)
                AppTheme.MODE_NIGHT -> DarkTheme(true)
            }
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

                WindowCompat.getInsetsController(
                    activity.window, activity.window.decorView
                ).apply {
                    isAppearanceLightStatusBars = false
                    isAppearanceLightNavigationBars = false
                }

                onDispose { }
            }
            CompositionLocalProvider(
                LocalTheme provides useDarkColors,
                LocalLayoutDirection provides local
            ) {
                FlushyTheme(darkTheme = LocalTheme.current.isDark) {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        askNotificationPermission()
                        val navController = rememberNavController()
                        val viewModel: SignInViewModel = viewModel()
                        val launcher = rememberLauncherForActivityResult(
                            contract = ActivityResultContracts.StartIntentSenderForResult()
                        ) { result ->
                            if (result.resultCode == RESULT_OK) {
                                lifecycleScope.launch {
                                    val signInResult = flushyAuthUiClient.signInWithIntent(
                                        intent = result.data ?: return@launch
                                    )
                                    viewModel.signInUser(signInResult)
                                }
                            }
                        }
                        val state by viewModel.signInState.collectAsStateWithLifecycle()
                        LaunchedEffect(key1 = Unit) {
                            _isLoading.value = true // Set loading state to true initially
                            if (flushyAuthUiClient.getSignedInUser() != null) {
                                navController.navigate(Graph.HOME) {
                                    popUpTo(Graph.AUTH) {
                                        inclusive = true
                                    }
                                }
                            }
                            _isLoading.value = false // Set loading state to false after check
                        }
                        LaunchedEffect(key1 = state.isSuccess) {
                            if (state.isSuccess) {
                                Toast.makeText(
                                    applicationContext, "Success",
                                    Toast.LENGTH_SHORT
                                ).show()
                                navController.navigate(Graph.HOME) {
                                    popUpTo(Graph.AUTH) {
                                        inclusive = true
                                    }
                                }
                                addUserToFirestore(flushyAuthUiClient, context)
                            }
                        }
                        RootNavigationGraph(
                            navController = navController,
                            selectedTheme = theme.value,
                            authUiClient = flushyAuthUiClient,
                            onItemSelected = { theme->
                                userSettings.theme = theme
                            },
                            locale = getLocale(),
                            country = "world",
                            q = stringResource(id = R.string.footBall),
                            state = state,
                            userData = flushyAuthUiClient.getSignedInUser(),
                            signOut = {
                                lifecycleScope.launch {
                                    flushyAuthUiClient.signOut()
                                    Toast.makeText(
                                        applicationContext, "Signed Out",
                                        Toast.LENGTH_LONG
                                    ).show()
                                    navController.navigate(Graph.AUTH) {
                                        popUpTo(Graph.HOME) {
                                            inclusive = true
                                        }
                                    }
                                }
                            }
                        ) {
                            lifecycleScope.launch {
                                val signInIntentSender = flushyAuthUiClient.signIn()
                                launcher.launch(
                                    IntentSenderRequest.Builder(
                                        signInIntentSender ?: return@launch
                                    ).build()
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

fun addUserToFirestore(
    flushyAuthUiClient: AuthUiClient, context: Context
) {
    val db = Firebase.firestore
    db.collection("users")
        .document(flushyAuthUiClient.getSignedInUser()?.userId!!).set(
        UserData(
            userId =
            flushyAuthUiClient.getSignedInUser()?.userId,
            userName =
            flushyAuthUiClient.getSignedInUser()?.userName,
            profilePictureUrl =
            flushyAuthUiClient.getSignedInUser()?.profilePictureUrl
        )
    ).addOnSuccessListener {
        Toast.makeText(context, "Added", Toast.LENGTH_LONG).show()
    }.addOnFailureListener {
        Toast.makeText(context, "Failed", Toast.LENGTH_LONG).show()
    }
}

fun getMatchDayAndMonth(date: String): String? {
    val parser = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
    val formatter = SimpleDateFormat("d MMM", Locale.ENGLISH)
    return date.let { it -> parser.parse(it)?.let { formatter.format(it)  } }
}

fun getMatchTime(date: String): String? {
    val parser = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
    val formatter = SimpleDateFormat("hh:mm a", Locale.ENGLISH)//06:30 pm
    return date.let { it -> parser.parse(it)?.let { formatter.format(it)  } }
}

@Preview(showBackground = true)
@Composable
fun PreviewFunction(){
    Surface(modifier = Modifier.fillMaxSize()) {
    }
}

internal val LocalCollapsingLayoutOffset = compositionLocalOf { 0f }