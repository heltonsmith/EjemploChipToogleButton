package com.heltonbustos.ejemplochiptooglebutton

import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.heltonbustos.ejemplochiptooglebutton.bd.Conexion
import com.heltonbustos.ejemplochiptooglebutton.databinding.ActivityMainBinding
import com.heltonbustos.ejemplochiptooglebutton.recyclerview.AdaptadorDatos
import com.heltonbustos.ejemplochiptooglebutton.recyclerview.LenguajesModelo

class MainActivity : AppCompatActivity() {

    private lateinit var bi: ActivityMainBinding
    var listaLenguajes: ArrayList<LenguajesModelo> = ArrayList()
    lateinit var myRecyclerView: RecyclerView
    lateinit var adaptador:AdaptadorDatos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bi = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bi.root)

        //chip con iconos añadidos automáticamente mediante un listof
        var lenguajes:ArrayList<LenguajesModelo> = ArrayList()
        lenguajes.add(LenguajesModelo(1, "Java"))
        lenguajes.add(LenguajesModelo(2, "C#"))
        lenguajes.add(LenguajesModelo(3, "PHP"))
        lenguajes.add(LenguajesModelo(4, "Kotlin"))
        lenguajes.add(LenguajesModelo(5, "JavaScript"))

        for(i in 0 until lenguajes.size){
            val chip = layoutInflater.inflate(R.layout.chip_recurso, bi.chipGrupo, false) as Chip
            chip.id = lenguajes[i].id
            chip.text = lenguajes[i].lenguaje
            chip.setChipIconResource(R.drawable.ic_code)
            bi.chipGrupo.addView(chip)
        }
        //chip con iconos añadidos automáticamente mediante un listof

        //chip con iconos añadidos automáticamente mediante una bd SQLite
        //insertarLenguajes() //solo ejecutar la primera vez
        /*
        var lenguajesbd = listarLenguajes()

        if (lenguajesbd != null) {
            for(i in 0 until lenguajesbd.size){
                val chip = layoutInflater.inflate(R.layout.chip_recurso, bi.chipGrupo, false) as Chip
                chip.text = lenguajesbd[i]
                chip.setChipIconResource(R.drawable.ic_code)
                bi.chipGrupo.addView(chip)
            }
        }
        */
        //chip con iconos añadidos automáticamente mediante una bd SQLite

        //listener para eliminar chip
        bi.chipGrupo.setOnCheckedChangeListener { group, checkedId ->
            val chip = bi.chipGrupo.findViewById<Chip>(checkedId)
            bi.chipGrupo.removeView(chip)
            refacRecycler(LenguajesModelo(chip.id, chip.text.toString()))
        }
        //listener para eliminar chip

        //tooglebutton
        bi.toggleButton.setOnClickListener {
            Toast.makeText(this, "Selección: ${bi.toggleButton.text}", Toast.LENGTH_SHORT).show()
        }
        //tooglebutton

        //botón para agregar chip al grupo
        bi.btnAgregar.setOnClickListener {
            val chip = layoutInflater.inflate(R.layout.chip_recurso, bi.chipGrupo, false) as Chip
            //val chip = Chip(this)
            var textoChip = bi.txtChip.text
            chip.text = textoChip
            chip.setChipIconResource(R.drawable.ic_code) //icono
            bi.chipGrupo.addView(chip)
            agregarElementosAlRecycler(LenguajesModelo(chip.id, chip.text.toString()))
        }
        //botón para agregar chip al grupo

        //llenado inicial de recycler
        myRecyclerView = findViewById(R.id.myRecyclerView)

        myRecyclerView.layoutManager =
            LinearLayoutManager(applicationContext,
                LinearLayoutManager.VERTICAL, false)

        listaLenguajes.add(LenguajesModelo(1, "Java"))
        listaLenguajes.add(LenguajesModelo(2, "C#"))
        listaLenguajes.add(LenguajesModelo(3, "PHP"))
        listaLenguajes.add(LenguajesModelo(4, "Kotlin"))
        listaLenguajes.add(LenguajesModelo(5, "JavaScript"))

        adaptador = AdaptadorDatos(applicationContext, listaLenguajes)
        myRecyclerView.adapter = adaptador
        //llenado inicial de recycler

    }

    fun agregarElementosAlRecycler(lenguaje: LenguajesModelo){
        //llenado inicial de recycler
        myRecyclerView.layoutManager =
            LinearLayoutManager(applicationContext,
                LinearLayoutManager.VERTICAL, false)

        listaLenguajes.add(lenguaje)

        adaptador = AdaptadorDatos(applicationContext, listaLenguajes)
        myRecyclerView.adapter = adaptador
        //llenado inicial de recycler
    }

    fun refacRecycler(lenguaje: LenguajesModelo){

        //llenado inicial de recycler
        myRecyclerView.layoutManager =
            LinearLayoutManager(applicationContext,
                LinearLayoutManager.VERTICAL, false)

        //elimino el objeto
        for(i in 0.. listaLenguajes.size-1){
            if(listaLenguajes[i].lenguaje.equals(lenguaje.lenguaje)){
                listaLenguajes.removeAt(i)
                break
            }
        }

        adaptador = AdaptadorDatos(applicationContext, listaLenguajes)
        myRecyclerView.adapter = adaptador
        //llenado inicial de recycler

    }

    fun listarLenguajes(): ArrayList<String>? {
        var lenguajes: ArrayList<String> = ArrayList()

        val conexion = Conexion(this, "administracion", null, 1)
        val bd: SQLiteDatabase = conexion.getWritableDatabase()
        val fila = bd.rawQuery("SELECT lenguaje FROM lenguajes", null)

        if (fila.moveToFirst()) {
            do {
                lenguajes.add(fila.getString(0))
            }
            while (fila.moveToNext())
        }

        bd.close()
        return lenguajes
    }

    fun insertarLenguajes(){
        var con:Conexion = Conexion(this, "administracion", null, 1)
        val bd: SQLiteDatabase = con.writableDatabase
        bd.execSQL("INSERT INTO lenguajes values (1, 'Ruby'), (2, 'Pascal'), (3, 'C++'), (4, 'Cobol')")
        bd.close()
    }
}