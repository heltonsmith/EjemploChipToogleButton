package com.heltonbustos.ejemplochiptooglebutton

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.chip.Chip
import com.heltonbustos.ejemplochiptooglebutton.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var bi: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bi = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bi.root)

        bi.chipGrupo.setOnCheckedChangeListener { group, checkedId ->
            val chip = bi.chipGrupo.findViewById<Chip>(checkedId)
            bi.chipGrupo.removeView(chip)
        }

        bi.toggleButton.setOnClickListener {
            Toast.makeText(this, "Selección: ${bi.toggleButton.text}", Toast.LENGTH_SHORT).show()
        }

        bi.btnAgregar.setOnClickListener {
            val chip = layoutInflater.inflate(R.layout.chip_recurso, bi.chipGrupo, false) as Chip
            //val chip = Chip(this)
            var textoChip = bi.txtChip.text
            chip.text = textoChip
            bi.chipGrupo.addView(chip)
        }
    }
}