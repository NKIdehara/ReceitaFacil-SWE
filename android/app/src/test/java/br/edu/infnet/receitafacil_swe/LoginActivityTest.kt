package br.edu.infnet.receitafacil_swe

import com.google.common.truth.Truth
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class LoginActivityTest {
    private lateinit var auth: FirebaseAuth

    @Before
    fun setup(){
        FirebaseApp.initializeApp(getBaseContext())
        auth = Firebase.auth
    }


    @Test
    fun teste_login(){
        var login: Boolean = false

        // Inicia Firebase Auth
        auth.signInWithEmailAndPassword("teste@teste.com", "123456").addOnCompleteListener { task ->
            if (task.isSuccessful) {
                login = true
            }
        }
        Truth.assertThat(login).isTrue()
    }
}