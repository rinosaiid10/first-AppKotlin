package com.rino.firstapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Telephony.Sms.Intents
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class  MainActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = this.getSharedPreferences("app_state", Context.MODE_PRIVATE)

        val is_authentificated = sharedPreferences.getBoolean("is_authentificated", false)

        //recuperer email
        val emailShare = sharedPreferences.getString("email","")
        // si l'on  est dejà connecter avant meme si il sort de appli il restera connecter
        if (is_authentificated){
            Intent(this,HomeActivity::class.java).also {
                it.putExtra("name",emailShare)
                startActivity(it)
            }
        }

        val connect = findViewById<Button>(R.id.connect)
        val email = findViewById<EditText>(R.id.email)
        val password = findViewById<EditText>(R.id.password)
        val error = findViewById<TextView>(R.id.error)




        connect.setOnClickListener  {
            error.visibility= View.GONE
            val txtEmail = email.text.toString()
            val txtPassword= password.text.toString()
 //methode trim () pour eviter les espaces
            if(txtEmail.trim().isEmpty() || txtPassword.trim().isEmpty()) {
                error.text = "Vous devez remplir tous les champs !"
                error.visibility = View.VISIBLE

            } else{
                val correctEmail = "rino@gmail.com"
                val correctPassword = "azerty"
                if (correctEmail==txtEmail && correctPassword==txtPassword){
                    email.setText("")
                    password.setText("")
                    // enregistrer dans sharedPreferences le boolean isAuthentificated
                    val editor = sharedPreferences.edit()
                    editor.putBoolean("is_authentificated", true)
                    editor.putString("email",txtEmail)
                        editor.apply()

                   val intentToHomeActivity: Intent= Intent(this, HomeActivity::class.java)
//Intent Explicite c'est a dire demarrer une nouvelle  activité
                    Intent(this, HomeActivity::class.java).also {
                        it.putExtra("email",txtEmail)
                        startActivity(it) }

                   /* intentToHomeActivity.apply {
                        intentToHomeActivity.putExtra("email",txtEmail)
                        startActivity(intentToHomeActivity)

                    }*/
                    // methode putExtra c'est a dire ajouter quelque chose de plus


                } else{
                    error.text = "Email ou password n'est pas correct"
                    error.visibility = View.VISIBLE

                }

            }

        }
    }
}