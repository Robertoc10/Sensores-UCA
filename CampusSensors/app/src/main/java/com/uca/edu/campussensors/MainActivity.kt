package com.uca.edu.campussensors

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uca.edu.campussensors.adapter.DispositivoAdapter
import com.uca.edu.campussensors.api.RetrofitClient
import com.uca.edu.campussensors.model.Dispositivo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : Activity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DispositivoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        fetchDispositivos()
    }

    private fun fetchDispositivos() {
        RetrofitClient.instance.getDispositivos().enqueue(object : Callback<List<Dispositivo>> {
            override fun onResponse(call: Call<List<Dispositivo>>, response: Response<List<Dispositivo>>) {
                if (response.isSuccessful) {
                    val dispositivos = response.body() ?: emptyList()
                    adapter = DispositivoAdapter(dispositivos)
                    recyclerView.adapter = adapter
                }
            }

            override fun onFailure(call: Call<List<Dispositivo>>, t: Throwable) {
                Log.e("MainActivity", "Error fetching dispositivos", t)
            }
        })
    }
}