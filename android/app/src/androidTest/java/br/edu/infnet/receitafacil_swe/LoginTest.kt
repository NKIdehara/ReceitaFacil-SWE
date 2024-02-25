package br.edu.infnet.receitafacil_swe

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth.assertThat
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class LoginTest {
    private lateinit var auth: FirebaseAuth
    private lateinit var usuario: String

    @Test
    fun testLogin_True() {

//        val inputStream: InputStream = getAssets().open("filename.json")

//        val inputStream = getAssets().open("XXXXX-6e000f81XXXX.json")
//        val credentials = GoogleCredentials.fromStream(inputStream)        .createScoped(Lists.newArrayList("https://www.googleapis.com/auth/cloudplatform"))
//        val credentialsProvider = FixedCredentialsProvider.create(credentials)
//        val speechSettings = SpeechSettings.newBuilder().setCredentialsProvider(credentialsProvider).build();


//        val resourceAsStream: InputStream = AuthTest::class.java.getClassLoader()
//            .getResourceAsStream("Google-Play-Android-Developer.json")
//
//        val credential: GoogleCredential = GoogleCredential.fromStream(resourceAsStream)
//
//
        val firebase = FirebaseApp.initializeApp(InstrumentationRegistry.getInstrumentation().targetContext)
        FirebaseAuth.getInstance(firebase!!).createUserWithEmailAndPassword("novo_usuario@teste.com", "123456")
//        usuario = FirebaseAuth.getInstance(firebase!!).currentUser.toString()
        auth = Firebase.auth
//        usuario = auth.currentUser.toString()
        usuario = "TESTE"
//        auth = Firebase.auth
        auth.createUserWithEmailAndPassword("novo_teste@teste.com", "123456")
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    usuario = auth.currentUser.toString()
                } else {
                    usuario = ""
                }
            }
        assertThat(usuario).isEqualTo("TESTE")
    }
}