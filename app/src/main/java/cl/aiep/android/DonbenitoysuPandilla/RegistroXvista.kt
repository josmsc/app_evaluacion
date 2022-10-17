package cl.aiep.android.DonbenitoysuPandilla

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import cl.aiep.android.DonbenitoysuPandilla.databinding.ActivityRegistroXVistaBinding

class RegistroXvista : AppCompatActivity() {

    private lateinit var binding: ActivityRegistroXVistaBinding

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        //Vinculacion de la vista con el activity
        binding = ActivityRegistroXVistaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Asignacion de la accion al Boton Registrar para ingresar a la sigueinte pantalla
        binding.btnRegistrar.setOnClickListener {
            ingresar()
        }
        binding.btnMostrar.setOnClickListener {
            //cargarLista()
        }
    }

    private fun ingresar() {
        startActivity(Intent(this,Formulario::class.java))
    }


}







