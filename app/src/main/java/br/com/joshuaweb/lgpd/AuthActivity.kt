package br.com.joshuaweb.lgpd

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        //Splash
        Thread.sleep(2000) // HACK:
        setTheme(R.style.Theme_LGPD)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        //Analytics Event
        val analytics:FirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()
        bundle.putString("message", "Integración de Firebase completa")
        analytics.logEvent("InitScreen", bundle)

        //Setup
        setup()
    }

    private fun setup() {

        title = "Autenticación"

        singUpButton.setOnClickListener {it:View!
            if (emailEditText.text.isNotEmpty() && passwordEditText.text.isNotEmpty()){

                FirebaseAuth.getInstance().createUserWithEmailAndPassword(emailEditText.text.toString(),passwordEditText.text.toString()).addOnCanceledListener {it: Task<AuthResult>

                if (it.isSuccessful){
                    showHome(it.result?.user?.email ?: "", ProviderType.BASIC)
                }else{
                    showAlert()
                }

                }
            }
        }

        logInButton.setOnClickListener {it:View!
            if (emailEditText.text.isNotEmpty() && passwordEditText.text.isNotEmpty()){

                FirebaseAuth.getInstance().singInWithEmailAndPassword(emailEditText.text.toString(),passwordEditText.text.toString()).addOnCanceledListener {it: Task<AuthResult>

                    if (it.isSuccessful){
                        showHome(it.result?.user?.email ?: "", ProviderType.BASIC)
                    }else{
                        showAlert()
                    }

                }
            }
        }


    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(context: this)
        builder.setTitle("Error")
        builder.setMessage("Erro de autenticação de usuário")
        builder.setPositiveButton(text:"Aceptar", listener:null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showHome(email: String, provider: ProviderType){
        val homeIntent:Intent = Intent(packageContext:this, HomeActivity::class.java).apply {
            putExtra(name:"email", email)
            putExtra(name:"provider", provider.name)
        }
        startActivity(homeIntent)
    }


}