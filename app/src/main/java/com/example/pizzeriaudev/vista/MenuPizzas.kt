package com.example.pizzeriaudev.vista

import android.annotation.SuppressLint
import android.content.ContentProviderClient
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.example.pizzeriaudev.R
import com.example.pizzeriaudev.controlador.Pizza
import com.example.pizzeriaudev.controlador.PizzaListResponce
import com.example.pizzeriaudev.modelo.PizzeriaServices
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MenuPizzas : AppCompatActivity() {

    lateinit var layout_boton_pizza: LinearLayout
    lateinit var name: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_pizzas)
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val httpClient = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()

        layout_boton_pizza = findViewById(R.id.layout_boton_pizza)
        val retrofit = Retrofit.Builder()
            .baseUrl("http://18.221.242.208:80/api/")
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val obtenerPizzas = retrofit.create(PizzeriaServices::class.java)

        obtenerPizzas.obtenerPizzas()
            .enqueue(object : Callback<List<Pizza>> {
                override fun onResponse(
                    call: Call<List<Pizza>>,
                    response: Response<List<Pizza>>
                    // Una Response encapsula una respuesta analizada que se entregará para un
                    // tipo específico (como una string, una imagen o un elemento JSON)
                    ) {
                    if (response.isSuccessful) {
                        runOnUiThread {// esta funcion ejecuta en un hilo los componentes de una pantalla
                        var pizza = response.body()
                            if (pizza != null){
                                pizza.forEach {
                                  crearBoton(it)
                                }
                            }else {
                                Log.e(TAG, "Error al obtener la descripcion del pokemon")
                            }
                        }

                    }

                }

                override fun onFailure(call: Call<List<Pizza>>, t: Throwable) {
                     Log.e(TAG, "Error al obtener la descripcion del pokemon")
                }
            })


    }

    fun obtenerNumeroDeUrl(url: String): Int {
        val urlSinBarra =  url.substringBeforeLast("/") // con esta funcion lo que hago es quitar la funcion el la ultima / de una url o un caracter
        val urlNumero = urlSinBarra.substringAfterLast("/") //con esta funcion lo que hago es quitar la funcion el la penultima / de una url o un caracter
        return urlNumero.toInt()
    }

    private fun crearBoton(pizza: Pizza) {
        val button = Button(this)
        button.text = "${pizza.name}"
        button.setOnClickListener {
            //Toast.makeText(this, "${pizza.name}", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, DescripcionPizza::class.java)
               intent.putExtra("url",pizza.id)
             intent.putExtra("name",pizza.name)
              startActivity(intent)
        }
         layout_boton_pizza.addView(button)
    }
}