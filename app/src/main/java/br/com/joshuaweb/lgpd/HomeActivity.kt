package br.com.joshuaweb.lgpd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.joshuaweb.lgpd.databinding.ActivityHomeBinding
import com.google.firebase.auth.FirebaseAuth

enum class ProviderType {
    BASIC
}

class HomeActivity : AppCompatActivity() {

    //    private lateinit var binding:
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //Setup

        val bundle = intent.extras
        val email:String? = bundle?.getString("email")
        val provider:String? = bundle?.getString("provider")
        setup(email ?: "", provider ?: "")


    }

    private fun setup(email: String, provider: String) {

        title = "Início"
        binding.emailTextView.text = email
        binding.provedorTextView.text = provider

        binding.logOutButton.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            onBackPressed()
        }

    }

}