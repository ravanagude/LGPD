package br.com.joshuaweb.lgpd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth

enum class ProviderType {
    BASIC
}

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //Setup

        val bundle:Bundle? = intent.extras
        val email:String? = bundle?.getString(key:"email")
        val provider: String? = bundle?.getString(key:"provider")
        setup(email:email ?: "", provider: provider ?: "")
    }

    private fun setup(email: String, provider: String) {
        title = "Inicio"
        emailTextView.text = email
        providerTextView.text = provider

        LogOutButton.setOnClickListener{it:View!
            FirebaseAuth.getInstance().signOut()
            onBackPressed()
        }

    }
}