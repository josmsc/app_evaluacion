package cl.aiep.android.donbenitoysuPandilla

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.recyclerview.widget.LinearLayoutManager
import cl.aiep.android.donbenitoysuPandilla.Constantes.FIREBASE
import cl.aiep.android.donbenitoysuPandilla.Constantes.KEY_DESCRIPCION
import cl.aiep.android.donbenitoysuPandilla.Constantes.KEY_ESPECIALIDAD
import cl.aiep.android.donbenitoysuPandilla.Constantes.KEY_LOCALIDAD
import cl.aiep.android.donbenitoysuPandilla.Constantes.KEY_NOMBRE
import cl.aiep.android.donbenitoysuPandilla.Constantes.TABLA_LUGARES
import cl.aiep.android.donbenitoysuPandilla.Lugares.Lugares
import cl.aiep.android.donbenitoysuPandilla.adaptador.LugaresAdapter
import cl.aiep.android.donbenitoysuPandilla.databinding.ActivityRegistroXVistaBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

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
            binding.progressBar.visibility = VISIBLE
            binding.listaData.visibility = GONE
            listarData(FIREBASE)
        }
    }

    private fun ingresar() {
        startActivity(Intent(this,Formulario::class.java))
    }

    //Funcion para listar la data
    fun listarData(fuenteDatos:String){
        val listaData = ArrayList<Lugares>()
        val db = Firebase.firestore
        //que tabla traeremos
        db.collection(TABLA_LUGARES)
            .orderBy("nombre")
            .get()
            .addOnSuccessListener { result ->
                for (document in result){
                    Log.d("Data", "${document.id} => ${document.data}")
                    //Se crea un objeto lugar y se crea como objeto para agregarlo a la vista
                    val lugar = Lugares(
                        document.id,
                        document.data[KEY_NOMBRE].toString(),
                        document.data[KEY_LOCALIDAD].toString(),
                        document.data[KEY_ESPECIALIDAD].toString(),
                        document.data[KEY_DESCRIPCION].toString()
                    )
                    //Se agrega la data a la lista
                    listaData.add(lugar)
                }
                //Configurar la lista
                mostrarDataEnLista(listaData)
            }
            .addOnFailureListener { exception ->
                binding.listaData.visibility = GONE
                binding.progressBar.visibility = GONE
                Log.w("Data", "Error getting documents.", exception)
            }

    }

    private fun mostrarDataEnLista(listaData:ArrayList<Lugares>){
        val adaptadorDataLugares = LugaresAdapter(listaData, binding, this)
        //configurar la lista del recyclerview
        binding.listaData.apply {
            layoutManager = LinearLayoutManager (applicationContext)
            adapter = adaptadorDataLugares
        }
        //Mostrar la lista y ocultar el lugar
        binding.progressBar.visibility = GONE
        binding.listaData.visibility = VISIBLE
    }




}







