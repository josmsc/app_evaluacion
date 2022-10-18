package cl.aiep.android.donbenitoysuPandilla

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import cl.aiep.android.donbenitoysuPandilla.Constantes.KEY_DESCRIPCION
import cl.aiep.android.donbenitoysuPandilla.Constantes.KEY_ESPECIALIDAD
import cl.aiep.android.donbenitoysuPandilla.Constantes.KEY_LOCALIDAD
import cl.aiep.android.donbenitoysuPandilla.Constantes.KEY_NOMBRE
import cl.aiep.android.donbenitoysuPandilla.Constantes.TABLA_LUGARES
import cl.aiep.android.donbenitoysuPandilla.databinding.ActivityFormularioBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class Formulario : AppCompatActivity() {

    private lateinit var binding: ActivityFormularioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormularioBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnGuardarRestaurante.setOnClickListener {
            guardarDatosFirebase()
        }
    }
    /*Funcion para guardar datos en base local
    private fun guardarDatos() {
        val nombre = binding.nombreLocal.editableText.toString()
        val localidad = binding.direccion.editableText.toString()
        val especialidad = binding.especialidad.editableText.toString()
        val descripcion = binding.descripcionLocal.editableText.toString()
        
        val datoLugar = Lugares(null,nombre, localidad, especialidad, descripcion)
        val baseDeDatos = BaseDatos(this,NOMBRE_BASE, null, VERSION_BD)
        //Se guardara el dato
        val status = baseDeDatos.agregarLugares(datoLugar)
        if (status > -1){
            Toast.makeText(this, "Guardado Correctamente ($status)", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this, "Error al guardar el dato ($status)", Toast.LENGTH_LONG).show()
        }
    }*/

    //Funcion para guardar datos en Firebase
    fun guardarDatosFirebase() {
        FirebaseFirestore.setLoggingEnabled(true)
        val db = Firebase.firestore
        val nombre = binding.nombreLocal.editableText.toString()
        val localidad = binding.direccion.editableText.toString()
        val especialidad = binding.especialidad.editableText.toString()
        val descripcion = binding.descripcionLocal.editableText.toString()
        //Se crea un nuevo hashmap con informacion
        val localAGuardar = hashMapOf(
            KEY_NOMBRE to nombre,
            KEY_LOCALIDAD to localidad,
            KEY_ESPECIALIDAD to especialidad,
            KEY_DESCRIPCION to descripcion
        )
        //Se agrega un nuevo documento con un ID generado
        db.collection(TABLA_LUGARES)
            .add(localAGuardar)
            .addOnSuccessListener{ documentReference ->
                Toast.makeText(this, "Guardado con exito (${documentReference.id}) ", Toast.LENGTH_LONG).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error al guardar el dato (${e.message})", Toast.LENGTH_LONG).show()
            }
    }
}