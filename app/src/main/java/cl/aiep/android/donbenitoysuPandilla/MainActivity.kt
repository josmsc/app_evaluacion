package cl.aiep.android.donbenitoysuPandilla

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import cl.aiep.android.donbenitoysuPandilla.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


            val usuario = findViewById<EditText>(R.id.usuario)
            val contraseña = findViewById<EditText>(R.id.contrseña)

        findViewById<Button>(R.id.btn_Ingresar).setOnClickListener {
            val user = usuario.text.toString()
            val password = contraseña.text.toString()
            //Validacion del ingreso de los campos
            if(user.isNullOrEmpty()||password.isNullOrEmpty()){
                Toast.makeText(this, "Debes igresar los datos", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            val intentLogin = Intent (this ,RegistroXvista::class.java)
            startActivity(intentLogin)
            finish()
        }



    }



    /*
    private fun ingresar(view: View){
        startActivity(Intent(this,RegistroXvista::class.java))
    }*/
}