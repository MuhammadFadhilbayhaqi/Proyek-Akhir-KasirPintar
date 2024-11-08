package org.d3ifcool.tokopintar.ui.screen.authentication

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.d3ifcool.tokopintar.model.Users

class AuthViewModel : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    // Fungsi untuk mendaftar pengguna baru
    fun registerUser(email: String, password: String, storeName: String, phoneNumber: String, onResult: (Boolean, String) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val uid = auth.currentUser?.uid
                    if (uid != null) {
                        val newUser = Users(uid, email, storeName, phoneNumber)
                        saveUserToFirestore(newUser, onResult)
                    } else {
                        onResult(false, "UID tidak ditemukan.")
                    }
                } else {
                    onResult(false, task.exception?.message ?: "Registrasi gagal")
                }
            }
    }

    // Fungsi untuk menyimpan data pengguna ke Firestore
    private fun saveUserToFirestore(user: Users, onResult: (Boolean, String) -> Unit) {
        firestore.collection("users").document(user.uid).set(user)
            .addOnSuccessListener {
                Log.d("Firestore", "Data pengguna berhasil disimpan")
                onResult(true, "Registrasi berhasil dan data disimpan.")
            }
            .addOnFailureListener { e ->
                Log.d("Firestore", "Gagal menyimpan data pengguna: ${e.message}")
                onResult(false, "Gagal menyimpan data pengguna: ${e.message}")
            }
    }


    // Fungsi untuk masuk dengan pengguna yang sudah terdaftar
    fun loginUser(email: String, password: String, onResult: (Boolean, String) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onResult(true, "Login berhasil")
                } else {
                    onResult(false, task.exception?.message ?: "Login gagal")
                }
            }
    }
}