package com.elTohamy.flushy.presentation.bottomNavBar

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextGeometricTransform
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.wear.compose.material3.Text
import com.elTohamy.flushy.R
import com.elTohamy.flushy.data.state.SignInState
import com.elTohamy.flushy.presentation.customTheme.customColorsPalette

@Composable
fun SignInScreen(
    navHostController: NavHostController,
    state: SignInState,
    onSignInClick: ()-> Unit
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = state.isError) {
        state.isError?.let { error->
            Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(50.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier.size(200.dp),
                    painter = painterResource(id = R.drawable.flushy_logo_t),
                    contentDescription = "Flushy Logo"
                )
                Card(
                    modifier = Modifier,
                    shape = RoundedCornerShape(15.dp),
                    elevation = CardDefaults.cardElevation(4.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.customColorsPalette.mainCardContainer
                    ),
                    onClick = { onSignInClick() }
                ) {
                    Text(
                        modifier = Modifier.padding(10.dp),
                        text = stringResource(id = R.string.signIn),
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 20.sp
                        )
                    )
                }
            }
            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(25.dp)
            ) {
                Text(
                    modifier = Modifier,
                    text = stringResource(id = R.string.contactMe),
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp,
                        textGeometricTransform = TextGeometricTransform(
                            1f, 0.2f
                        )
                    )
                )
                val uriHandler = LocalUriHandler.current
                val gmail = "mahmoudnabil54321@gmail.com"
                val outlook = "mahmoudnabil288@hotmail.com"
                val linkedIn = "https://www.linkedin.com/in/mahmoud-nabil-01b901153"
                val phoneNumber = "+201090344538"
                val error = stringResource(id = R.string.error)
                val noMail = stringResource(id = R.string.noMail)
                val intent = remember {
                    Intent(Intent.ACTION_VIEW, Uri.parse(
                        linkedIn)
                    )
                }
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Image(
                        modifier = Modifier
                            .size(50.dp)
                            .clickable {
                                context.sendMail(
                                    gmail, "Contact Gmail",
                                    onError = {
                                        Toast
                                            .makeText(
                                                context,
                                                error,
                                                Toast.LENGTH_SHORT
                                            )
                                            .show()
                                    }
                                ) {
                                    Toast
                                        .makeText(
                                            context,
                                            noMail,
                                            Toast.LENGTH_SHORT
                                        )
                                        .show()
                                }
                            },
                        painter = painterResource(id = R.drawable.gmail),
                        contentDescription = "Gmail"
                    )
                    Image(
                        modifier = Modifier
                            .size(50.dp)
                            .clickable {
                                context.sendMail(
                                    outlook, "Contact Outlook",
                                    onError = {
                                        Toast
                                            .makeText(
                                                context,
                                                error,
                                                Toast.LENGTH_SHORT
                                            )
                                            .show()
                                    }
                                ) {
                                    Toast
                                        .makeText(
                                            context,
                                            noMail,
                                            Toast.LENGTH_SHORT
                                        )
                                        .show()
                                }
                            },
                        painter = painterResource(id = R.drawable.outlook),
                        contentDescription = "Outlook"
                    )
                    Image(
                        modifier = Modifier
                            .size(50.dp)
                            .clickable {
                                uriHandler.openUri(linkedIn)
                            },
                        painter = painterResource(id = R.drawable.linked_in),
                        contentDescription = "LinkedIn"
                    )
                    Image(
                        modifier = Modifier
                            .size(50.dp)
                            .clickable {
                                context.dial(
                                    phoneNumber
                                ) {
                                    Toast
                                        .makeText(
                                            context,
                                            error,
                                            Toast.LENGTH_SHORT
                                        )
                                        .show()
                                }
                            },
                        painter = painterResource(id = R.drawable.call_me),
                        contentDescription = "Contact Dial"
                    )
                }
            }
        }
    }
}

fun Context.sendMail(
    to: String, subject: String, onError: () -> Unit,
    onNoMail: () -> Unit
) {
    try {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "vnd.android.cursor.item/email" // or "message/rfc822"
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(to))
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        onNoMail()
    } catch (t: Throwable) {
        onError()
    }
}

fun Context.dial(phone: String, onError: () -> Unit) {
    try {
        val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null))
        startActivity(intent)
    } catch (t: Throwable) {
        onError()
    }
}

@Preview(showBackground = true)
@Composable
fun SignInPreview() {
    SignInScreen(
        navHostController = rememberNavController(),
        state = SignInState()
    ) {
    }
}