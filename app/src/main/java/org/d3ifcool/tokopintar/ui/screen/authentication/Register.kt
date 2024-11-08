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
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.d3ifcool.tokopintar.R

@Composable
fun RegisterScreen(
    authViewModel: AuthViewModel = viewModel(),
    onRegisterSuccess: () -> Unit,
    onLoginClick: () -> Unit
) {
    var storeName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var registrationStatus by remember { mutableStateOf("") }

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
            text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.titleLarge,
            color = Color.Black
        )

        Text(
            text = "Mulai gratis tanpa risiko sekarang\ntemukan solusi terbaik untuk bisnismu",
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Register form
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
                    text = "Daftar",
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = storeName,
                    onValueChange = { storeName = it },
                    label = { Text("Nama Toko") },
                    maxLines = 1,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text(stringResource(R.string.email)) },
                    maxLines = 1,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text(stringResource(R.string.password)) },
                    maxLines = 1,
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = phoneNumber,
                    onValueChange = { phoneNumber = it },
                    label = { Text("Nomor Telepon") },
                    maxLines = 1,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = {
                    authViewModel.registerUser(email, password, storeName, phoneNumber) { success, message ->
                        registrationStatus = message
                        if (success) {
                            onRegisterSuccess() // Navigasi ke login setelah registrasi berhasil
                        }
                    }
                }
                ) {
                    Text(stringResource(R.string.login))
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Login link
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text("Sudah punya akun?")
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = stringResource(R.string.login),
                        color = Color.Blue,
                        textDecoration = TextDecoration.Underline,
                        modifier = Modifier.clickable { onLoginClick() }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(
        onRegisterSuccess = { /* Tidak ada aksi untuk preview */ },
        onLoginClick = { /* Tidak ada aksi untuk preview */ }
    )
}
