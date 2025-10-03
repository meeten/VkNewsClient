package com.example.vknewsclient.presention.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vknewsclient.R
import com.example.vknewsclient.ui.theme.VkNewsClientTheme

@Composable
fun LoginScreen(onLoginClickListener: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(R.drawable.vk_logo),
            contentDescription = null,
            modifier = Modifier.padding(120.dp)
        )

        Button(
            onClick = { onLoginClickListener() },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.tertiary,
                contentColor = MaterialTheme.colorScheme.primary
            )
        ) {

            Text(
                text = stringResource(R.string.login),
                fontSize = 18.sp,
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun LoginScreenPreviewLight() {
    VkNewsClientTheme(darkTheme = false) {
        Box(modifier = Modifier.fillMaxSize()) {
            LoginScreen({

            })
        }
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun LoginScreenPreviewDark() {
    VkNewsClientTheme(darkTheme = true) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.primary)
        ) {
            LoginScreen { }
        }
    }
}