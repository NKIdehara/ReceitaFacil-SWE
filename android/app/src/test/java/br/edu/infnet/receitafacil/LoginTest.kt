package br.edu.infnet.receitafacil2

import com.google.common.truth.Truth.assertThat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.junit.Test

/***************************************************************************************************
 * Teste Unitário
 ***************************************************************************************************/
class TesteUnitario {
    private lateinit var auth: FirebaseAuth

    @Test
    fun teste_login(){
//        var auth: FirebaseAuth
        var login: Boolean = false

        // Inicia Firebase Auth
        auth = Firebase.auth
        auth.signInWithEmailAndPassword("teste@teste.com", "123456").addOnCompleteListener { task ->
            if (task.isSuccessful) {
                login = true
            }
        }
        assertThat(login).isTrue()
    }
}