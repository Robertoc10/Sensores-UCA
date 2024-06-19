package com.uca.edu.campussensors.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.uca.edu.campussensors.R
import com.uca.edu.campussensors.model.Dispositivo
import java.text.SimpleDateFormat
import java.util.Locale

class DispositivoAdapter(private val dispositivos: List<Dispositivo>) :
    RecyclerView.Adapter<DispositivoAdapter.DispositivoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DispositivoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_dispositivo, parent, false)
        return DispositivoViewHolder(view)
    }

    override fun onBindViewHolder(holder: DispositivoViewHolder, position: Int) {
        val dispositivo = dispositivos[position]
        holder.bind(dispositivo)
    }

    override fun getItemCount(): Int = dispositivos.size

    class DispositivoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val dispositivoId: TextView = itemView.findViewById(R.id.dispositivoId)
        private val fenomeno: TextView = itemView.findViewById(R.id.fenomeno)
        private val descripcion: TextView = itemView.findViewById(R.id.descripcion)
        private val unidad: TextView = itemView.findViewById(R.id.unidad)
        private val ubicacionNombre: TextView = itemView.findViewById(R.id.ubicacionNombre)
        private val tipoUbicacion: TextView = itemView.findViewById(R.id.tipoUbicacion)
        private val lecturaIntervalo: TextView = itemView.findViewById(R.id.lecturaIntervalo)
        private val lecturaValor: TextView = itemView.findViewById(R.id.lecturaValor)
        private val lecturaFecha: TextView = itemView.findViewById(R.id.lecturaFecha)

        fun bind(dispositivo: Dispositivo) {
            dispositivoId.text = dispositivo.dispositivo_id
            fenomeno.text = dispositivo.medicion.medicion_fenomeno
            descripcion.text = dispositivo.medicion.medicion_descripcion
            unidad.text = "${dispositivo.medicion.medicion_unidad} (${dispositivo.medicion.medicion_unidad_abreviatura ?: ""})"
            ubicacionNombre.text = dispositivo.ubicacion.ubicacion_nombre
            tipoUbicacion.text = dispositivo.ubicacion.tipo_ubicacion
            lecturaIntervalo.text = "Intervalo de Lectura: ${dispositivo.dispositivo_lectura_intervalo} segundos"

            // Mostrar la lectura más reciente
            if (dispositivo.lecturasRecientes.isNotEmpty()) {
                val lecturaReciente = dispositivo.lecturasRecientes.maxByOrNull { it.createdAt }
                lecturaValor.text = "Valor de Lectura: ${lecturaReciente?.lectura_valor ?: "N/A"}"

                val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
                lecturaFecha.text = "Fecha de Lectura: ${lecturaReciente?.createdAt?: "N/A"}"
            } else {
                lecturaValor.text = "Valor de Lectura: N/A"
                lecturaFecha.text = "Fecha de Lectura: N/A"
            }
        }
    }
}