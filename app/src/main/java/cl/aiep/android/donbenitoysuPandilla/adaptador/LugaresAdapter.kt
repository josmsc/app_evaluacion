package cl.aiep.android.donbenitoysuPandilla.adaptador

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cl.aiep.android.donbenitoysuPandilla.Lugares.Lugares
import cl.aiep.android.donbenitoysuPandilla.R
import cl.aiep.android.donbenitoysuPandilla.RegistroXvista
import cl.aiep.android.donbenitoysuPandilla.databinding.ActivityRegistroXVistaBinding

class LugaresAdapter (
    val listaLugar: List<Lugares>,
    val binding: ActivityRegistroXVistaBinding,
    val listarLugar: RegistroXvista
    ):
        RecyclerView.Adapter<LugaresAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtNombre: TextView
        val txtLocalidad: TextView
        val txtEspecialidad: TextView
        val txtDescripcion: TextView
        val btnReserva: Button

        init {
            txtNombre = view.findViewById(R.id.txtNombreLocal)
            txtLocalidad = view.findViewById(R.id.txtLocalidad)
            txtEspecialidad = view.findViewById(R.id.txtEspecialidad)
            txtDescripcion = view.findViewById(R.id.txtdescripcion)
            btnReserva = view.findViewById(R.id.btnReservar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_lista, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtNombre.text = listaLugar.get(position).nombre
        holder.txtLocalidad.text = listaLugar.get(position).localidad
        holder.txtEspecialidad.text = listaLugar.get(position).especialidad
        holder.txtDescripcion.text = listaLugar.get(position).descripcion
        }

    override fun getItemCount(): Int {
        return listaLugar.size
    }
}

