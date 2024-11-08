package org.d3ifcool.tokopintar.ui.screen.authentication

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.d3ifcool.tokopintar.R

@Composable
fun LoginScreen(
    authViewModel: AuthViewModel = viewModel(),
    onLoginSuccess: () -> Unit,
    onRegisterClick: () -> Unit,
    onGoogleLoginClick: () -> Unit,
    onForgotPasswordClick: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var loginStatus by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Logo
        Image(
            painter = painterResource(id = R.drawable.app_logo), // Replace with your logo drawable
            contentDescription = "Logo",
            modifier = Modifier.size(150.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Toko Pintar",
            style = MaterialTheme.typography.titleLarge,
            color = Color.Black
        )

        Text(
            text = "Kelola usahamu dengan lebih bijak",
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Login form
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Masuk",
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    maxLines = 1,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Kata Sandi") },
                    maxLines = 1,
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Forgot Password
                Text(
                    text = "Lupa Kata Sandi?",
                    color = Color.Blue,
                    style = MaterialTheme.typography.bodySmall.copy(
                        textDecoration = TextDecoration.Underline
                    ),
                    modifier = Modifier.clickable { onForgotPasswordClick() }
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = {
                    authViewModel.loginUser(email, password) { success, message ->
                        loginStatus = message
                        if (success) {
                            onLoginSuccess() // Navigasi ke home setelah login berhasil
                        }
                    }
                }
                ) {
                    Text("Masuk")
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Register link
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text("Belum punya akun?")
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Daftar",
                        color = Color.Blue,
                        style = MaterialTheme.typography.bodySmall.copy(
                            textDecoration = TextDecoration.Underline
                        ),
                        modifier = Modifier.clickable { onRegisterClick() }
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                HorizontalDivider(
                    color = Color.Gray.copy(alpha = 0.5f),
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                    // Login with Google
                OutlinedButton(
                    onClick = onGoogleLoginClick,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row (
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Image(
                            painter = painterResource(R.drawable.google_logo),
                            contentDescription = "Google logo",
                            modifier = Modifier
                                .size(25.dp)
                                .padding(end = 8.dp)
                        )
                        Text(
                            "Masuk Dengan Google",
                            color = Color.Black
                        )
                    }

                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(
        onLoginSuccess = { /* Tidak ada aksi untuk preview */ },
        onRegisterClick = { /* Tidak ada aksi untuk preview */ },
        onGoogleLoginClick = { /* Tidak ada aksi untuk preview */ },
        onForgotPasswordClick = { /* Tidak ada aksi untuk preview */ }
    )
}
